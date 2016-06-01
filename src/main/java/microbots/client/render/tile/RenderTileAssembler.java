package microbots.client.render.tile;

import dorkbox.tweenengine.Timeline;
import dorkbox.tweenengine.Tween;
import dorkbox.tweenengine.TweenAccessor;
import dorkbox.tweenengine.TweenEquations;
import microbots.api.IRobitPart;
import microbots.client.ClientEventHandler;
import microbots.client.ShaderHelper;
import microbots.client.model.ModularRobitModel;
import microbots.common.Microbots;
import microbots.common.tile.TileEntityAssembler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.InputStream;
import java.io.InputStreamReader;

public final class RenderTileAssembler
extends TileEntitySpecialRenderer<TileEntityAssembler>
implements TweenAccessor<RenderTileAssembler> {
  private static final FaceBakery faceBarkery = new FaceBakery();

  private static IBakedModel create(String side, TextureAtlasSprite textureSprite) {
    try (InputStream in = Microbots.proxy.client()
                                         .getResourceManager()
                                         .getResource(new ResourceLocation("microbots", "models/block/robit_assembler_" + side + ".json"))
                                         .getInputStream()) {
      ModelBlock model = ModelBlock.deserialize(new InputStreamReader(in));
      SimpleBakedModel.Builder builder = new SimpleBakedModel.Builder(model, model.createOverrides()).setTexture(textureSprite);
      for (BlockPart part : model.getElements()) {
        for (EnumFacing facing : part.mapFaces.keySet()) {
          BlockPartFace face = part.mapFaces.get(facing);
          if (face.cullFace == null) {
            builder.addGeneralQuad(faceBarkery.makeBakedQuad(part.positionFrom, part.positionTo, face, textureSprite, facing, ModelRotation.X0_Y0, part.partRotation, false, part.shade));
          } else {
            builder.addFaceQuad(ModelRotation.X0_Y0.rotate(face.cullFace), faceBarkery.makeBakedQuad(part.positionFrom, part.positionTo, face, textureSprite, facing, ModelRotation.X0_Y0, part.partRotation, false, part.shade));
          }
        }
      }
      return builder.makeBakedModel();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static final IBakedModel top = create("top", ClientEventHandler.instance()
                                                                         .assemblerTop());
  private static final ResourceLocation topTexture = new ResourceLocation("microbots", "textures/models/assembler_top.png");
  private static final IBakedModel bottom = create("bottom", ClientEventHandler.instance()
                                                                               .assemblerBottom());
  private static final ResourceLocation bottomTexture = new ResourceLocation("microbots", "textures/models/assembler_bottom.png");
  private static final byte COLOR_R = 0x1;
  private static final byte COLOR_G = 0x2;
  private static final byte COLOR_B = 0x3;
  private static final byte LEVITATION = 0x4;
  private static final byte DIVERGENCE = 0x5;

  private float red = 0.0F;
  private float green = 0.31764705882F;
  private float blue = 0.31764705882F;
  private int pulsar = -1;
  private int accents = -1;
  private volatile TileEntityAssembler.State state = TileEntityAssembler.State.CONVERGED;
  private float levitation = 0.0F;
  private float divergence = 0.0F;

  private final Timeline levitationTimeline = Timeline.createSequence()
                                                      .push(Tween.to(this, LEVITATION, this, 3)
                                                                 .ease(TweenEquations.Quad_InOut)
                                                                 .target(0.5F))
                                                      .push(Tween.to(this, LEVITATION, this, 3)
                                                                 .ease(TweenEquations.Quad_InOut)
                                                                 .target(0.0F))
                                                      .repeatAutoReverse(-1, 0.5F);
  private final Timeline pulsarTimeline = Timeline.createParallel()
                                                  .push(Tween.to(this, COLOR_R, this, 3)
                                                             .ease(TweenEquations.Linear)
                                                             .target(0.19607843137F))
                                                  .push(Tween.to(this, COLOR_G, this, 3)
                                                             .ease(TweenEquations.Linear)
                                                             .target(1.0F))
                                                  .push(Tween.to(this, COLOR_B, this, 3)
                                                             .ease(TweenEquations.Linear)
                                                             .target(1.0F))
                                                  .repeatAutoReverse(-1, 0.0F);

  public RenderTileAssembler() {
    this.levitationTimeline.start(Microbots.proxy.tweenManager())
                           .pause();
    this.pulsarTimeline.start(Microbots.proxy.tweenManager())
                       .pause();
  }

  private Timeline createConvergenceTimeline() {
    return Timeline.createSequence()
                   .push(Tween.to(this, DIVERGENCE, this, 1)
                              .ease(TweenEquations.Linear)
                              .target(0.0F))
                   .setEndCallback((updatedObject -> {
                     this.state = TileEntityAssembler.State.CONVERGED;
                   }));
  }

  private Timeline createDivergenceTimeline() {
    return Timeline.createSequence()
                   .push(Tween.to(this, DIVERGENCE, this, 1)
                              .ease(TweenEquations.Linear)
                              .target(0.2F))
                   .setEndCallback(updatedObject -> {
                     state = TileEntityAssembler.State.DIVERGED;
                   });
  }

  @Override
  public void renderTileEntityAt(TileEntityAssembler te, double x, double y, double z, float partialTicks, int destroyStage) {
    if (this.levitationTimeline.isPaused()) {
      this.levitationTimeline.resume();
      this.pulsarTimeline.resume();
    }

    if (te.state() == TileEntityAssembler.State.CONVERGED && this.state != TileEntityAssembler.State.CONVERGED) {
      this.createConvergenceTimeline()
          .start(Microbots.proxy.tweenManager());
    } else if (te.state() == TileEntityAssembler.State.DIVERGED && this.state != TileEntityAssembler.State.DIVERGED) {
      this.createDivergenceTimeline()
          .start(Microbots.proxy.tweenManager());
    }

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);

    GlStateManager.disableLighting();
    this.bindPulsar();
    this.render(top, topTexture);
    this.render(bottom, bottomTexture);
    this.unbindPulsar();
    GlStateManager.enableLighting();

    if (te.getModel() != null) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.5F, -0.35F, 0.5F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, this.levitation, 0.0F);
      this.renderRobitModel(0.0F, 0.0F, 0.0F, te.getModel());
      GlStateManager.popMatrix();
    }

    GlStateManager.popMatrix();
  }

  private void render(IBakedModel model, ResourceLocation texture) {
    Microbots.proxy.client().renderEngine.bindTexture(texture);
    Microbots.proxy.client()
                   .getBlockRendererDispatcher()
                   .getBlockModelRenderer()
                   .renderModelBrightnessColor(model, 1.0F, 1.0F, 1.0F, 1.0F);
  }

  private void bindPulsar() {
    if (this.pulsar != -1) {
      ARBShaderObjects.glUseProgramObjectARB(this.pulsar);
    } else {
      this.pulsar = ShaderHelper.compile("assembler_pulsar");
      if (this.pulsar != -1) {
        ARBShaderObjects.glUseProgramObjectARB(this.pulsar);
      }
    }

    GL20.glUniform3f(GL20.glGetUniformLocation(this.pulsar, "color"), this.red, this.green, this.blue);
  }

  private void unbindPulsar() {
    if (this.pulsar != -1) {
      ARBShaderObjects.glUseProgramObjectARB(0);
    }
  }

  @Override
  public int getValues(RenderTileAssembler target, int tweenType, float[] returnValues) {
    switch (tweenType) {
      case COLOR_R:
        returnValues[0] = this.red;
        return 1;
      case COLOR_G:
        returnValues[0] = this.green;
        return 1;
      case COLOR_B:
        returnValues[0] = this.blue;
        return 1;
      case DIVERGENCE:
        returnValues[0] = this.divergence;
        return 1;
      case LEVITATION:
        returnValues[0] = this.levitation;
        return 1;
      default:
        return 0;
    }
  }

  @Override
  public void setValues(RenderTileAssembler target, int tweenType, float[] newValues) {
    switch (tweenType) {
      case COLOR_R:
        this.red = newValues[0];
        break;
      case COLOR_G:
        this.green = newValues[0];
        break;
      case COLOR_B:
        this.blue = newValues[0];
        break;
      case LEVITATION:
        this.levitation = newValues[0];
        break;
      case DIVERGENCE:
        this.divergence = newValues[0];
        break;
      default:
        break;
    }
  }

  private void renderRobitModel(double x, double y, double z, ModularRobitModel model) {
    GlStateManager.pushMatrix();
    GlStateManager.disableLighting();
    GlStateManager.shadeModel(GL11.GL_SMOOTH);
    this.bindAccents();

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y + 2.5F, z);
    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    for (IRobitPart part : model) {
      switch (part.type()) {
        case LEFT_ARM: {
          GlStateManager.translate(-divergence, 0.0F, 0.0F);
          part.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(divergence, 0.0F, 0.0F);
          break;
        }
        case RIGHT_ARM: {
          GlStateManager.translate(divergence, 0.0F, 0.0F);
          part.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(-divergence, 0.0F, 0.0F);
          break;
        }
        case LEFT_LEG: {
          GlStateManager.translate(0.0F, divergence, 0.0F);
          part.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(0.0F, -divergence, 0.0F);
          break;
        }
        case RIGHT_LEG: {
          GlStateManager.translate(0.0F, divergence, 0.0F);
          part.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
          GlStateManager.translate(0.0F, -divergence, 0.0F);
          break;
        }
        default: {
          part.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F / 16.0F);
        }
      }
    }
    GlStateManager.popMatrix();
    this.unbindAccents();
    GlStateManager.enableLighting();
    GlStateManager.popMatrix();
  }

  private void bindAccents() {
    if (this.accents != -1) {
      ARBShaderObjects.glUseProgramObjectARB(this.accents);
    } else {
      this.accents = ShaderHelper.compile("robit_accents");
      if (this.accents != -1) {
        ARBShaderObjects.glUseProgramObjectARB(this.accents);
      }
    }
    GL20.glUniform3f(GL20.glGetUniformLocation(this.accents, "accent"), this.red, this.green, this.blue);
  }

  private void unbindAccents() {
    if (this.accents != -1) {
      ARBShaderObjects.glUseProgramObjectARB(0);
    }
  }
}