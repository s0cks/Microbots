package microbots.common;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class MicrobotsTickHandler{
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent e){
    if(e.phase == TickEvent.Phase.START){
      Microbots.SERVER_REGISTRY.update();
    }
  }
}