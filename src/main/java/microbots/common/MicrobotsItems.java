package microbots.common;

import microbots.common.item.ItemRobit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class MicrobotsItems {
  private MicrobotsItems() {}

  public static final Item itemRobit = new ItemRobit()
                                       .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
                                       .setUnlocalizedName("robit");

  public static void init(){
    register(itemRobit);
  }

  private static void register(Item item){
    String unlocName = item.getUnlocalizedName();
    ResourceLocation loc = new ResourceLocation("microbots", unlocName.substring(unlocName.lastIndexOf('.') + 1));
    GameRegistry.register(item, loc);
  }
}