package microbots.common.entity;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.ModularRobitModel;
import microbots.common.Microbots;
import microbots.common.core.robit.ClientRobit;
import microbots.common.core.robit.ServerRobit;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;

public final class EntityRobit
extends EntityGolem {
  private static final ForkJoinPool threads = new ForkJoinPool();
  private static final ModelSerializer MODELS = new ModelSerializer();

  public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityRobit.class, DataSerializers.VARINT);
  public static final DataParameter<ModularRobitModel> MODEL = EntityDataManager.createKey(EntityRobit.class, MODELS);
  public static final DataParameter<String> ROBIT = EntityDataManager.createKey(EntityRobit.class, DataSerializers.STRING);

  private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

  public EntityRobit(World worldIn){
    super(worldIn);
    this.setEntityBoundingBox(BOX);
  }

  public ModularRobitModel getModel(){
    return this.getDataManager().get(MODEL);
  }

  public ServerRobit getServerRobit(){
    return Microbots.SERVER_REGISTRY.get(this.getDataManager().get(ROBIT));
  }

  public ClientRobit getClientRobit(){
    if(Microbots.CLIENT_REGISTRY.contains(this.getDataManager().get(ROBIT))){
      return Microbots.CLIENT_REGISTRY.get(this.getDataManager().get(ROBIT));
    }

    ClientRobit robit = new ClientRobit(this.getDataManager().get(ROBIT));
    Microbots.CLIENT_REGISTRY.register(robit);
    return robit;
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.getDataManager().register(COLOR, 0x005050);
    this.getDataManager().register(MODEL, DefaultRobitModels.TUNGSTEN);
    this.getDataManager().register(ROBIT, "");
  }

  public void initialize(ModularRobitModel model){
    this.getDataManager().set(MODEL, model);
    String id = UUID.randomUUID().toString();
    this.getDataManager().set(ROBIT, id);
    ServerRobit robit = new ServerRobit(id, this);
    Microbots.SERVER_REGISTRY.register(robit);
    threads.execute(robit::initialize);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    if(compound.hasKey("RobitModel")){
      this.getDataManager().set(MODEL, ModularRobitModel.fromNBT(compound.getCompoundTag("RobitModel")));
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    NBTTagCompound modelCompound = new NBTTagCompound();
    this.getModel().writeToNBT(modelCompound);
    compound.setTag("RobitModel", modelCompound);
  }

  @Override
  public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, ItemStack stack, EnumHand hand) {
    player.openGui(Microbots.instance, Microbots.GUI_ROBIT, this.getEntityWorld(), ((int) this.posX), ((int) this.posY), ((int) this.posZ));
    return EnumActionResult.PASS;
  }

  @Override
  public Iterable<ItemStack> getArmorInventoryList() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

  }

  private static final class ModelSerializer
  implements DataSerializer<ModularRobitModel>{
    public ModelSerializer(){
      DataSerializers.registerSerializer(this);
    }

    @Override
    public void write(PacketBuffer buf, ModularRobitModel value) {
      NBTTagCompound compound = new NBTTagCompound();
      value.writeToNBT(compound);
      ByteBufUtils.writeTag(buf, compound);
    }

    @Override
    public ModularRobitModel read(PacketBuffer buf)
    throws IOException {
      return ModularRobitModel.fromNBT(ByteBufUtils.readTag(buf));
    }

    @Override
    public DataParameter<ModularRobitModel> createKey(int id) {
      return new DataParameter<>(id, this);
    }
  }
}