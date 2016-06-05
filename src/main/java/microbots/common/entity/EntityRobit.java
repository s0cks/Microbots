package microbots.common.entity;

import microbots.api.IRobitAccess;
import microbots.client.model.DefaultRobitModels;
import microbots.client.model.ModularRobitModel;
import microbots.common.Microbots;
import microbots.common.MicrobotsItems;
import microbots.common.core.robit.ClientRobit;
import microbots.common.core.robit.ServerRobit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class EntityRobit
extends Entity{
  private static final ExecutorService threads = Executors.newFixedThreadPool(128);
  private static final ModelSerializer MODELS = new ModelSerializer();
  private static final float VELOCITY = 0.4F;
  private static final float ACCELERATION = 0.1F;
  private static final float DRAG = 0.8F;

  public static final BlockPos UNBOUND_TARGET = new BlockPos(-1, -1, -1);

  public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityRobit.class, DataSerializers.VARINT);
  public static final DataParameter<ModularRobitModel> MODEL = EntityDataManager.createKey(EntityRobit.class, MODELS);
  public static final DataParameter<String> ROBIT = EntityDataManager.createKey(EntityRobit.class, DataSerializers.STRING);
  public static final DataParameter<BlockPos> TARGET = EntityDataManager.createKey(EntityRobit.class, DataSerializers.BLOCK_POS);
  public static final DataParameter<EnumFacing> FACING = EntityDataManager.createKey(EntityRobit.class, DataSerializers.FACING);

  public float prevLimbSwingAmount = 0.0F;
  public float limbSwingAmount = 0.0F;
  public float limbSwing = 0.0F;

  private Future<?> task = null;

  public EntityRobit(World worldIn){
    super(worldIn);
    this.setSize(1.0F, 1.0F);
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
    this.getDataManager().register(COLOR, 0x005050);
    this.getDataManager().register(MODEL, DefaultRobitModels.TUNGSTEN);
    this.getDataManager().register(ROBIT, "");
    this.getDataManager().register(TARGET, UNBOUND_TARGET);
    this.getDataManager().register(FACING, EnumFacing.WEST);

    if((this.getDataManager().get(ROBIT)).isEmpty()){
      String id = UUID.randomUUID().toString();
      this.getDataManager().set(ROBIT, id);
      ServerRobit robit = new ServerRobit(id, this);
      Microbots.SERVER_REGISTRY.register(robit);
    }

    if(this.task == null){
      ServerRobit robit = this.getServerRobit();
      this.task = threads.submit(robit::initialize);
    }
  }

  public void setModel(ModularRobitModel model){
    this.getDataManager().set(MODEL, model);
  }

  public void setFacing(EnumFacing dir){
    this.getDataManager().set(FACING, dir);
  }

  public EnumFacing getFacing(){
    return this.getDataManager().get(FACING);
  }

  public BlockPos getTarget(){
    return this.getDataManager().get(TARGET);
  }

  public void setTarget(BlockPos pos){
    this.getDataManager().set(TARGET, pos);
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    if(this.getTarget() != UNBOUND_TARGET){
      Vec3d toTarget = new Vec3d(this.getTarget().getX() - this.posX, this.getTarget().getY() - this.posY, this.getTarget().getZ() - this.posZ);
      Vec3d velocity = new Vec3d(this.motionX, this.motionY, this.motionZ);
      double distance = toTarget.lengthVector();
      if(distance > 0 && (distance > 0.005F || velocity.dotProduct(velocity) > 0.005F)){
        double acceleration = Math.min(ACCELERATION, distance) / distance;
        velocity = velocity.addVector(
        toTarget.xCoord * acceleration,
        toTarget.yCoord * acceleration,
        toTarget.zCoord * acceleration
        );
        this.motionX = Math.max(-VELOCITY, Math.min(VELOCITY, velocity.xCoord));
        this.motionZ = Math.max(-VELOCITY, Math.min(VELOCITY, velocity.zCoord));
      } else{
        this.motionX = 0;
        this.motionZ = 0;
        this.posX = this.getTarget().getX();
        this.posZ = this.getTarget().getZ();
        this.setTarget(UNBOUND_TARGET);
      }

      this.moveEntity(this.motionX, 0, this.motionZ);

      if(distance > 0 && (distance > 0.005F || velocity.dotProduct(velocity) > 0.005F)){
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double deltaX = this.posX - this.prevPosX;
        double deltaZ = this.posZ - this.prevPosZ;
        float delta = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
        if(delta > 1.0F){
          delta = 1.0F;
        }
        this.limbSwingAmount += (delta - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
      } else{
        this.limbSwing = 0.0F;
        this.limbSwingAmount = 0.0F;
      }

      this.motionX *= DRAG;
      this.motionZ *= DRAG;
    }
  }

  @Override
  protected void readEntityFromNBT(NBTTagCompound compound) {
    if(compound.hasKey("RobitModel")){
      this.getDataManager().set(MODEL, ModularRobitModel.fromNBT(compound.getCompoundTag("RobitModel")));
    }
  }

  @Override
  protected void writeEntityToNBT(NBTTagCompound compound) {
    NBTTagCompound modelCompound = new NBTTagCompound();
    this.getModel().writeToNBT(modelCompound);
    compound.setTag("RobitModel", modelCompound);
  }

  @Override
  public void setDead() {
    super.setDead();
    if(this.task != null) this.task.cancel(true);
  }

  @Override
  public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, ItemStack stack, EnumHand hand) {
    if(player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof IRobitAccess && !player.isSneaking()){
      player.openGui(Microbots.instance, Microbots.GUI_ROBIT, this.getEntityWorld(), ((int) this.posX), ((int) this.posY), ((int) this.posZ));
    } else if(player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof IRobitAccess && player.isSneaking()){
      ItemStack robit = new ItemStack(MicrobotsItems.itemRobit);
      NBTTagCompound compound = new NBTTagCompound();
      this.getModel().writeToNBT(compound);
      robit.setTagCompound(compound);
      EntityItem item = new EntityItem(this.getEntityWorld(), this.posX, this.posY, this.posZ, robit);
      this.setDead();
      if(!this.getEntityWorld().isRemote){
        this.getEntityWorld().spawnEntityInWorld(item);
      }
    }
    return EnumActionResult.PASS;
  }

  @Override
  public boolean canBeCollidedWith() {
    return true;
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