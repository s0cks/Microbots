package microbots.common.core.robit;

import io.github.s0cks.mscheme.Environment;
import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeParser;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import microbots.api.IRobit;
import microbots.common.Microbots;
import microbots.common.core.Terminal;
import microbots.common.entity.EntityRobit;
import microbots.common.net.MicrobotsNetwork;
import microbots.common.net.PacketSyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;

public final class ServerRobit
implements IRobit {
  private final EntityRobit robit;
  private final Terminal terminal = new Terminal(69, 13);
  private final Scheme scheme = new Scheme();
  private final Environment env = new Environment();
  private final String id;

  public ServerRobit(String id, EntityRobit robit){
    this.id = id;
    this.robit = robit;
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

  }

  @Override
  public void writeToNBT(NBTTagCompound compound) {
    this.terminal.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
  }

  public void initialize(){
    try(InputStream in = Microbots.proxy.client().getResourceManager().getResource(new ResourceLocation("microbots", "init/test.scm")).getInputStream()){
      SchemeObject prog = (new SchemeParser(in)).parse();
      SchemeObject returned = this.scheme.eval(prog, this.env);
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