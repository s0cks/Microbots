package microbots.common.tile;

import microbots.client.model.ModularRobitModel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public final class TileEntityAssembler
extends TileEntity{
  public enum State{
    CONVERGED,
    DIVERGED
  }

  private State state = State.CONVERGED;
  private ModularRobitModel model = null;

  public void swapState(){
    if(this.state == State.CONVERGED){
      this.state = State.DIVERGED;
    } else{
      this.state = State.CONVERGED;
    }
  }

  public ModularRobitModel getModel(){
    return this.model;
  }

  public void setModel(ModularRobitModel model){
    this.model = model;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    if(compound.hasKey("RobitModel")){
      this.model = ModularRobitModel.fromNBT(compound.getCompoundTag("RobitModel"));
      System.out.println("Loaded Model Compound");
      this.model.forEach((part) -> System.out.println(part.id()));
    }
    if(compound.hasKey("DivState")){
      this.state = State.valueOf(compound.getString("DivState").toUpperCase());
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    if(this.model != null){
      NBTTagCompound modelCompound = new NBTTagCompound();
      this.model.writeToNBT(modelCompound);
      compound.setTag("RobitModel", modelCompound);
    }
    compound.setString("DivState", this.state.toString());
  }

  public State state(){
    return this.state;
  }
}