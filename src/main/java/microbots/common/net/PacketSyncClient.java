package microbots.common.net;

import io.netty.buffer.ByteBuf;
import microbots.common.Microbots;
import microbots.common.core.robit.ClientRobit;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public final class PacketSyncClient
implements IMessage,
           IMessageHandler<PacketSyncClient, IMessage>{
  private String id;
  private NBTTagCompound terminal;

  public PacketSyncClient(){}

  public PacketSyncClient(String id, NBTTagCompound terminal){
    this.id = id;
    this.terminal = terminal;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.id = ByteBufUtils.readUTF8String(buf);
    this.terminal = ByteBufUtils.readTag(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.id);
    ByteBufUtils.writeTag(buf, this.terminal);
  }

  @Override
  public IMessage onMessage(PacketSyncClient message, MessageContext ctx) {
    if(!Microbots.CLIENT_REGISTRY.contains(message.id)){
      System.out.println("[" + getClass().getSimpleName() + "] Creating client robit");
      Microbots.CLIENT_REGISTRY.register(new ClientRobit(message.id));
    }
    Microbots.CLIENT_REGISTRY.get(message.id).readFromNBT(message.terminal);
    return null;
  }
}