package microbots.client.model.robits.base;

import microbots.api.IRobitPart;
import net.minecraft.client.model.ModelBase;

public class RobitRightLeg
extends ModelBase
implements IRobitPart{
  private final String id;

  public RobitRightLeg(String id){
    this.id = id;
  }

  @Override
  public String id() {
    return this.id;
  }

  @Override
  public Type type() {
    return Type.RIGHT_LEG;
  }
}