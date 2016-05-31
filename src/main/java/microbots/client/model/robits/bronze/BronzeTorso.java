package microbots.client.model.robits.bronze;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitTorso;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BronzeRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class BronzeTorso
extends RobitTorso{
  public ModelRenderer torsoSpine;
  public ModelRenderer torsoUpper;
  public ModelRenderer torsoHead;

  public BronzeTorso() {
    super("bronze_torso");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.torsoHead = new ModelRenderer(this, 20, 8);
    this.torsoHead.setRotationPoint(0.0F, 12.0F, 0.0F);
    this.torsoHead.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
    this.torsoSpine = new ModelRenderer(this, 0, 8);
    this.torsoSpine.setRotationPoint(0.0F, 14.5F, 0.0F);
    this.torsoSpine.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2, 0.0F);
    this.torsoUpper = new ModelRenderer(this, 6, 8);
    this.torsoUpper.setRotationPoint(0.0F, 11.5F, 0.0F);
    this.torsoUpper.addBox(-2.0F, 0.0F, -1.5F, 4, 3, 3, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.BRONZE_TEX);
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
