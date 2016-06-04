package microbots.common.core.robit;

import io.github.s0cks.mscheme.Environment;
import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeParser;
import io.github.s0cks.mscheme.primitives.SchemeNull;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeSymbol;
import microbots.api.IRobit;
import microbots.common.Microbots;
import microbots.common.core.Keyboard;
import microbots.common.core.Terminal;
import microbots.common.core.robit.rproc.move;
import microbots.common.core.robit.rproc.set_color;
import microbots.common.core.robit.tproc.cls;
import microbots.common.core.robit.tproc.display;
import microbots.common.core.robit.tproc.get_cursor_pos_x;
import microbots.common.core.robit.tproc.get_cursor_pos_y;
import microbots.common.core.robit.tproc.set_cursor_pos;
import microbots.common.entity.EntityRobit;
import microbots.common.net.MicrobotsNetwork;
import microbots.common.net.PacketSyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;

public final class ServerRobit
implements IRobit {
  private final EntityRobit robit;
  private final Keyboard keyboard = new Keyboard(256);
  private final Terminal terminal = new Terminal(69, 13);
  private final Scheme scheme = new Scheme();
  private final Environment env = new Environment(SchemeNull.instance, SchemeNull.instance, Scheme.GLOBAL);
  private final String id;

  public ServerRobit(String id, EntityRobit robit){
    this.id = id;
    this.robit = robit;

    // Terminal Procedures
    this.env.define(new SchemeSymbol("display"), new display(this.terminal));
    this.env.define(new SchemeSymbol("set-cursor-pos"), new set_cursor_pos(this.terminal));
    this.env.define(new SchemeSymbol("cls"), new cls(this.terminal));
    this.env.define(new SchemeSymbol("get-cursor-pos-y"), new get_cursor_pos_y(this.terminal));
    this.env.define(new SchemeSymbol("get-cursor-pos-x"), new get_cursor_pos_x(this.terminal));

    // Robit Procedures
    this.env.define(new SchemeSymbol("set-color"), new set_color(this.robit));
    this.env.define(new SchemeSymbol("move"), new move(this.robit));
  }

  @Override
  public String id() {
    return this.id;
  }

  @Override
  public String line(int idx) {
    return null;
  }

  @Override
  public void onKeydown(int code, char c) {
    switch(code){
      case org.lwjgl.input.Keyboard.KEY_BACK:
        this.keyboard.take();
        break;
      case org.lwjgl.input.Keyboard.KEY_END:{
        // TODO: implement signal logic
        break;
      }
      default:{
        this.keyboard.put(c);
      }
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound compound) {
    this.terminal.writeToNBT(compound);
    this.keyboard.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
  }

  public void initialize(){
    try(InputStream in = Microbots.proxy.client().getResourceManager().getResource(new ResourceLocation("microbots", "init/core_os.scm")).getInputStream()){
      SchemeObject returned = this.scheme.eval(((new SchemeParser(in)).parse()), this.env);
      String ret = returned != null ? returned.toString() : "";
      this.terminal.write(ret);
      this.terminal.setCursorPos(this.terminal.getCursorX() + ret.length(), this.terminal.getCursorY());
    } catch(Exception e){
      this.terminal.clear();
      this.terminal.setCursorPos(1, 1);
      this.terminal.write(e.getMessage());
      e.printStackTrace(System.err);
    }
  }

  public void sync(){
    NBTTagCompound compound = new NBTTagCompound();
    this.writeToNBT(compound);
    MicrobotsNetwork.INSTANCE.sendToAll(new PacketSyncClient(this.id, compound));
  }
}