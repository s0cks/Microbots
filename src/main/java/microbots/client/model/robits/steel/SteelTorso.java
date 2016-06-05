package microbots.client.model.robits.steel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitTorso;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SteelRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class SteelTorso
extends RobitTorso{
  public ModelRenderer torsoPelvis;
  public ModelRenderer torsoLower;
  public ModelRenderer torsoUpper;
  public ModelRenderer torsoHead;
  public ModelRenderer torsoSpine;

  public SteelTorso() {
    super("steel_torso");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.torsoPelvis = new ModelRenderer(this, 0, 9);
    this.torsoPelvis.setRotationPoint(0.0F, 16.7F, 0.0F);
    this.torsoPelvis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2, 0.0F);
    this.torsoLower = new ModelRenderer(this, 6, 9);
    this.torsoLower.setRotationPoint(0.0F, 13.7F, 0.0F);
    this.torsoLower.addBox(-1.5F, 0.0F, -1.0F, 3, 3, 2, 0.0F);
    this.torsoHead = new ModelRenderer(this, 32, 9);
    this.torsoHead.setRotationPoint(0.0F, 11.5F, 0.0F);
    this.torsoHead.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
    this.torsoSpine = new ModelRenderer(this, 40, 9);
    this.torsoSpine.setRotationPoint(0.0F, 11.1F, 1.3F);
    this.torsoSpine.addBox(-1.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
    this.setRotateAngle(torsoSpine, -0.17453292519943295F, 0.0F, 0.0F);
    this.torsoUpper = new ModelRenderer(this, 16, 9);
    this.torsoUpper.setRotationPoint(0.0F, 13.2F, 0.0F);
    this.torsoUpper.addBox(-2.5F, -2.0F, -1.5F, 5, 3, 3, 0.0F);
    this.setRotateAngle(torsoUpper, 0.08726646259971647F, 0.0F, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STEEL_TEX);
    this.torsoPelvis.render(f5);
    this.torsoLower.render(f5);
    this.torsoHead.render(f5);
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
