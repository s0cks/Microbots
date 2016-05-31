package microbots.client.model.robits.bronze;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitRightLeg;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BronzeRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class BronzeRightLeg
extends RobitRightLeg{
  public ModelRenderer rightLeg;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightFoot;

  public BronzeRightLeg() {
    super("bronze_rleg");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightLeg2 = new ModelRenderer(this, 4, 0);
    this.rightLeg2.setRotationPoint(1.0F, 16.5F, 0.0F);
    this.rightLeg2.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
    this.rightFoot = new ModelRenderer(this, 10, 0);
    this.rightFoot.setRotationPoint(1.5F, 22.0F, 0.0F);
    this.rightFoot.addBox(-1.0F, 0.0F, -1.5F, 2, 2, 3, 0.0F);
    this.rightLeg = new ModelRenderer(this, 0, 0);
    this.rightLeg.setRotationPoint(0.5F, 17.5F, 0.0F);
    this.rightLeg.addBox(0.0F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.BRONZE_TEX);
    this.rightLeg2.render(f5);
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
