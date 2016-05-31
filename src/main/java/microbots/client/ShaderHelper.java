package microbots.client;

import microbots.common.Microbots;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import java.io.InputStream;

public final class ShaderHelper{
  public static int compile(String name){
    ResourceLocation fsh = new ResourceLocation("microbots", "shader/" + name + ".fsh");
    ResourceLocation vsh = new ResourceLocation("microbots", "shader/" + name + ".vsh");

    int program = 0;
    try{
      int vertex = createShader(vsh, ARBVertexShader.GL_VERTEX_SHADER_ARB);
      int frag = createShader(fsh, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
      program = ARBShaderObjects.glCreateProgramObjectARB();
      ARBShaderObjects.glAttachObjectARB(program, vertex);
      ARBShaderObjects.glAttachObjectARB(program, frag);
      ARBShaderObjects.glLinkProgramARB(program);
      ARBShaderObjects.glValidateProgramARB(program);
    } catch(Exception e){
      throw new RuntimeException(getLog(program), e);
    }
    return program;
  }

  public static int createShader(ResourceLocation code, int type){
    int shader = 0;
    try(InputStream in = Microbots.proxy.client().getResourceManager().getResource(code).getInputStream()){
      shader = ARBShaderObjects.glCreateShaderObjectARB(type);
      if(shader == 0) throw new IllegalStateException("Couldn't compile shader");

      ARBShaderObjects.glShaderSourceARB(shader, IOUtils.toString(in));
      ARBShaderObjects.glCompileShaderARB(shader);

      if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE){
        throw new IllegalStateException("Cannot compile shader: " + code.toString());
      }

      return shader;
    } catch(Exception e){
      throw new RuntimeException("Error Creating Shader (" + type + "): "+ getLog(shader), e);
    }
  }

  private static String getLog(int obj){
    return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
  }
}