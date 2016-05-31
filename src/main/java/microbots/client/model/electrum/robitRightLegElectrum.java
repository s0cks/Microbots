package microbots.client.model.electrum;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ElectrumRobit - Undefined
 * Created using Tabula 4.1.1
 */
public class robitRightLegElectrum extends ModelBase {
    public ModelRenderer rightLeg;
    public ModelRenderer rightLDecoration1;

    public robitRightLegElectrum() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rightLeg = new ModelRenderer(this, 0, 0);
        this.rightLeg.mirror = true;
        this.rightLeg.setRotationPoint(0.5F, 17.5F, 0.0F);
        this.rightLeg.addBox(0.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
        this.rightLDecoration1 = new ModelRenderer(this, 8, 0);
        this.rightLDecoration1.mirror = true;
        this.rightLDecoration1.setRotationPoint(1.0F, 17.5F, -0.2F);
        this.rightLDecoration1.addBox(0.0F, -0.5F, -1.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(rightLDecoration1, -0.05235987755982988F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rightLeg.render(f5);
        this.rightLDecoration1.render(f5);
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
