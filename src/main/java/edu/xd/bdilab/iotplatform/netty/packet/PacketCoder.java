package edu.xd.bdilab.iotplatform.netty.packet;


import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.responsechain.MainEncoder;
import edu.xd.bdilab.iotplatform.netty.responsechain.PMEncoder;
import edu.xd.bdilab.iotplatform.netty.responsechain.THEncoder;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PacketCoder {

    public Packet decode (ByteBuf byteBuf, ChannelHandlerContext ctx){

        //解析源数据
        int lenData = byteBuf.readableBytes();
        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String rawData = DataUtil.encode(dataBytes);

        //责任链
        MainEncoder thEncoder = new THEncoder();
        MainEncoder pmEncoder = new PMEncoder();


        thEncoder.setNext(pmEncoder);

        return  thEncoder.encode(rawData,ctx);

//        byteBuf.markReaderIndex();
//        int lenData = byteBuf.readableBytes();
//        byte[] dataBytes = new byte[lenData];
//        byteBuf.readBytes(dataBytes);
//        String rawData = DataUtil.encode(dataBytes);
//        byteBuf.resetReaderIndex();
//        System.out.println("源数据 ："+rawData);
//        System.out.println("len: "+rawData.length());
//
//            byteBuf.skipBytes(2);
//
//            //读取返回数据长度
//            byte[] lenBytes = new byte[1];
//            byteBuf.readBytes(lenBytes);
//            String lenStr = DataUtil.encode(lenBytes);
//            //得到数据个数
//            int len = Integer.parseInt(lenStr, 16) / 2;
//
//            byte[] bytes = new byte[byteBuf.readableBytes()];
//            byteBuf.readBytes(bytes);
//            String dataStr = DataUtil.encode(bytes);
//
//            if (len == 2) {
//                THPacket thPacket = new THPacket();
//                thPacket.setFlag("A");
//                thPacket.setData(dataStr);
//                thPacket.setChannelId(ctx.channel().id().asShortText());
//                return thPacket;
//            } else if (len == 7) {
//                PMPacket pmPacket = new PMPacket();
//                pmPacket.setFlag("B");
//                pmPacket.setData(dataStr);
//                pmPacket.setChannelId(ctx.channel().id().asShortText());
//                return pmPacket;
//            }

//            return null;

    }

    public void split (ByteBuf byteBuf, ChannelHandlerContext ctx) throws Exception{
        //定义数据库
        RedisUtil redisUtil = new RedisUtil();
//        HbaseUtil hbaseUtil = new HbaseUtil();

        int lenData = byteBuf.readableBytes();
        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String gateData = DataUtil.encode(dataBytes);
        logger.info("first receive gateway "+gateData+"channelId "+ctx.channel().id().asShortText());
        //将网关和channel id 存入redis
        if (gateData!=null) {
            redisUtil.set(ctx.channel().id().asShortText(),gateData);
            logger.info("网关存入redis");

        }else {
            logger.info("网关为空");
        }

    }


}
