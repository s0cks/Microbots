package microbots.client;

import dorkbox.tweenengine.TweenManager;
import microbots.client.model.DefaultRobitModels;
import microbots.client.render.entity.RenderEntityRobit;
import microbots.client.render.tile.RenderTileAssembler;
import microbots.common.CommonProxy;
import microbots.common.MicrobotsBlocks;
import microbots.common.MicrobotsItems;
import microbots.common.entity.EntityRobit;
import microbots.common.tile.TileEntityAssembler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public final class ClientProxy
extends CommonProxy{
  private final TweenManager manager = new TweenManager();

  @Override
  public TweenManager tweenManager() {
    return this.manager;
  }

  @Override
  public Minecraft client() {
    return FMLClientHandler.instance().getClient();
  }

  @Override
  public void preInit() {
    RenderingRegistry.registerEntityRenderingHandler(EntityRobit.class, RenderEntityRobit::new);
    MinecraftForge.EVENT_BUS.register(ClientEventHandler.instance());

    for(int i = 0; i < DefaultRobitModels.ALL_NAMES.length; i++){
      this.registerRender(MicrobotsItems.itemRobit, i, "robit");
    }
    this.registerRender(MicrobotsItems.itemPDA, 0, "pda");
    this.registerRender(MicrobotsBlocks.blockAssembler, "assembler");
  }

  @Override
  public void init() {
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAssembler.class, new RenderTileAssembler());
  }

  private void registerRender(Block b, String id){
    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(new ResourceLocation("microbots", id), "inventory"));
  }

  private void registerRender(Item item, int meta, String id){
    ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation("microbots", id), "inventory"));
  }
}