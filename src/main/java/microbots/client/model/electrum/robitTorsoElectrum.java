package microbots.client.model.electrum;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ElectrumRobit - Undefined
 * Created using Tabula 4.1.1
 */
public class robitTorsoElectrum extends ModelBase {
    public ModelRenderer torsoPelvis;
    public ModelRenderer torsoLower;
    public ModelRenderer torsoUpper;
    public ModelRenderer torsoBlade1;
    public ModelRenderer torsoBlade2;
    public ModelRenderer torsoHead;

    public robitTorsoElectrum() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.torsoHead = new ModelRenderer(this, 34, 9);
        this.torsoHead.setRotationPoint(0.0F, 11.3F, 0.0F);
        this.torsoHead.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(torsoHead, 0.0F, -0.7853981633974483F, 0.0F);
        this.torsoUpper = new ModelRenderer(this, 16, 9);
        this.torsoUpper.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.torsoUpper.addBox(-2.0F, 0.0F, -1.5F, 4, 3, 3, 0.0F);
        this.setRotateAngle(torsoUpper, 0.08726646259971647F, 0.0F, 0.0F);
        this.torsoLower = new ModelRenderer(this, 6, 9);
        this.torsoLower.setRotationPoint(0.0F, 13.8F, 0.0F);
        this.torsoLower.addBox(-1.5F, 0.0F, -1.0F, 3, 3, 2, 0.0F);
        this.torsoBlade2 = new ModelRenderer(this, 30, 9);
        this.torsoBlade2.setRotationPoint(0.8F, 10.8F, 1.5F);
        this.torsoBlade2.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(torsoBlade2, -0.16196655458507375F, 0.0F, 0.0F);
        this.torsoBlade1 = new ModelRenderer(this, 30, 9);
        this.torsoBlade1.setRotationPoint(-0.8F, 10.8F, 1.5F);
        this.torsoBlade1.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(torsoBlade1, -0.16196655458507375F, 0.0F, 0.0F);
        this.torsoPelvis = new ModelRenderer(this, 0, 9);
        this.torsoPelvis.setRotationPoint(0.0F, 16.8F, 0.0F);
        this.torsoPelvis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.torsoHead.render(f5);
        this.torsoUpper.render(f5);
        this.torsoLower.render(f5);
        this.torsoBlade2.render(f5);
        this.torsoBlade1.render(f5);
        this.torsoPelvis.render(f5);
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
