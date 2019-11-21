package edu.xd.bdilab.iotplatform.netty.Packet;

import lombok.Data;


@Data
public abstract class Packet {

    private String data;

    private String flag;

    private String channelId;


}
