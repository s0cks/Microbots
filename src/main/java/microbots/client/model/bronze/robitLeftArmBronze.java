package microbots.client.model.bronze;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BronzeRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class robitLeftArmBronze extends ModelBase {
    public ModelRenderer leftAShoulder;
    public ModelRenderer leftArm;

    public robitLeftArmBronze() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftArm = new ModelRenderer(this, 8, 14);
        this.leftArm.setRotationPoint(-3.0F, 13.0F, 0.0F);
        this.leftArm.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.leftAShoulder = new ModelRenderer(this, 0, 14);
        this.leftAShoulder.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.leftAShoulder.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leftArm.render(f5);
        this.leftAShoulder.render(f5);
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
