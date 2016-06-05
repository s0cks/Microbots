package microbots.client.model.robits.steel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitRightArm;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SteelRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class SteelRightArm
extends RobitRightArm {
  public ModelRenderer rightAShoulder;
  public ModelRenderer rightArm;
  public ModelRenderer rightHand;
  public ModelRenderer rightArm2;
  public ModelRenderer rightArm3;

  public SteelRightArm() {
    super("steel_rarm");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightArm2 = new ModelRenderer(this, 12, 16);
    this.rightArm2.mirror = true;
    this.rightArm2.setRotationPoint(4.7F, 13.1F, 0.6F);
    this.rightArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
    this.rightArm = new ModelRenderer(this, 12, 16);
    this.rightArm.mirror = true;
    this.rightArm.setRotationPoint(3.5F, 12.5F, 0.0F);
    this.rightArm.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
    this.rightHand = new ModelRenderer(this, 16, 16);
    this.rightHand.mirror = true;
    this.rightHand.setRotationPoint(4.0F, 17.5F, 0.0F);
    this.rightHand.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
    this.rightArm3 = new ModelRenderer(this, 12, 16);
    this.rightArm3.mirror = true;
    this.rightArm3.setRotationPoint(4.7F, 13.1F, -0.6F);
    this.rightArm3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
    this.rightAShoulder = new ModelRenderer(this, 0, 16);
    this.rightAShoulder.mirror = true;
    this.rightAShoulder.setRotationPoint(2.5F, 12.0F, 0.0F);
    this.rightAShoulder.addBox(0.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.setRotateAngle(rightAShoulder, 0.7853981633974483F, 0.0F, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STEEL_TEX);
    this.rightArm2.render(f5);
    this.rightArm.render(f5);
    this.rightHand.render(f5);
    this.rightArm3.render(f5);
    this.rightAShoulder.render(f5);
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
