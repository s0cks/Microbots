package microbots.api;

import net.minecraft.entity.Entity;

public interface IRobitPart{
  public static enum Type{
    TORSO,
    LEFT_ARM,
    LEFT_LEG,
    RIGHT_ARM,
    RIGHT_LEG;
  }

  public String id();
  public Type type();
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5);
}