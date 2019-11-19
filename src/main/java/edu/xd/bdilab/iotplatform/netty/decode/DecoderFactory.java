package edu.xd.bdilab.iotplatform.netty.decode;


import edu.xd.bdilab.iotplatform.netty.decode.impl.GatewayDecoder;
import edu.xd.bdilab.iotplatform.netty.decode.impl.PMDecoder;
import edu.xd.bdilab.iotplatform.netty.decode.impl.THDecoder;

/**
 * @ClassName DecoderFactory
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 10:18
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public class DecoderFactory {
    /**
     * 工厂方法，生产对应Decoder
     * @return
     */
   public Decoder getDecoder(String handlerType){
       if (handlerType == null){
           return null;
       }
       if (handlerType.equals("THHandler")){
           return new THDecoder();
       }else if (handlerType.equals("PMHandler")){
           return new PMDecoder();
       }else if (handlerType.equals("GatewayHandler")){
           return new GatewayDecoder();
       }
       return null;

   }
}
