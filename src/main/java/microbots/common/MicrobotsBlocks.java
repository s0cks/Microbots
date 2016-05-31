package microbots.common;

import microbots.common.blocks.BlockAssembler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class MicrobotsBlocks {
  private MicrobotsBlocks() {}

  public static final Block blockAssembler = new BlockAssembler()
                                             .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
                                             .setUnlocalizedName("assembler");

  public static void init(){
    register(blockAssembler);
  }

  private static void register(Block b) {
    String unlocName = b.getUnlocalizedName();
    ResourceLocation loc = new ResourceLocation("mmc", unlocName.substring(unlocName.lastIndexOf('.') + 1));
    GameRegistry.register(b, loc);
    GameRegistry.register(new ItemBlock(b), loc);
  }
}