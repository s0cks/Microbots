package microbots.common.core.registry;

import microbots.common.core.robit.ServerRobit;

public final class ServerRobitRegistry
extends RobitRegistry<ServerRobit>{
  @Override
  public void register(ServerRobit robit) {
    super.register(robit);
    robit.sync();
  }
}