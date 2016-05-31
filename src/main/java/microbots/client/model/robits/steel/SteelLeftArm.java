package microbots.client.model.robits.steel;

import microbots.api.IRobitPart;
import microbots.client.model.DefaultRobitModels;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SteelRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class SteelLeftArm
extends ModelBase
implements IRobitPart {
  public ModelRenderer leftAShoulder;
  public ModelRenderer leftArm;
  public ModelRenderer leftHand;
  public ModelRenderer leftArm2;
  public ModelRenderer leftArm3;

  public SteelLeftArm() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftAShoulder = new ModelRenderer(this, 0, 16);
    this.leftAShoulder.setRotationPoint(-2.5F, 12.0F, 0.0F);
    this.leftAShoulder.addBox(-3.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.setRotateAngle(leftAShoulder, 0.7853981633974483F, 0.0F, 0.0F);
    this.leftArm3 = new ModelRenderer(this, 12, 16);
    this.leftArm3.setRotationPoint(-4.7F, 13.1F, -0.6F);
    this.leftArm3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
    this.leftArm2 = new ModelRenderer(this, 12, 16);
    this.leftArm2.setRotationPoint(-4.7F, 13.1F, 0.6F);
    this.leftArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
    this.leftHand = new ModelRenderer(this, 16, 16);
    this.leftHand.setRotationPoint(-4.0F, 17.5F, 0.0F);
    this.leftHand.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
    this.leftArm = new ModelRenderer(this, 12, 16);
    this.leftArm.setRotationPoint(-3.5F, 12.5F, 0.0F);
    this.leftArm.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
  }

  @Override
  public String id() {
    return "steel_larm";
  }

  @Override
  public Type type() {
    return Type.LEFT_ARM;
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STEEL_TEX);
    this.leftAShoulder.render(f5);
    this.leftArm3.render(f5);
    this.leftArm2.render(f5);
    this.leftHand.render(f5);
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
