package edu.xd.bdilab.iotplatform.netty.responsechain;

import edu.xd.bdilab.iotplatform.netty.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public abstract class MainEncoder {

    private MainEncoder next;
    public void setNext(MainEncoder next){
        this.next = next;
    }
    public MainEncoder getNext(){
        return next;
    }

    public abstract Packet encode(String data, ChannelHandlerContext ctx);
}
