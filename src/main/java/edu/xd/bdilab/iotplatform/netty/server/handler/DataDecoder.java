package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.packet.PacketCoder;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DataDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out)  {


        try {
            PacketCoder packetCoder = new PacketCoder();
            logger.info("服务端收到数据");


            int lenData = byteBuf.readableBytes();
            byte[] dataBytes = new byte[lenData];
            byteBuf.readBytes(dataBytes);
            String rawData = DataUtil.encode(dataBytes);

            logger.info("源数据 ：" +rawData);

            if (!rawData.startsWith("40")){
                byte[] bytes = DataUtil.deocde(rawData);
                byteBuf.writeBytes(bytes);
                out.add(packetCoder.encode(byteBuf,ctx));
            }else {
                byte[] bytes = DataUtil.deocde(rawData);
                byteBuf.writeBytes(bytes);
                packetCoder.split(byteBuf,ctx);
                logger.info("收到网关.....");
            }


        }catch (NullPointerException e){

            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
