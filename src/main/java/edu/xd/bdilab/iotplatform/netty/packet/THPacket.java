package edu.xd.bdilab.iotplatform.netty.packet;

import static edu.xd.bdilab.iotplatform.netty.packet.command.Command.THSensor;

public class THPacket extends Packet{

    @Override
    public String getFlag() {
        return THSensor;
    }
}
