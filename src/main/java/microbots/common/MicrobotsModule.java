package microbots.common;

import net.minecraft.util.ResourceLocation;
import shoes.common.injector.Module;
import truetyper.FontLoader;
import truetyper.TrueTypeFont;

public final class MicrobotsModule
extends Module{
  private TrueTypeFont font;

  @Override
  protected void configure() {
    this.bind(TrueTypeFont.class).toProvider(() ->{
      if(font == null) font = FontLoader.createFont(new ResourceLocation("microbots", "font/droid_sans_mono.ttf"), 12.0F, true);
      return font;
    });
  }
}