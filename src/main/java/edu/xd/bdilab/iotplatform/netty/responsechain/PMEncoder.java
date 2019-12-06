package edu.xd.bdilab.iotplatform.netty.responsechain;



import edu.xd.bdilab.iotplatform.netty.packet.PMPacket;
import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PMEncoder extends MainEncoder {
    @Override
    public Packet encode(String data, ChannelHandlerContext ctx) {

        if (data.length()==38){
            PMPacket pmPacket = new PMPacket();
                pmPacket.setData(data);
                pmPacket.setChannelId(ctx.channel().id().asShortText());
                return pmPacket;
        }else if (getNext() != null) {

            return getNext().encode(data, ctx);
        }else {
                logger.info("PMSensor数据错误");
                return null;
            }
    }
}
