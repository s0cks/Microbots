package microbots.client.render.entity;

import microbots.api.IRobitPart;
import microbots.client.ShaderHelper;
import microbots.common.entity.EntityRobit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public final class RenderEntityRobit
extends Render<EntityRobit> {
  private int accents = -1;

  public RenderEntityRobit(RenderManager renderManagerIn){
    super(renderManagerIn);
  }

  public void doRenderWithDivergence(EntityRobit entity, double x, double y, double z, float divergence){
    GlStateManager.pushMatrix();
    GlStateManager.disableLighting();
    GlStateManager.shadeModel(GL11.GL_SMOOTH);
    this.bindAccents(entity.getDataManager().get(EntityRobit.COLOR));

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y + 1.5F, z);
    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

    switch(entity.getFacing().getOpposite()){
      case WEST: GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F); break;
      case EAST: GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F); break;
      case NORTH: GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F); break;
      case SOUTH: GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F); break;
    }

    for(IRobitPart part : entity.getModel()){
      switch(part.type()){
        case LEFT_ARM:{
          GlStateManager.translate(-divergence, 0.0F, 0.0F);
          part.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(divergence, 0.0F, 0.0F);
          break;
        }
        case RIGHT_ARM:{
          GlStateManager.translate(divergence, 0.0F, 0.0F);
          part.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(-divergence, 0.0F, 0.0F);
          break;
        }
        case LEFT_LEG:{
          GlStateManager.translate(0.0F, divergence, 0.0F);
          part.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(0.0F, -divergence, 0.0F);
          break;
        }
        case RIGHT_LEG:{
          GlStateManager.translate(0.0F, divergence, 0.0F);
          part.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(0.0F, -divergence, 0.0F);
          break;
        }
        default: {
          part.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
        }
      }
    }
    GlStateManager.popMatrix();
    this.unbindAccents();
    GlStateManager.enableLighting();
    GlStateManager.popMatrix();
  }

  private void bindAccents(int c){
    if(this.accents != -1){
      ARBShaderObjects.glUseProgramObjectARB(this.accents);
    } else{
      this.accents = ShaderHelper.compile("robit_accents");
      if(this.accents != -1){
        ARBShaderObjects.glUseProgramObjectARB(this.accents);
      }
    }

    float r = (c & 0xFF0000) >> 16;
    float g = (c & 0xFF00) >> 8;
    float b = (c & 0xFF);
    GL20.glUniform3f(GL20.glGetUniformLocation(this.accents, "accent"), r / 255, g / 255, b / 255);
  }

  private void unbindAccents(){
    if(this.accents != -1){
      ARBShaderObjects.glUseProgramObjectARB(0);
    }
  }

  @Override
  public void doRender(EntityRobit entity, double x, double y, double z, float entityYaw, float partialTicks) {
    this.doRenderWithDivergence(entity, x, y, z, 0.0F);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityRobit entity) {
    return null;
  }
}