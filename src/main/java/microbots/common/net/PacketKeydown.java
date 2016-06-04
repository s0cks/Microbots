package microbots.common.net;

import io.netty.buffer.ByteBuf;
import microbots.common.Microbots;
import microbots.common.core.robit.ServerRobit;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public final class PacketKeydown
implements IMessage,
           IMessageHandler<PacketKeydown, IMessage>{
  private String id;
  private int code;
  private char key;

  public PacketKeydown(String id, int code, char key) {
    this.id = id;
    this.code = code;
    this.key = key;
  }

  public PacketKeydown(){}

  @Override
  public void fromBytes(ByteBuf buf) {
    this.code = buf.readInt();
    this.key = buf.readChar();
    this.id = ByteBufUtils.readUTF8String(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.code);
    buf.writeChar(this.key);
    ByteBufUtils.writeUTF8String(buf, this.id);
  }

  @Override
  public IMessage onMessage(PacketKeydown message, MessageContext ctx) {
    ServerRobit robit = Microbots.SERVER_REGISTRY.get(message.id);
    robit.onKeydown(message.code, message.key);
    return null;
  }
}
