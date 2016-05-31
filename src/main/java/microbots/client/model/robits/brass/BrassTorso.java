package microbots.client.model.robits.brass;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitTorso;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BrassRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class BrassTorso
extends RobitTorso {
  public ModelRenderer torsoPelvis;
  public ModelRenderer torsoLower;
  public ModelRenderer torsoUpper;
  public ModelRenderer torsoSpine;
  public ModelRenderer torsoHead;

  public BrassTorso() {
    super("brass_torso");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.torsoLower = new ModelRenderer(this, 6, 8);
    this.torsoLower.setRotationPoint(0.0F, 14.9F, 0.0F);
    this.torsoLower.addBox(-2.0F, 0.0F, -1.0F, 4, 2, 2, 0.0F);
    this.setRotateAngle(torsoLower, 0.05235987755982988F, 0.0F, 0.0F);
    this.torsoHead = new ModelRenderer(this, 44, 8);
    this.torsoHead.setRotationPoint(0.0F, 11.4F, 0.0F);
    this.torsoHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
    this.setRotateAngle(torsoHead, 0.17453292519943295F, 0.0F, 0.0F);
    this.torsoPelvis = new ModelRenderer(this, 0, 8);
    this.torsoPelvis.setRotationPoint(0.0F, 16.8F, 0.0F);
    this.torsoPelvis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2, 0.0F);
    this.torsoSpine = new ModelRenderer(this, 34, 8);
    this.torsoSpine.setRotationPoint(0.0F, 10.8F, 1.3F);
    this.torsoSpine.addBox(-1.5F, 0.0F, -1.0F, 3, 6, 2, 0.0F);
    this.setRotateAngle(torsoSpine, -0.08726646259971647F, 0.0F, 0.0F);
    this.torsoUpper = new ModelRenderer(this, 18, 8);
    this.torsoUpper.setRotationPoint(0.0F, 11.4F, -0.5F);
    this.torsoUpper.addBox(-2.5F, 0.0F, -1.5F, 5, 4, 3, 0.0F);
    this.setRotateAngle(torsoUpper, 0.17453292519943295F, 0.0F, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.BRASS_TEX);
    this.torsoLower.render(f5);
    this.torsoHead.render(f5);
    this.torsoPelvis.render(f5);
    this.torsoSpine.render(f5);
    this.torsoUpper.render(f5);
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
