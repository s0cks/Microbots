package microbots.client.model.robits.tungsten;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitLeftArm;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class TungstenLeftArm
extends RobitLeftArm {
  private final ModelRenderer leftAShoulder;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftADecoration1;
  private final ModelRenderer leftADecoration2;

  public TungstenLeftArm() {
    super("tungsten_larm");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftADecoration1 = new ModelRenderer(this, 22, 18);
    this.leftADecoration1.setRotationPoint(-4.0F, 16.0F, 0.0F);
    this.leftADecoration1.addBox(-1.0F, 0.0F, -1.0F, 1, 2, 2, 0.2F);
    this.leftADecoration2 = new ModelRenderer(this, 28, 18);
    this.leftADecoration2.setRotationPoint(-4.0F, 14.0F, 0.0F);
    this.leftADecoration2.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.2F);
    this.leftAShoulder = new ModelRenderer(this, 0, 18);
    this.leftAShoulder.setRotationPoint(-1.3F, 12.0F, 0.0F);
    this.leftAShoulder.addBox(-4.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
    this.leftArm = new ModelRenderer(this, 14, 18);
    this.leftArm.setRotationPoint(-4.0F, 12.5F, 0.0F);
    this.leftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.TUNGSTEN_TEX);
    this.leftADecoration1.render(f5);
    this.leftADecoration2.render(f5);
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