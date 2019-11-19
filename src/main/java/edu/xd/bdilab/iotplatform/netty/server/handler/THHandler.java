package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.Packet.THPacket;
import edu.xd.bdilab.iotplatform.netty.decode.DecoderFactory;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class THHandler extends SimpleChannelInboundHandler<THPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, THPacket thPacket) throws Exception {
        System.out.println("THHandler");
        DecoderFactory decoderFactory = new DecoderFactory();

        String data = thPacket.getData();
        //温湿度
        String channelId = thPacket.getChannelId();

        byte[] thByteByte = DataUtil.deocde(data);


        decoderFactory.getDecoder("THHandler").decode(thByteByte,channelId);
//        THDecoder.decode(thByteByte,channelId);

    }
}