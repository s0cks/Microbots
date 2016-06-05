package microbots.common.core.registry;

import microbots.common.core.robit.ClientRobit;
import microbots.common.net.MicrobotsNetwork;
import microbots.common.net.PacketRequestSync;

public final class ClientRobitRegistry
extends RobitRegistry<ClientRobit> {
  @Override
  public void register(ClientRobit robit) {
    super.register(robit);
    MicrobotsNetwork.INSTANCE.sendToServer(new PacketRequestSync(robit.id()));
  }
}