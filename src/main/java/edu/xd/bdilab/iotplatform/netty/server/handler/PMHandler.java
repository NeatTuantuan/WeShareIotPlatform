package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.Packet.PMPacket;
import edu.xd.bdilab.iotplatform.netty.decode.DecoderFactory;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PMHandler extends SimpleChannelInboundHandler<PMPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PMPacket pmPacket) throws Exception {
        DecoderFactory decoderFactory = new DecoderFactory();
        System.out.println("PMHandler");

        String data = pmPacket.getData();

        String channelId = pmPacket.getChannelId();

        byte[] bytes = DataUtil.deocde(data);

        decoderFactory.getDecoder("PMHandler").decode(bytes,channelId);

//        PMDecoder.decode(bytes,channelId);
    }
}
