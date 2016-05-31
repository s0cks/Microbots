package microbots.client.model.electrum;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ElectrumRobit - Undefined
 * Created using Tabula 4.1.1
 */
public class robitLeftArmElectrum extends ModelBase {
    public ModelRenderer leftAShoulder;
    public ModelRenderer leftShoulderLight;
    public ModelRenderer leftArm;
    public ModelRenderer leftArmLight;

    public robitLeftArmElectrum() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftAShoulder = new ModelRenderer(this, 0, 15);
        this.leftAShoulder.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.leftAShoulder.addBox(-3.0F, -1.0F, -1.0F, 3, 1, 2, 0.0F);
        this.leftArmLight = new ModelRenderer(this, 25, 15);
        this.leftArmLight.setRotationPoint(-3.5F, 11.5F, 0.0F);
        this.leftArmLight.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.leftArm = new ModelRenderer(this, 19, 15);
        this.leftArm.setRotationPoint(-3.3F, 12.0F, 0.0F);
        this.leftArm.addBox(-0.5F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
        this.leftShoulderLight = new ModelRenderer(this, 10, 15);
        this.leftShoulderLight.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.leftShoulderLight.addBox(-3.0F, -1.0F, -0.5F, 3, 1, 1, 0.2F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leftAShoulder.render(f5);
        this.leftArmLight.render(f5);
        this.leftArm.render(f5);
        this.leftShoulderLight.render(f5);
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
