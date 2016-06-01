package microbots.client;


import microbots.common.Microbots;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.TimeUnit;

public final class ClientEventHandler {
  private static ClientEventHandler instance;

  public static ClientEventHandler instance() {
    return (instance == null
            ? (instance = new ClientEventHandler())
            : instance);
  }

  private TextureAtlasSprite assemblerTop;
  private TextureAtlasSprite assemblerBottom;
  private long lastSystemTime = 0;

  @SubscribeEvent
  public void onTextureStitch(TextureStitchEvent e) {
    this.assemblerTop = e.getMap()
                         .registerSprite(new ResourceLocation("mmc", "textures/models/assembler_top.png"));
    this.assemblerBottom = e.getMap()
                            .registerSprite(new ResourceLocation("mmc", "textures/models/assembler_bottom.png"));
  }

  @SubscribeEvent
  public void clientTick(TickEvent.ClientTickEvent e){
    if(e.phase == TickEvent.Phase.START){
      long now = Minecraft.getSystemTime();
      long delta = now - this.lastSystemTime;
      this.lastSystemTime = now;
      Microbots.proxy.tweenManager().update(TimeUnit.MILLISECONDS.toNanos(delta));
    }
  }

  public TextureAtlasSprite assemblerTop() {
    return this.assemblerTop;
  }

  public TextureAtlasSprite assemblerBottom() {
    return this.assemblerBottom;
  }
}