package microbots.client.model;

import microbots.api.IRobitPart;
import microbots.api.MicrobotsApi;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class ModularRobitModel
implements Iterable<IRobitPart>{
  private final List<IRobitPart> parts;

  protected ModularRobitModel(List<IRobitPart> parts){
    this.parts = parts;
  }

  @Override
  public Iterator<IRobitPart> iterator() {
    return this.parts.iterator();
  }

  public static ModularRobitModel fromNBT(NBTTagCompound compound){
    ModularRobitModel model = new ModularRobitModel(new LinkedList<>());
    model.readFromNBT(compound);
    return model;
  }

  public void readFromNBT(NBTTagCompound compound){
    if(compound.hasKey("RobitParts")){
      this.parts.clear();
      NBTTagList parts = compound.getTagList("RobitParts", Constants.NBT.TAG_STRING);
      for(int i = 0; i < parts.tagCount(); i++){
        this.parts.add(MicrobotsApi.PART_MANAGER.getPart(parts.getStringTagAt(i)));
      }
    }
  }

  public void writeToNBT(NBTTagCompound compound){
    if(this.parts.size() > 0){
      NBTTagList parts = new NBTTagList();
      for(IRobitPart part : this){
        parts.appendTag(new NBTTagString(part.id()));
      }
      compound.setTag("RobitParts", parts);
    }
  }
}