package edu.xd.bdilab.iotplatform.netty.responsechain;



import edu.xd.bdilab.iotplatform.netty.packet.PMPacket;
import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PMDecoder extends MainDecoder {
    @Override
    public Packet decode(String data, ChannelHandlerContext ctx) {

        if (data.length()==38){
            PMPacket pmPacket = new PMPacket();
                pmPacket.setFlag("B");
                pmPacket.setData(data);
                pmPacket.setChannelId(ctx.channel().id().asShortText());
                return pmPacket;
        }else if (getNext() != null) {

            return getNext().decode(data, ctx);
        }else {
                logger.info("pmpacket为空");
                return null;
            }
    }
}
