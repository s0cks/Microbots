package microbots.client.model.robits.brass;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitLeftArm;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BrassRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class BrassLeftArm
extends RobitLeftArm{
  public ModelRenderer leftAShoulder;
  public ModelRenderer leftArm;
  public ModelRenderer leftADecoration1;

  public BrassLeftArm() {
    super("brass_larm");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftADecoration1 = new ModelRenderer(this, 22, 15);
    this.leftADecoration1.setRotationPoint(-4.2F, 15.0F, 0.0F);
    this.leftADecoration1.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.2F);
    this.leftAShoulder = new ModelRenderer(this, 0, 15);
    this.leftAShoulder.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.leftAShoulder.addBox(-4.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
    this.leftArm = new ModelRenderer(this, 14, 15);
    this.leftArm.setRotationPoint(-4.2F, 13.5F, 0.0F);
    this.leftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.BRASS_TEX);
    this.leftADecoration1.render(f5);
    this.leftAShoulder.render(f5);
    this.leftArm.render(f5);
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
