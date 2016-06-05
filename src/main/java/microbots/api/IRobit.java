package microbots.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IRobit{
  public String id();
  public String line(int idx);
  public void onKeydown(int code, char c);
  public void writeToNBT(NBTTagCompound compound);
  public void readFromNBT(NBTTagCompound compound);
  public void sendSignal(Signal signal);
  public Signal pollSignal();
}