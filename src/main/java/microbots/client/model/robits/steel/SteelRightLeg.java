package microbots.client.model.robits.steel;

import microbots.api.IRobitPart;
import microbots.client.model.DefaultRobitModels;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SteelRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class SteelRightLeg
extends ModelBase
implements IRobitPart {
  public ModelRenderer rightLeg;
  public ModelRenderer rightFoot;
  public ModelRenderer rightHeel;
  public ModelRenderer rightLGuard;

  public SteelRightLeg() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightHeel = new ModelRenderer(this, 18, 0);
    this.rightHeel.setRotationPoint(1.5F, 17.0F, 1.2F);
    this.rightHeel.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
    this.setRotateAngle(rightHeel, 0.08726646259971647F, 0.0F, 0.0F);
    this.rightLeg = new ModelRenderer(this, 0, 0);
    this.rightLeg.setRotationPoint(0.5F, 17.5F, 0.0F);
    this.rightLeg.addBox(0.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
    this.rightLGuard = new ModelRenderer(this, 24, 0);
    this.rightLGuard.setRotationPoint(1.5F, 17.0F, 0.0F);
    this.rightLGuard.addBox(0.0F, 0.0F, -1.0F, 1, 7, 2, 0.2F);
    this.rightFoot = new ModelRenderer(this, 8, 0);
    this.rightFoot.setRotationPoint(1.5F, 21.0F, 0.0F);
    this.rightFoot.addBox(-1.0F, 0.0F, -2.0F, 2, 3, 3, 0.0F);
  }

  @Override
  public String id() {
    return "steel_rleg";
  }

  @Override
  public Type type() {
    return Type.RIGHT_LEG;
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STEEL_TEX);
    this.rightHeel.render(f5);
    this.rightLeg.render(f5);
    this.rightLGuard.render(f5);
    this.rightFoot.render(f5);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
