package edu.xd.bdilab.iotplatform.netty.packet;


import static edu.xd.bdilab.iotplatform.netty.packet.command.Command.PMSensor;

public class PMPacket extends Packet {

    @Override
    public String getFlag() {
        return PMSensor;
    }
}
