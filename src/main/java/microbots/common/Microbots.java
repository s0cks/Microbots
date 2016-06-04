package microbots.common;

import microbots.api.IRobitPart;
import microbots.api.IRobitPartManager;
import microbots.api.MicrobotsApi;
import microbots.client.gui.GuiRobit;
import microbots.common.core.registry.ClientRobitRegistry;
import microbots.common.core.registry.RobitRegistry;
import microbots.common.core.registry.ServerRobitRegistry;
import microbots.common.core.robit.ClientRobit;
import microbots.common.core.robit.ServerRobit;
import microbots.common.entity.EntityRobit;
import microbots.common.net.MicrobotsNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import shoes.common.injector.Injector;
import shoes.common.injector.InjectorFactory;

import java.util.HashMap;
import java.util.Map;

@Mod(
modid = "microbots",
name = "Microbots",
version = "0.0.0.0"
)
public final class Microbots
implements IRobitPartManager,
           IGuiHandler{
  @Mod.Instance("microbots")
  public static Microbots instance;

  @SidedProxy(
    clientSide = "microbots.client.ClientProxy",
    serverSide = "microbots.common.CommonProxy"
  )
  public static CommonProxy proxy;

  public static final byte GUI_ROBIT = 0x0;

  public static final RobitRegistry<ClientRobit> CLIENT_REGISTRY = new ClientRobitRegistry();
  public static final RobitRegistry<ServerRobit> SERVER_REGISTRY = new ServerRobitRegistry();
  public static final Injector injector = InjectorFactory.create(new MicrobotsModule());

  private final Map<String, IRobitPart> parts = new HashMap<>();

  @Mod.EventHandler
  public void onPreInit(FMLPreInitializationEvent e) {
    MicrobotsApi.PART_MANAGER = this;

    try {
      Class.forName("microbots.client.model.DefaultRobitModels");
    } catch (ClassNotFoundException e1) {
      throw new RuntimeException(e1);
    }

    MicrobotsTiles.init();
    MicrobotsBlocks.init();
    MicrobotsItems.init();
    MicrobotsNetwork.init();

    proxy.preInit();
  }

  @Mod.EventHandler
  public void onInit(FMLInitializationEvent e) {
    EntityRegistry.registerModEntity(EntityRobit.class, "robit", 1, instance, 80, 3, true);

    MinecraftForge.EVENT_BUS.register(new MicrobotsTickHandler());

    proxy.init();
  }

  @Mod.EventHandler
  public void onPostInit(FMLPostInitializationEvent e) {
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, instance);
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
    if(!this.parts.containsKey(id)) throw new IllegalStateException("Unknown part: " + id);
    return this.parts.get(id);
  }

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch(ID){
      default: return null;
    }
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch(ID){
      case GUI_ROBIT: return new GuiRobit(((EntityRobit) world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(x - 1, y - 1, z - 1, x + 1, y + 1 , z + 1)).get(0)));
      default: return null;
    }
  }
}