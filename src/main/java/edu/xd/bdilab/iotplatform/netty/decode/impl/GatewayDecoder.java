package edu.xd.bdilab.iotplatform.netty.decode.impl;


import edu.xd.bdilab.iotplatform.netty.decode.Decoder;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;

public class GatewayDecoder implements Decoder {

    @Override
    public void decode(byte[] bytes, String channelId) {
        System.out.println(StringUtil.getString(bytes));

    }
}
