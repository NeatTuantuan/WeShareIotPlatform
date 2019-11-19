package edu.xd.bdilab.iotplatform.netty.server.handler;



import edu.xd.bdilab.iotplatform.netty.Packet.PacketCoder;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class DataDecoder extends ByteToMessageDecoder {



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out)  {


        try {
            PacketCoder packetCoder = new PacketCoder();
            System.out.println("服务端收到数据");

            int lenData = byteBuf.readableBytes();
            byte[] dataBytes = new byte[lenData];
            byteBuf.readBytes(dataBytes);
            String rawData = DataUtil.encode(dataBytes);

            System.out.println("rawData : "+rawData);

            if (!rawData.startsWith("40")){
                byte[] bytes = DataUtil.deocde(rawData);
                byteBuf.writeBytes(bytes);
                out.add(packetCoder.decode(byteBuf,ctx));
            }else {
                byte[] bytes = DataUtil.deocde(rawData);
                byteBuf.writeBytes(bytes);
                packetCoder.split(byteBuf,ctx);
                System.out.println("收到网关");
            }


//            out.add(PacketCoder.INSTANCE.decode(byteBuf,ctx));

        }catch (NullPointerException e){

            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
