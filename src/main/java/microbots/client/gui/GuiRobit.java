package microbots.client.gui;

import dorkbox.tweenengine.Timeline;
import dorkbox.tweenengine.Tween;
import dorkbox.tweenengine.TweenAccessor;
import dorkbox.tweenengine.TweenEquations;
import microbots.client.render.entity.RenderEntityRobit;
import microbots.common.Microbots;
import microbots.common.entity.EntityRobit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

public final class GuiRobit
extends GuiScreen
implements TweenAccessor<GuiRobit> {
  private static final byte ROTATION = 0x1;

  private final EntityRobit robit;

  private float rotation;
  private int guiLeft;
  private int guiTop;

  public GuiRobit(EntityRobit robit) {
    this.robit = robit;
    Timeline rotationTimeline = Timeline.createSequence()
                                        .push(Tween.to(this, ROTATION, this, 5)
                                                   .ease(TweenEquations.Linear)
                                                   .target(360.0F))
                                        .repeat(-1, 0.0F);
    rotationTimeline.start(Microbots.proxy.tweenManager());
  }

  @Override
  public void initGui() {
    super.initGui();
    this.guiLeft = ((this.width - (Microbots.proxy.client().displayWidth / 4)) / 2);
    this.guiTop = ((this.height - (Microbots.proxy.client().displayHeight / 4)) / 2);
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    this.drawDefaultBackground();
    this.renderRobit();

    String ret = robit.getClientRobit().line(0);

    this.fontRendererObj.drawString(ret, 100, 100, 0xFFFFFF);
  }

  private void renderRobit(){
    GlStateManager.pushMatrix();
    GlStateManager.enableColorMaterial();
    GlStateManager.pushMatrix();
    GlStateManager.translate(0.0F, 200.0F, 0.0F);
    {
      int y = (((this.height / 2) - 120) / 2);
      GlStateManager.translate(this.guiLeft + 10, this.guiTop + y, 50.0F);
    }
    GlStateManager.scale(-100.0F, 100.0F, 100.0F);
    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    RenderHelper.enableStandardItemLighting();
    GlStateManager.rotate(this.rotation, 0.0f, 1.0F, 0.0F);
    GlStateManager.translate(0.0F, 0.0F, 0.0F);
    ((RenderEntityRobit) Microbots.proxy.client()
                                        .getRenderManager()
                                        .getEntityRenderObject(this.robit)).doRenderWithDivergence(this.robit, 0.0, 0.0, 0.0, 0.0F);
    RenderHelper.disableStandardItemLighting();
    GlStateManager.popMatrix();
    GlStateManager.popMatrix();
  }

  @Override
  public int getValues(GuiRobit target, int tweenType, float[] returnValues) {
    switch (tweenType) {
      case ROTATION:
        returnValues[1] = this.rotation;
        return 1;
      default:
        return 0;
    }
  }

  @Override
  public void setValues(GuiRobit target, int tweenType, float[] newValues) {
    switch (tweenType) {
      case ROTATION:
        this.rotation = newValues[0];
        break;
      default:
        break;
    }
  }
}