package edu.xd.bdilab.iotplatform.netty.util;



import java.nio.charset.Charset;
import java.util.HashMap;


import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.Map;

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

    public static Map JsonToMap(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;
    }

    public static void main(String[] args) {
        String s1 =   "404040383634333736303439383334373233";
        byte[] bytes = DataUtil.deocde(s1);

        System.out.println(getString(bytes));


    }
}
