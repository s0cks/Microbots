package microbots.common.net;

import io.netty.buffer.ByteBuf;
import microbots.common.Microbots;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public final class PacketRequestSync
implements IMessage,
           IMessageHandler<PacketRequestSync, IMessage>{
  private String id;

  public PacketRequestSync(){}

  public PacketRequestSync(String id){
    this.id = id;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.id = ByteBufUtils.readUTF8String(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.id);
  }

  @Override
  public IMessage onMessage(PacketRequestSync message, MessageContext ctx) {
    if(!Microbots.SERVER_REGISTRY.contains(message.id)) return null;
    NBTTagCompound compound = new NBTTagCompound();
    Microbots.SERVER_REGISTRY.get(message.id).writeToNBT(compound);
    PacketSyncClient sync = new PacketSyncClient(message.id, compound);
    MicrobotsNetwork.INSTANCE.sendToAll(sync);
    return null;
  }
}