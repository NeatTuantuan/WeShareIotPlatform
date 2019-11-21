package edu.xd.bdilab.iotplatform.netty.decode;

/**
 * @InterfaceName Decoder
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 10:13
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public interface Decoder {
    /**
     * decode抽象方法
     * @param bytes
     */
    public void decode(byte[] bytes, String channelId);
}
