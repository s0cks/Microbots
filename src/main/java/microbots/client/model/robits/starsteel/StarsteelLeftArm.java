package microbots.client.model.robits.starsteel;

import microbots.api.IRobitPart;
import microbots.client.model.DefaultRobitModels;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AuroranRobit - Tris
 * Created using Tabula 4.1.1
 */
public class StarsteelLeftArm
extends ModelBase
implements IRobitPart {
  public ModelRenderer leftAShoulder;
  public ModelRenderer leftArm;
  public ModelRenderer leftADecoration1;
  public ModelRenderer leftADecoration2;
  public ModelRenderer leftASpike;

  public StarsteelLeftArm() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftADecoration1 = new ModelRenderer(this, 18, 15);
    this.leftADecoration1.setRotationPoint(-3.8F, 15.5F, 0.0F);
    this.leftADecoration1.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.2F);
    this.leftADecoration2 = new ModelRenderer(this, 26, 15);
    this.leftADecoration2.setRotationPoint(-3.8F, 13.3F, 0.4F);
    this.leftADecoration2.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
    this.leftArm = new ModelRenderer(this, 10, 15);
    this.leftArm.setRotationPoint(-3.8F, 12.5F, 0.0F);
    this.leftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.leftAShoulder = new ModelRenderer(this, 0, 15);
    this.leftAShoulder.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.leftAShoulder.addBox(-3.0F, -1.5F, -1.0F, 3, 2, 2, 0.2F);
    this.setRotateAngle(leftAShoulder, 0.17453292519943295F, 0.0F, 0.0F);
    this.leftASpike = new ModelRenderer(this, 30, 17);
    this.leftASpike.setRotationPoint(-4.5F, 15.4F, 0.0F);
    this.leftASpike.addBox(-1.0F, 0.0F, -1.0F, 1, 3, 2, -0.1F);
    this.setRotateAngle(leftASpike, 0.0F, 0.0F, -0.2090904443889207F);
  }

  @Override
  public String id() {
    return "starsteel_larm";
  }

  @Override
  public Type type() {
    return Type.LEFT_ARM;
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STARSTEEL_TEX);
    this.leftADecoration1.render(f5);
    this.leftADecoration2.render(f5);
    this.leftArm.render(f5);
    this.leftAShoulder.render(f5);
    this.leftASpike.render(f5);
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
