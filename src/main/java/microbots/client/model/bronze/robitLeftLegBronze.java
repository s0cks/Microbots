package microbots.client.model.bronze;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * BronzeRobit - Tristaric
 * Created using Tabula 4.1.1
 */
public class robitLeftLegBronze extends ModelBase {
    public ModelRenderer leftLeg;
    public ModelRenderer leftLeg2;
    public ModelRenderer leftFoot;

    public robitLeftLegBronze() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftFoot = new ModelRenderer(this, 10, 0);
        this.leftFoot.setRotationPoint(-1.5F, 22.0F, 0.0F);
        this.leftFoot.addBox(-1.0F, 0.0F, -1.5F, 2, 2, 3, 0.0F);
        this.leftLeg2 = new ModelRenderer(this, 4, 0);
        this.leftLeg2.setRotationPoint(-1.0F, 16.5F, 0.0F);
        this.leftLeg2.addBox(-1.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 0);
        this.leftLeg.setRotationPoint(-0.5F, 17.5F, 0.0F);
        this.leftLeg.addBox(-1.0F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leftFoot.render(f5);
        this.leftLeg2.render(f5);
        this.leftLeg.render(f5);
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
