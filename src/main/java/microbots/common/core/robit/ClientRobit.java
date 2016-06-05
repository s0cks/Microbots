package microbots.common.core.robit;

import microbots.api.IRobit;
import microbots.api.Signal;
import microbots.common.core.Keyboard;
import microbots.common.core.Terminal;
import net.minecraft.nbt.NBTTagCompound;

public final class ClientRobit
implements IRobit {
  private final String id;
  private final Keyboard keyboard = new Keyboard(256);
  private final Terminal terminal = new Terminal();

  public ClientRobit(String id){
    this.id = id;
  }

  public int getCursorX(){
    return this.terminal.getCursorX();
  }

  public int getCursorY(){
    return this.terminal.getCursorY();
  }

  @Override
  public String id() {
    return this.id;
  }

  @Override
  public String line(int idx) {
    return this.terminal.getLine(idx);
  }

  @Override
  public void onKeydown(int code, char c) {

  }

  @Override
  public void writeToNBT(NBTTagCompound compound) {

  }

  public String getKeyboard(){
    return this.keyboard.toString();
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    this.terminal.readFromNBT(compound);
    this.keyboard.readFromNBT(compound);
  }

  @Override
  public void sendSignal(Signal signal) {

  }

  @Override
  public Signal pollSignal() {
    return null;
  }
}