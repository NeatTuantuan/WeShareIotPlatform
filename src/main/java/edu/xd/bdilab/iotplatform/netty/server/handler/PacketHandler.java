package edu.xd.bdilab.iotplatform.netty.server.handler;

import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static edu.xd.bdilab.iotplatform.netty.packet.command.Command.*;


@ChannelHandler.Sharable
public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    public static final PacketHandler INSTACNE = new PacketHandler();

    private Map<String,SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private PacketHandler(){
        handlerMap = new HashMap<>();

        handlerMap.put(THSensor,THHandler.INSTANCE);
        handlerMap.put(PMSensor,PMHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getFlag()).channelRead(ctx,packet);
    }
}
