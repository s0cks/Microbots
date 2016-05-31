package microbots.client;

import microbots.client.render.tile.RenderTileAssembler;
import microbots.common.CommonProxy;
import microbots.common.tile.TileEntityAssembler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ClientProxy
extends CommonProxy{
  @Override
  public Minecraft client() {
    return FMLClientHandler.instance().getClient();
  }

  @Override
  public void preInit() {
    MinecraftForge.EVENT_BUS.register(ClientEventHandler.instance());
  }

  @Override
  public void init() {
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAssembler.class, new RenderTileAssembler());
  }
}