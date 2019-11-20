package edu.xd.bdilab.iotplatform.netty.responsechain;




import edu.xd.bdilab.iotplatform.netty.Packet.Packet;
import edu.xd.bdilab.iotplatform.netty.Packet.THPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class THDecoder extends MainDecoder {

    @Override
    public Packet decode(String data, ChannelHandlerContext ctx) {

        if (data.length() == 18) {
            THPacket thPacket = new THPacket();
            thPacket.setFlag("A");
            thPacket.setData(data);
            thPacket.setChannelId(ctx.channel().id().asShortText());
            return thPacket;
        } else if (getNext() != null) {

           return getNext().decode(data, ctx);
        } else {
            logger.info("thPacket为空");
            return null;
        }
    }

}
