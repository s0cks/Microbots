package microbots.client.model.robits.starsteel;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitRightArm;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AuroranRobit - Tris
 * Created using Tabula 4.1.1
 */
public class StarsteelRightArm
extends RobitRightArm{
  public ModelRenderer rightAShoulder;
  public ModelRenderer rightArm;
  public ModelRenderer rightADecoration1;
  public ModelRenderer rightADecoration2;
  public ModelRenderer rightASpike;

  public StarsteelRightArm() {
    super("starsteel_rarm");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightADecoration2 = new ModelRenderer(this, 26, 15);
    this.rightADecoration2.mirror = true;
    this.rightADecoration2.setRotationPoint(3.8F, 13.3F, 0.4F);
    this.rightADecoration2.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
    this.rightArm = new ModelRenderer(this, 10, 15);
    this.rightArm.mirror = true;
    this.rightArm.setRotationPoint(3.8F, 12.5F, 0.0F);
    this.rightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.rightASpike = new ModelRenderer(this, 30, 17);
    this.rightASpike.mirror = true;
    this.rightASpike.setRotationPoint(4.5F, 15.4F, 0.0F);
    this.rightASpike.addBox(0.0F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
    this.setRotateAngle(rightASpike, 0.0F, 0.0F, 0.2090904443889207F);
    this.rightADecoration1 = new ModelRenderer(this, 18, 15);
    this.rightADecoration1.mirror = true;
    this.rightADecoration1.setRotationPoint(3.8F, 15.5F, 0.0F);
    this.rightADecoration1.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.2F);
    this.rightAShoulder = new ModelRenderer(this, 0, 15);
    this.rightAShoulder.mirror = true;
    this.rightAShoulder.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.rightAShoulder.addBox(0.0F, -1.5F, -1.0F, 3, 2, 2, 0.2F);
    this.setRotateAngle(rightAShoulder, 0.17453292519943295F, 0.0F, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.STARSTEEL_TEX);
    this.rightADecoration2.render(f5);
    this.rightArm.render(f5);
    this.rightASpike.render(f5);
    this.rightADecoration1.render(f5);
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
