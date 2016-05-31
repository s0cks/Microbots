package microbots.common.entity;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.ModularRobitModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.io.IOException;
import java.util.Collections;

public final class EntityRobit
extends EntityLivingBase{
  private static final ModelSerializer MODELS = new ModelSerializer();

  public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityRobit.class, DataSerializers.VARINT);
  public static final DataParameter<ModularRobitModel> MODEL = EntityDataManager.createKey(EntityRobit.class, MODELS);

  private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

  public EntityRobit(World worldIn){
    super(worldIn);
    this.setEntityBoundingBox(BOX);
  }

  public ModularRobitModel getModel(){
    return this.getDataManager().get(MODEL);
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.getDataManager().register(COLOR, 0xFF4500);
    this.getDataManager().register(MODEL, DefaultRobitModels.TUNGSTEN);
  }

  public void initialize(ModularRobitModel model){
    this.getDataManager().set(MODEL, model);
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
  public Iterable<ItemStack> getArmorInventoryList() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
    return null;
  }

  @Override
  public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

  }

  @Override
  public EnumHandSide getPrimaryHand() {
    return EnumHandSide.RIGHT;
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