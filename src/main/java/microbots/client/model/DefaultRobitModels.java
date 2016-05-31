package microbots.client.model;

import microbots.api.IRobitPart;
import microbots.api.MicrobotsApi;
import microbots.client.model.robits.brass.BrassLeftArm;
import microbots.client.model.robits.brass.BrassLeftLeg;
import microbots.client.model.robits.brass.BrassRightArm;
import microbots.client.model.robits.brass.BrassRightLeg;
import microbots.client.model.robits.brass.BrassTorso;
import microbots.client.model.robits.bronze.BronzeLeftArm;
import microbots.client.model.robits.bronze.BronzeLeftLeg;
import microbots.client.model.robits.bronze.BronzeRightArm;
import microbots.client.model.robits.bronze.BronzeRightLeg;
import microbots.client.model.robits.bronze.BronzeTorso;
import microbots.client.model.robits.electrum.ElectrumLeftArm;
import microbots.client.model.robits.electrum.ElectrumLeftLeg;
import microbots.client.model.robits.electrum.ElectrumRightArm;
import microbots.client.model.robits.electrum.ElectrumRightLeg;
import microbots.client.model.robits.electrum.ElectrumTorso;
import microbots.client.model.robits.rosegold.RosegoldLeftArm;
import microbots.client.model.robits.rosegold.RosegoldLeftLeg;
import microbots.client.model.robits.rosegold.RosegoldRightArm;
import microbots.client.model.robits.rosegold.RosegoldRightLeg;
import microbots.client.model.robits.rosegold.RosegoldTorso;
import microbots.client.model.robits.starsteel.StarsteelLeftArm;
import microbots.client.model.robits.starsteel.StarsteelLeftLeg;
import microbots.client.model.robits.starsteel.StarsteelRightArm;
import microbots.client.model.robits.starsteel.StarsteelRightLeg;
import microbots.client.model.robits.starsteel.StarsteelTorso;
import microbots.client.model.robits.steel.SteelLeftArm;
import microbots.client.model.robits.steel.SteelLeftLeg;
import microbots.client.model.robits.steel.SteelRightArm;
import microbots.client.model.robits.steel.SteelRightLeg;
import microbots.client.model.robits.steel.SteelTorso;
import microbots.client.model.robits.tungsten.TungstenLeftArm;
import microbots.client.model.robits.tungsten.TungstenLeftLeg;
import microbots.client.model.robits.tungsten.TungstenRightArm;
import microbots.client.model.robits.tungsten.TungstenRightLeg;
import microbots.client.model.robits.tungsten.TungstenTorso;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public final class DefaultRobitModels {
  private DefaultRobitModels() {}

  // Tungsten
  public static final ResourceLocation TUNGSTEN_TEX = new ResourceLocation("microbots", "textures/entity/tungsten.png");
  public static final ModularRobitModel TUNGSTEN = new ModularRobitModelBuilder()
                                                   .add(new TungstenTorso())
                                                   .add(new TungstenLeftArm())
                                                   .add(new TungstenLeftLeg())
                                                   .add(new TungstenRightArm())
                                                   .add(new TungstenRightLeg())
                                                   .build();

  // Steel
  public static final ResourceLocation STEEL_TEX = new ResourceLocation("microbots", "textures/entity/steel.png");
  public static final ModularRobitModel STEEL = new ModularRobitModelBuilder()
                                                .add(new SteelTorso())
                                                .add(new SteelLeftArm())
                                                .add(new SteelLeftLeg())
                                                .add(new SteelRightArm())
                                                .add(new SteelRightLeg())
                                                .build();

  // Starsteel
  public static final ResourceLocation STARSTEEL_TEX = new ResourceLocation("microbots", "textures/entity/starsteel.png");
  public static final ModularRobitModel STARSTEEL = new ModularRobitModelBuilder()
                                                    .add(new StarsteelTorso())
                                                    .add(new StarsteelLeftArm())
                                                    .add(new StarsteelLeftLeg())
                                                    .add(new StarsteelRightArm())
                                                    .add(new StarsteelRightLeg())
                                                    .build();

  // Rosegold
  public static final ResourceLocation ROSEGOLD_TEX = new ResourceLocation("microbots", "textures/entity/rosegold.png");
  public static final ModularRobitModel ROSEGOLD = new ModularRobitModelBuilder()
                                                   .add(new RosegoldTorso())
                                                   .add(new RosegoldLeftArm())
                                                   .add(new RosegoldLeftLeg())
                                                   .add(new RosegoldRightArm())
                                                   .add(new RosegoldRightLeg())
                                                   .build();

  // Electrum
  public static final ResourceLocation ELECTRUM_TEX = new ResourceLocation("microbots", "textures/entity/electrum.png");
  public static final ModularRobitModel ELECTRUM = new ModularRobitModelBuilder()
                                                   .add(new ElectrumTorso())
                                                   .add(new ElectrumLeftArm())
                                                   .add(new ElectrumLeftLeg())
                                                   .add(new ElectrumRightArm())
                                                   .add(new ElectrumRightLeg())
                                                   .build();

  // Brass
  public static final ResourceLocation BRASS_TEX = new ResourceLocation("microbots", "textures/entity/brass.png");
  public static final ModularRobitModel BRASS = new ModularRobitModelBuilder()
                                                .add(new BrassTorso())
                                                .add(new BrassLeftArm())
                                                .add(new BrassLeftLeg())
                                                .add(new BrassRightArm())
                                                .add(new BrassRightLeg())
                                                .build();

  // Bronze
  public static final ResourceLocation BRONZE_TEX = new ResourceLocation("microbots", "textures/entity/bronze.png");
  public static final ModularRobitModel BRONZE = new ModularRobitModelBuilder()
                                                 .add(new BronzeTorso())
                                                 .add(new BronzeLeftArm())
                                                 .add(new BronzeLeftLeg())
                                                 .add(new BronzeRightArm())
                                                 .add(new BronzeRightLeg())
                                                 .build();

  public static ModularRobitModel[] ALL = new ModularRobitModel[]{
  TUNGSTEN,
  STEEL,
  STARSTEEL,
  ROSEGOLD,
  ELECTRUM,
  BRASS,
  BRONZE
  };
  public static final String[] ALL_NAMES = new String[]{
  "tungsten",
  "steel",
  "starsteel",
  "rosegold",
  "electrum",
  "brass",
  "bronze"
  };

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