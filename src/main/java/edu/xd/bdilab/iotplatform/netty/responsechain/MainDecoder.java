package edu.xd.bdilab.iotplatform.netty.responsechain;

import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public abstract class MainDecoder {

    private MainDecoder next;
    public void setNext(MainDecoder next){
        this.next = next;
    }
    public MainDecoder getNext(){
        return next;
    }

    public abstract Packet decode(String data, ChannelHandlerContext ctx);
}
