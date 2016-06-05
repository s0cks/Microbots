package microbots.common.core;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;

public final class Keyboard{
  private final char[] buffer;
  private int capacity;
  private int write;
  private int available;

  public Keyboard(int cap){
    this.capacity = cap;
    this.buffer = new char[cap];
  }

  public void reset(){
    this.write = 0;
    this.available = 0;
    Arrays.fill(this.buffer, ((char) -1));
  }

  public int capacity(){
    return this.capacity;
  }

  public int available(){
    return this.available;
  }

  public int remaining(){
    return this.capacity - this.available;
  }

  public void put(char c){
    if(this.available < this.capacity){
      if(this.write >= this.capacity){
        this.write = 0;
      }
      this.buffer[this.write] = c;
      this.write++;
      this.available++;
    }
  }

  public char take(){
    if(this.available == 0) return ((char) -1);
    int next = this.write;
    this.write--;
    this.available--;
    return this.buffer[next];
  }

  @Override
  public String toString() {
    String str = "";
    if(this.available == 0) return "";
    for(int i = 0; i < this.available; i++){
      int next = (this.write - this.available) + i;
      str += this.buffer[next];
    }
    return str;
  }

  public void writeToNBT(NBTTagCompound compound){
    compound.setString("Keyboard", this.toString());
  }

  public void readFromNBT(NBTTagCompound compound){
    String str = compound.getString("Keyboard");
    this.reset();
    for(int i = 0; i < str.length(); i++){
      this.put(str.charAt(i));
    }
  }
}
