package microbots.client.model.robits.steel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitLeftLeg;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SteelRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class SteelLeftLeg
extends RobitLeftLeg{
  public ModelRenderer leftLeg;
  public ModelRenderer leftFoot;
  public ModelRenderer leftHeel;
  public ModelRenderer leftLGuard;

  public SteelLeftLeg() {
    super("steel_lleg");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftHeel = new ModelRenderer(this, 18, 0);
    this.leftHeel.setRotationPoint(-1.5F, 17.0F, 1.2F);
    this.leftHeel.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
    this.setRotateAngle(leftHeel, 0.08726646259971647F, 0.0F, 0.0F);
    this.leftFoot = new ModelRenderer(this, 8, 0);
    this.leftFoot.setRotationPoint(-1.5F, 21.0F, 0.0F);
    this.leftFoot.addBox(-1.0F, 0.0F, -2.0F, 2, 3, 3, 0.0F);
    this.leftLeg = new ModelRenderer(this, 0, 0);
    this.leftLeg.setRotationPoint(-0.5F, 17.5F, 0.0F);
    this.leftLeg.addBox(-2.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
    this.leftLGuard = new ModelRenderer(this, 24, 0);
    this.leftLGuard.setRotationPoint(-1.5F, 17.0F, 0.0F);
    this.leftLGuard.addBox(-1.0F, 0.0F, -1.0F, 1, 7, 2, 0.2F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STEEL_TEX);
    this.leftHeel.render(f5);
    this.leftFoot.render(f5);
    this.leftLeg.render(f5);
    this.leftLGuard.render(f5);
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
