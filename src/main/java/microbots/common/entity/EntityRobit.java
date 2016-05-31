package microbots.common.entity;

import microbots.client.model.ModularRobitModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public final class EntityRobit
extends EntityLivingBase {
  public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityRobit.class, DataSerializers.VARINT);

  private final ModularRobitModel model;

  public EntityRobit(World worldIn, ModularRobitModel model) {
    super(worldIn);
    this.model = model;
  }

  public ModularRobitModel getModel(){
    return this.model;
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.getDataManager().set(COLOR, 0xFF0000);
  }

  @Override
  public Iterable<ItemStack> getArmorInventoryList() {
    return null;
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
}