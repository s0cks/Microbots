package microbots.common.blocks;

import microbots.common.tile.TileEntityAssembler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAssembler
extends BlockContainer{
  public BlockAssembler() {
    super(Material.ROCK);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityAssembler();
  }
}