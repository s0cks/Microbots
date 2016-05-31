package microbots.common;

import microbots.api.IRobitPart;
import microbots.api.IRobitPartManager;
import microbots.api.MicrobotsApi;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.HashMap;
import java.util.Map;

@Mod(
modid = "microbots",
name = "Microbots",
version = "0.0.0.0"
)
public final class Microbots
implements IRobitPartManager{
  @Mod.Instance("microbots")
  public static Microbots instance;

  @SidedProxy(
    clientSide = "microbots.client.ClientProxy",
    serverSide = "microbots.common.CommonProxy"
  )
  public static CommonProxy proxy;

  private final Map<String, IRobitPart> parts = new HashMap<>();

  @Mod.EventHandler
  public void onPreInit(FMLPreInitializationEvent e) {
    MicrobotsApi.PART_MANAGER = this;

    try {
      Class.forName("microbots.client.model.DefaultRobitModels");
    } catch (ClassNotFoundException e1) {
      throw new RuntimeException(e1);
    }

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

  @Override
  public void registerPart(IRobitPart part) {
    if (this.parts.containsKey(part.id())){
      throw new IllegalStateException("Duplicate part named: " + part.id());
    }
    this.parts.put(part.id(), part);
  }

  @Override
  public IRobitPart getPart(String id) {
    return this.parts.get(id);
  }
}