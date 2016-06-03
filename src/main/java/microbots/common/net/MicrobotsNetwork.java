package microbots.common.net;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class MicrobotsNetwork{
  public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("microbots");

  public static void init(){
    INSTANCE.registerMessage(PacketRequestSync.class, PacketRequestSync.class, 0x0, Side.SERVER);
    INSTANCE.registerMessage(PacketSyncClient.class, PacketSyncClient.class, 0x1, Side.CLIENT);
  }
}