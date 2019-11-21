package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.Packet.GatewayPacket;
import edu.xd.bdilab.iotplatform.netty.decode.DecoderFactory;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GatewayHandler extends SimpleChannelInboundHandler<GatewayPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, GatewayPacket gatewayPacket) throws Exception {
        DecoderFactory decoderFactory = new DecoderFactory();
        String getWay = gatewayPacket.getData();
        byte[] bytes = DataUtil.deocde(getWay);

        decoderFactory.getDecoder("GatewayHandler").decode(bytes,null);
//        GatewayDecoder.decode(bytes);
    }
}
