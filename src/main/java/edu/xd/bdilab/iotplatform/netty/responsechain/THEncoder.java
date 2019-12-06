package edu.xd.bdilab.iotplatform.netty.responsechain;




import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import edu.xd.bdilab.iotplatform.netty.packet.THPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class THEncoder extends MainEncoder {

    @Override
    public Packet encode(String data, ChannelHandlerContext ctx) {

        if (data.length() == 18) {
            THPacket thPacket = new THPacket();
            thPacket.setData(data);
            thPacket.setChannelId(ctx.channel().id().asShortText());
            return thPacket;
        } else if (getNext() != null) {

           return getNext().encode(data, ctx);
        } else {
            logger.info("THSensor数据错误");
            return null;
        }
    }



}
