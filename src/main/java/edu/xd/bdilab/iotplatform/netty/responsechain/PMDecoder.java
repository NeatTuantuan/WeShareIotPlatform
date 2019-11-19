package edu.xd.bdilab.iotplatform.netty.responsechain;



import edu.xd.bdilab.iotplatform.netty.Packet.PMPacket;
import edu.xd.bdilab.iotplatform.netty.Packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public class PMDecoder extends MainDecoder {
    @Override
    public Packet decode(String data, ChannelHandlerContext ctx) {

        if (data.length()==38){
            PMPacket pmPacket = new PMPacket();
                pmPacket.setFlag("B");
                pmPacket.setData(data);
                pmPacket.setChannelId(ctx.channel().id().asShortText());
                return pmPacket;
        }else {
                System.out.println("pmpacket为空");
                return null;
            }
    }
}
