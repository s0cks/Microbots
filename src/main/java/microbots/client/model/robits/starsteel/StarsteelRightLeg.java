package microbots.client.model.robits.starsteel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitRightLeg;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AuroranRobit - Tris
 * Created using Tabula 4.1.1
 */
public class StarsteelRightLeg
extends RobitRightLeg{
  public ModelRenderer rightLeg;
  public ModelRenderer rightFoot;
  public ModelRenderer rightToes;
  public ModelRenderer rightHeel;
  public ModelRenderer rightKnee;

  public StarsteelRightLeg() {
    super("starsteel_rleg");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightToes = new ModelRenderer(this, 16, 0);
    this.rightToes.mirror = true;
    this.rightToes.setRotationPoint(1.5F, 23.0F, -0.7F);
    this.rightToes.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
    this.rightKnee = new ModelRenderer(this, 26, 0);
    this.rightKnee.mirror = true;
    this.rightKnee.setRotationPoint(1.5F, 18.0F, -0.4F);
    this.rightKnee.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1, 0.0F);
    this.rightHeel = new ModelRenderer(this, 22, 0);
    this.rightHeel.mirror = true;
    this.rightHeel.setRotationPoint(1.5F, 20.0F, 0.6F);
    this.rightHeel.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
    this.rightFoot = new ModelRenderer(this, 8, 0);
    this.rightFoot.mirror = true;
    this.rightFoot.setRotationPoint(1.5F, 20.0F, 0.0F);
    this.rightFoot.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.2F);
    this.rightLeg = new ModelRenderer(this, 0, 0);
    this.rightLeg.mirror = true;
    this.rightLeg.setRotationPoint(0.5F, 17.5F, 0.0F);
    this.rightLeg.addBox(0.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STARSTEEL_TEX);
    this.rightToes.render(f5);
    this.rightKnee.render(f5);
    this.rightHeel.render(f5);
    this.rightFoot.render(f5);
    this.rightLeg.render(f5);
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
