package microbots.client.model.electrum;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ElectrumRobit - Undefined
 * Created using Tabula 4.1.1
 */
public class robitLeftLegElectrum extends ModelBase {
    public ModelRenderer leftLeg;
    public ModelRenderer leftLDecoration1;

    public robitLeftLegElectrum() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftLeg = new ModelRenderer(this, 0, 0);
        this.leftLeg.setRotationPoint(-0.5F, 17.5F, 0.0F);
        this.leftLeg.addBox(-2.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
        this.leftLDecoration1 = new ModelRenderer(this, 8, 0);
        this.leftLDecoration1.setRotationPoint(-1.0F, 17.5F, -0.2F);
        this.leftLDecoration1.addBox(-1.0F, -0.5F, -1.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(leftLDecoration1, -0.05235987755982988F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leftLeg.render(f5);
        this.leftLDecoration1.render(f5);
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
