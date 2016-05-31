package microbots.client;

import microbots.client.render.entity.RenderEntityRobit;
import microbots.client.render.tile.RenderTileAssembler;
import microbots.common.CommonProxy;
import microbots.common.entity.EntityRobit;
import microbots.common.tile.TileEntityAssembler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

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
    RenderingRegistry.registerEntityRenderingHandler(EntityRobit.class, (manager) -> {
      return new RenderEntityRobit(manager, null, 0.0F);
    });
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAssembler.class, new RenderTileAssembler());
  }
}