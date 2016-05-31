package microbots.common;

import microbots.common.tile.TileEntityAssembler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class MicrobotsTiles{
  private MicrobotsTiles(){}

  public static void init(){
    GameRegistry.registerTileEntity(TileEntityAssembler.class, "microbots:assembler");
  }
}