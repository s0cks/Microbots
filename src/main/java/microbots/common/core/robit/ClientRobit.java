package microbots.common.core.robit;

import microbots.api.IRobit;
import microbots.common.core.Terminal;
import net.minecraft.nbt.NBTTagCompound;

public final class ClientRobit
implements IRobit {
  private final String id;
  private Terminal terminal = new Terminal(69, 13);

  public ClientRobit(String id){
    this.id = id;
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

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    this.terminal.readFromNBT(compound);
  }
}