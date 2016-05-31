package microbots.client.model;

import microbots.api.IRobitPart;
import microbots.api.MicrobotsApi;
import microbots.client.model.tungsten.TungstenLeftArm;
import microbots.client.model.tungsten.TungstenLeftLeg;
import microbots.client.model.tungsten.TungstenRightArm;
import microbots.client.model.tungsten.TungstenRightLeg;
import microbots.client.model.tungsten.TungstenTorso;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public final class DefaultRobitModels {
  private DefaultRobitModels() {}

  public static final ResourceLocation TUNGSTEN_TEX = new ResourceLocation("microbots", "textures/entity/tungsten.png");
  public static final ModularRobitModel TUNGSTEN = new ModularRobitModelBuilder()
                                                   .add(new TungstenTorso())
                                                   .add(new TungstenLeftArm())
                                                   .add(new TungstenLeftLeg())
                                                   .add(new TungstenRightArm())
                                                   .add(new TungstenRightLeg())
                                                   .build();

  private static final class ModularRobitModelBuilder {
    private final List<IRobitPart> parts = new LinkedList<>();

    public ModularRobitModelBuilder add(IRobitPart part) {
      this.parts.add(part);
      MicrobotsApi.PART_MANAGER.registerPart(part);
      return this;
    }

    public ModularRobitModel build() {
      return new ModularRobitModel(this.parts);
    }
  }
}