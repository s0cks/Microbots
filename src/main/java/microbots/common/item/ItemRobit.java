package microbots.common.item;

import microbots.client.model.DefaultRobitModels;
import microbots.common.entity.EntityRobit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRobit
extends Item{
  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if(!worldIn.isRemote){
      EntityRobit robit = new EntityRobit(worldIn, DefaultRobitModels.TUNGSTEN);
      robit.setPosition(pos.getX(), pos.getY(), pos.getZ());
      worldIn.spawnEntityInWorld(robit);
    }
    return EnumActionResult.PASS;
  }
}