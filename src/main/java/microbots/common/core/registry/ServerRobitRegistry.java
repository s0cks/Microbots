package microbots.common.core.registry;

import microbots.common.core.robit.ServerRobit;

public final class ServerRobitRegistry
extends RobitRegistry<ServerRobit>{
  @Override
  public void update() {
    this.all().forEach(ServerRobit::sync);
  }
}