package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.packet.PMPacket;
import edu.xd.bdilab.iotplatform.netty.decode.DecoderFactory;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PMHandler extends SimpleChannelInboundHandler<PMPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PMPacket pmPacket) throws Exception {
        DecoderFactory decoderFactory = new DecoderFactory();
        logger.info("PMHandler running......");

        String data = pmPacket.getData();

        String channelId = pmPacket.getChannelId();

        byte[] bytes = DataUtil.deocde(data);

        decoderFactory.getDecoder("PMHandler").decode(bytes,channelId);

//        PMDecoder.decode(bytes,channelId);
    }
}
