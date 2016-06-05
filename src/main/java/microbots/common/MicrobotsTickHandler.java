package microbots.common;

import microbots.common.core.robit.ServerRobit;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class MicrobotsTickHandler{
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent e){
    if(e.phase == TickEvent.Phase.START){
      Microbots.SERVER_REGISTRY.all().forEach(ServerRobit::sync);
    }
  }
}