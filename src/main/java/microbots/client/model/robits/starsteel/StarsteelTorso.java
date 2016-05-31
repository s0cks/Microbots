package microbots.client.model.robits.starsteel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AuroranRobit - Tris
 * Created using Tabula 4.1.1
 */
public class StarsteelTorso extends ModelBase {
    public ModelRenderer torsoPelvis;
    public ModelRenderer torsoLower;
    public ModelRenderer torsoUpper;
    public ModelRenderer torsoSpine;
    public ModelRenderer torsoDecoration1;
    public ModelRenderer torsoDecoration1_1;
    public ModelRenderer torsoHead;

    public StarsteelTorso() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.torsoSpine = new ModelRenderer(this, 32, 9);
        this.torsoSpine.setRotationPoint(0.0F, 10.7F, 1.0F);
        this.torsoSpine.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 2, 0.0F);
        this.setRotateAngle(torsoSpine, -0.30839967882739805F, 0.0F, 0.0F);
        this.torsoHead = new ModelRenderer(this, 42, 9);
        this.torsoHead.setRotationPoint(0.0F, 11.3F, -0.4F);
        this.torsoHead.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(torsoHead, 0.0F, 0.7853981633974483F, 0.0F);
        this.torsoUpper = new ModelRenderer(this, 16, 9);
        this.torsoUpper.setRotationPoint(0.0F, 11.0F, -0.4F);
        this.torsoUpper.addBox(-2.5F, 0.0F, -1.5F, 5, 3, 3, 0.0F);
        this.setRotateAngle(torsoUpper, 0.17453292519943295F, 0.0F, 0.0F);
        this.torsoDecoration1_1 = new ModelRenderer(this, 38, 9);
        this.torsoDecoration1_1.setRotationPoint(1.5F, 13.4F, 1.5F);
        this.torsoDecoration1_1.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(torsoDecoration1_1, 0.17453292519943295F, 0.0F, 0.0F);
        this.torsoPelvis = new ModelRenderer(this, 0, 9);
        this.torsoPelvis.setRotationPoint(0.0F, 16.8F, 0.0F);
        this.torsoPelvis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2, 0.0F);
        this.torsoLower = new ModelRenderer(this, 6, 9);
        this.torsoLower.setRotationPoint(0.0F, 13.8F, 0.0F);
        this.torsoLower.addBox(-1.5F, 0.0F, -1.0F, 3, 3, 2, 0.0F);
        this.torsoDecoration1 = new ModelRenderer(this, 38, 9);
        this.torsoDecoration1.setRotationPoint(-1.5F, 13.4F, 1.5F);
        this.torsoDecoration1.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(torsoDecoration1, 0.17453292519943295F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.torsoSpine.render(f5);
        this.torsoHead.render(f5);
        this.torsoUpper.render(f5);
        this.torsoDecoration1_1.render(f5);
        this.torsoPelvis.render(f5);
        this.torsoLower.render(f5);
        this.torsoDecoration1.render(f5);
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
