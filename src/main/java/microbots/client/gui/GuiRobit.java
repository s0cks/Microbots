package microbots.client.gui;

import microbots.common.Microbots;
import microbots.common.core.robit.ClientRobit;
import microbots.common.entity.EntityRobit;
import microbots.common.net.MicrobotsNetwork;
import microbots.common.net.PacketKeydown;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import shoes.client.ShoesTessellator;
import shoes.common.Shoes;
import truetyper.FontHelper;
import truetyper.TrueTypeFont;

import java.io.IOException;

public final class GuiRobit
extends GuiScreen {
  private static final ResourceLocation texture = new ResourceLocation("microbots", "textures/gui/display.png");
  private static final int xSize = 512;
  private static final int ySize = 512;
  private static final float scaleFactor = 0.45F;
  private static final float[] white = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

  private final EntityRobit robit;
  private final ClientRobit client;
  private int guiLeft;
  private int guiTop;

  public GuiRobit(EntityRobit robit) {
    this.robit = robit;
    this.client = robit.getClientRobit();
  }

  @Override
  public void initGui() {
    super.initGui();
    this.guiLeft = (int) (((this.width - (xSize * scaleFactor)) / (2 * scaleFactor)) - (xSize * 0.125F));
    this.guiTop = (int) (((this.height - (ySize * scaleFactor)) / (2 * scaleFactor)) - 10);
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode)
  throws IOException {
    switch (keyCode) {
      case Keyboard.KEY_ESCAPE:
        super.keyTyped(typedChar, keyCode);
        break;
      default: {
        MicrobotsNetwork.INSTANCE.sendToServer(new PacketKeydown(this.robit.getServerRobit()
                                                                           .id(), keyCode, typedChar));
      }
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    this.drawTerminal();
    this.drawText();
  }

  private void drawText() {
    GlStateManager.pushMatrix();
    TrueTypeFont droid = Microbots.injector.get(TrueTypeFont.class);
    int y = 0;
    for (int i = 0; i < 21; i++) {
      String line = this.client.line(i);
      FontHelper.drawString(line, (this.guiLeft + 23) * scaleFactor, ((this.guiTop + 28) * scaleFactor) + y, droid, 1.5F, 1.5F, white);
      if (i == this.client.getCursorY()) {
        FontHelper.drawString(
          this.client.getKeyboard(),
          ((this.guiLeft + 23) * scaleFactor + droid.getWidth(line.trim() + " ")),
          ((this.guiTop + 25) * scaleFactor + y),
          droid,
          1.5F, 1.5F,
          white
        );
      }
      y += (droid.getHeight() / 1.25F);
    }
    GlStateManager.popMatrix();
  }

  private void drawTerminal() {
    GlStateManager.pushMatrix();

    GlStateManager.pushMatrix();
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
    Microbots.proxy.client().renderEngine.bindTexture(texture);
    ShoesTessellator tess = Shoes.injector.get(ShoesTessellator.class);
    VertexBuffer vb = tess.getBuffer();
    vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
    vb.pos(this.guiLeft, this.guiTop, this.zLevel)
      .tex(0, 0)
      .endVertex();
    vb.pos(this.guiLeft, this.guiTop + (ySize * 1.5F), this.zLevel)
      .tex(0, 1)
      .endVertex();
    vb.pos(this.guiLeft + (xSize * 1.5F), this.guiTop + (ySize * 1.5F), this.zLevel)
      .tex(1, 1)
      .endVertex();
    vb.pos(this.guiLeft + (xSize * 1.5F), this.guiTop, this.zLevel)
      .tex(1, 0)
      .endVertex();
    tess.draw();
    GlStateManager.popMatrix();

    GlStateManager.pushMatrix();
    GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
    GlStateManager.disableTexture2D();
    vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
    vb.pos(this.guiLeft + 23, this.guiTop + 23, 1.0F)
      .color(17, 17, 17, 255)
      .endVertex();
    vb.pos(this.guiLeft + 23, this.guiTop + 23 + (ySize - 60), 1.0F)
      .color(17, 17, 17, 255)
      .endVertex();
    vb.pos(this.guiLeft + 23 + (xSize * 1.175F), this.guiTop + 23 + (ySize - 60), 1.0F)
      .color(17, 17, 17, 255)
      .endVertex();
    vb.pos(this.guiLeft + 23 + (xSize * 1.175F), this.guiTop + 23, 1.0F)
      .color(17, 17, 17, 255)
      .endVertex();
    tess.draw();
    GlStateManager.enableTexture2D();
    GlStateManager.popMatrix();

    GlStateManager.popMatrix();
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }
}