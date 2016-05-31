package microbots.client.model.robits.electrum;

import microbots.client.model.DefaultRobitModels;
import microbots.client.model.robits.base.RobitRightArm;
import microbots.common.Microbots;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ElectrumRobit - Undefined
 * Created using Tabula 4.1.1
 */
public class ElectrumRightArm
extends RobitRightArm {
  public ModelRenderer rightAShoulder;
  public ModelRenderer rightShoulderLight;
  public ModelRenderer rightArm;
  public ModelRenderer rightArmLight;

  public ElectrumRightArm() {
    super("electrum_rarm");
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightShoulderLight = new ModelRenderer(this, 10, 15);
    this.rightShoulderLight.mirror = true;
    this.rightShoulderLight.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.rightShoulderLight.addBox(0.0F, -1.0F, -0.5F, 3, 1, 1, 0.2F);
    this.rightArmLight = new ModelRenderer(this, 25, 15);
    this.rightArmLight.mirror = true;
    this.rightArmLight.setRotationPoint(3.5F, 11.5F, 0.0F);
    this.rightArmLight.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
    this.rightArm = new ModelRenderer(this, 19, 15);
    this.rightArm.mirror = true;
    this.rightArm.setRotationPoint(3.3F, 12.0F, 0.0F);
    this.rightArm.addBox(-0.5F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
    this.rightAShoulder = new ModelRenderer(this, 0, 15);
    this.rightAShoulder.mirror = true;
    this.rightAShoulder.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.rightAShoulder.addBox(0.0F, -1.0F, -1.0F, 3, 1, 2, 0.0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    Microbots.proxy.client().renderEngine.bindTexture(DefaultRobitModels.ELECTRUM_TEX);
    this.rightShoulderLight.render(f5);
    this.rightArmLight.render(f5);
    this.rightArm.render(f5);
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
