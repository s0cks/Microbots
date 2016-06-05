package microbots.client.model.robits.starsteel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitLeftLeg;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AuroranRobit - Tris
 * Created using Tabula 4.1.1
 */
public class StarsteelLeftLeg
extends RobitLeftLeg{
  public ModelRenderer leftLeg;
  public ModelRenderer leftFoot;
  public ModelRenderer leftToes;
  public ModelRenderer leftHeel;
  public ModelRenderer leftKnee;

  public StarsteelLeftLeg() {
    super("starsteel_lleg");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftFoot = new ModelRenderer(this, 8, 0);
    this.leftFoot.setRotationPoint(-1.5F, 20.0F, 0.0F);
    this.leftFoot.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.2F);
    this.leftKnee = new ModelRenderer(this, 26, 0);
    this.leftKnee.setRotationPoint(-1.5F, 18.0F, -0.4F);
    this.leftKnee.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1, 0.0F);
    this.leftLeg = new ModelRenderer(this, 0, 0);
    this.leftLeg.setRotationPoint(-0.5F, 17.5F, 0.0F);
    this.leftLeg.addBox(-2.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
    this.leftToes = new ModelRenderer(this, 16, 0);
    this.leftToes.setRotationPoint(-1.5F, 23.0F, -0.7F);
    this.leftToes.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
    this.leftHeel = new ModelRenderer(this, 22, 0);
    this.leftHeel.setRotationPoint(-1.5F, 20.0F, 0.6F);
    this.leftHeel.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STARSTEEL_TEX);
    this.leftFoot.render(f5);
    this.leftKnee.render(f5);
    this.leftLeg.render(f5);
    this.leftToes.render(f5);
    this.leftHeel.render(f5);
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
