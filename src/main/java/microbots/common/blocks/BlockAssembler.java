package microbots.common.blocks;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.ModularRobitModel;
import microbots.common.item.ItemRobit;
import microbots.common.tile.TileEntityAssembler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAssembler
extends BlockContainer{
  public BlockAssembler() {
    super(Material.ROCK);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (playerIn.getHeldItem(EnumHand.MAIN_HAND) != null){
      ItemStack stack = playerIn.getHeldItem(EnumHand.MAIN_HAND);
      if(stack.getItem() instanceof ItemRobit){
        if(stack.hasTagCompound()){
          ((TileEntityAssembler) worldIn.getTileEntity(pos)).setModel(ModularRobitModel.fromNBT(stack.getTagCompound()));
        } else{
          ((TileEntityAssembler) worldIn.getTileEntity(pos)).setModel(DefaultRobitModels.ALL[stack.getItemDamage()]);
        }
        return true;
      }
    }

    return false;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityAssembler();
  }
}