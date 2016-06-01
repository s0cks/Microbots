package microbots.common;

import dorkbox.tweenengine.TweenManager;
import net.minecraft.client.Minecraft;

public class CommonProxy{
  public TweenManager tweenManager(){ return null; }
  public Minecraft client(){ return null; }
  public void preInit(){}
  public void init(){}
}