package microbots.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
modid = "microbots",
name = "Microbots",
version = "0.0.0.0"
)
public final class Microbots {
  @Mod.Instance("microbots")
  public static Microbots instance;

  @SidedProxy(
    clientSide = "microbots.client.ClientProxy",
    serverSide = "microbots.common.CommonProxy"
  )
  public static CommonProxy proxy;

  @Mod.EventHandler
  public void onPreInit(FMLPreInitializationEvent e) {
    proxy.preInit();
  }

  @Mod.EventHandler
  public void onInit(FMLInitializationEvent e) {
    MicrobotsTiles.init();
    MicrobotsBlocks.init();

    proxy.init();
  }

  @Mod.EventHandler
  public void onPostInit(FMLPostInitializationEvent e) {

  }

  @Mod.EventHandler
  public void onServerStarting(FMLServerStartingEvent e) {

  }
}