package microbots.common.core.robit;

import io.github.s0cks.mscheme.Environment;
import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeParser;
import io.github.s0cks.mscheme.primitives.SchemeNull;
import io.github.s0cks.mscheme.primitives.SchemeSymbol;
import microbots.api.IFileSystem;
import microbots.api.IRobit;
import microbots.api.Signal;
import microbots.common.core.Keyboard;
import microbots.common.core.Terminal;
import microbots.common.core.fs.Ext9001FileSystem;
import microbots.common.core.robit.fproc.fclose;
import microbots.common.core.robit.fproc.fopen;
import microbots.common.core.robit.fproc.fread;
import microbots.common.core.robit.fproc.fwrite;
import microbots.common.core.robit.oproc.eval;
import microbots.common.core.robit.oproc.pause;
import microbots.common.core.robit.oproc.poll;
import microbots.common.core.robit.oproc.send;
import microbots.common.core.robit.rproc.move;
import microbots.common.core.robit.rproc.set_color;
import microbots.common.core.robit.tproc.cls;
import microbots.common.core.robit.tproc.display;
import microbots.common.core.robit.tproc.get_cursor_pos_x;
import microbots.common.core.robit.tproc.get_cursor_pos_y;
import microbots.common.core.robit.tproc.newline;
import microbots.common.core.robit.tproc.set_cursor_pos;
import microbots.common.entity.EntityRobit;
import microbots.common.net.MicrobotsNetwork;
import microbots.common.net.PacketSyncClient;
import net.minecraft.nbt.NBTTagCompound;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public final class ServerRobit
implements IRobit {
  private final EntityRobit robit;
  private final Keyboard keyboard = new Keyboard(256);
  private final Terminal terminal = new Terminal();
  private final IFileSystem fileSystem = new Ext9001FileSystem();
  private final Scheme scheme = new Scheme();
  private final Environment env = new Environment(SchemeNull.instance, SchemeNull.instance, Scheme.GLOBAL);
  private final String id;
  private final BlockingQueue<Signal> signals = new LinkedBlockingDeque<>();

  public ServerRobit(String id, EntityRobit robit){
    this.id = id;
    this.robit = robit;

    // Terminal Procedures
    this.env.define(new SchemeSymbol("display"), new display(this.terminal));
    this.env.define(new SchemeSymbol("set-cursor-pos"), new set_cursor_pos(this.terminal));
    this.env.define(new SchemeSymbol("cls"), new cls(this.terminal));
    this.env.define(new SchemeSymbol("get-cursor-pos-y"), new get_cursor_pos_y(this.terminal));
    this.env.define(new SchemeSymbol("get-cursor-pos-x"), new get_cursor_pos_x(this.terminal));
    this.env.define(new SchemeSymbol("newline"), new newline(this.terminal));

    // Robit Procedures
    this.env.define(new SchemeSymbol("set-color"), new set_color(this.robit));
    this.env.define(new SchemeSymbol("move"), new move(this.robit));

    // OS Procedures
    this.env.define(new SchemeSymbol("poll"), new poll(this));
    this.env.define(new SchemeSymbol("send"), new send(this));
    this.env.define(new SchemeSymbol("eval"), new eval(this.fileSystem, this.scheme, new Environment(SchemeNull.instance, SchemeNull.instance, this.env)));
    this.env.define(new SchemeSymbol("pause"), new pause());

    // FS Procedures
    this.env.define(new SchemeSymbol("fopen"), new fopen(this.fileSystem));
    this.env.define(new SchemeSymbol("fclose"), new fclose());
    this.env.define(new SchemeSymbol("fwrite"), new fwrite());
    this.env.define(new SchemeSymbol("fread"), new fread());
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
      case org.lwjgl.input.Keyboard.KEY_RETURN:{
        this.sendSignal(new Signal("send", this.keyboard.toString()));
        this.keyboard.reset();
        break;
      }
      case org.lwjgl.input.Keyboard.KEY_SPACE:{
        this.keyboard.put(' ' );
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

  @Override
  public void sendSignal(Signal signal) {
    this.signals.add(signal);
  }

  @Override
  public Signal pollSignal() {
    try {
      return this.signals.take();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  public void initialize(){
    try(InputStream in = this.fileSystem.openRead("/bin/core_os.scm")){
      this.terminal.clear();
      this.terminal.setCursorPos(1, 1);
      this.terminal.write("Loading core_os.scm");
      this.scheme.eval((new SchemeParser(in)).parse(), this.env);
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