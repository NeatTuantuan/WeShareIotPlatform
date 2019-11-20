package edu.xd.bdilab.iotplatform.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;

public class StringUtil {
//    public static ByteBuf String2ByteBuf(String s, ChannelHandlerContext ctx){
//        ByteBuf byteBuf = ctx.alloc().ioBuffer();
//        byte[] bytes = s.getBytes();
//        byteBuf.writeBytes(bytes);
//        return byteBuf;
//    }
////
////    public static String ByteBuf2String(ByteBuf byteBuf){
////        byte[] bytes = new byte[byteBuf.readableBytes()];
////        byteBuf.readBytes(bytes);
////        String res = new String(bytes);
////        return res;
////    }
public static String getString(byte[] bytes) {
    return getString(bytes, "UTF-8");
}

    public static String getString(byte[] bytes, String charsetName)
    {
        return new String(bytes, Charset.forName(charsetName));
    }

    public static void main(String[] args) {
        String s1 =   "404040383634333736303439383334373233";
        byte[] bytes = DataUtil.deocde(s1);

        System.out.println(getString(bytes));


    }
}
