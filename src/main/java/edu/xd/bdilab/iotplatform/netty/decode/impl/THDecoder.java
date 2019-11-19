package edu.xd.bdilab.iotplatform.netty.decode.impl;


import com.alibaba.fastjson.JSONObject;
import edu.xd.bdilab.iotplatform.netty.decode.Decoder;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class THDecoder implements Decoder {


    @Override
    public void decode(byte[] bytes, String channelId) {
        RedisUtil redisUtil = new RedisUtil();

        //二进制->十六进制
        String data = DataUtil.encode(bytes);
        String dataStr = data.substring(6);

        DecimalFormat df=new DecimalFormat("0.0");//设置保留位数

        //温度
        String temperaturStr = dataStr.substring(0,4);
        int temperaturInt = Integer.parseInt(temperaturStr,16);
        String temperatur = df.format((float)temperaturInt/10);
        //湿度
        String humilityStr = dataStr.substring(4,8);
        int humilityInt = Integer.parseInt(humilityStr,16);
        String humility = df.format((float)humilityInt/10);

        System.out.println("温度 "+temperatur);
        System.out.println("湿度 "+humility);



        //将结果定义为json串
        JSONObject json = new JSONObject();
        json.put("温度",temperatur);
        json.put("湿度",humility);
        String result= json.toString();

        //通过id找到对应网关
        String gateWay = redisUtil.get(channelId);
        //通过网关存储到hbase相关表
//        if (gateWay!= null) {
//
//            HbaseUtil hbaseUtil = new HbaseUtil();
//            List<Put> putList = new ArrayList<Put>();
//            Put put = new Put(Bytes.toBytes(getDate()));
//            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("source data"), Bytes.toBytes(data));
//            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("data"), Bytes.toBytes(result));
//            putList.add(put);
//            hbaseUtil.insert(gateWay, putList);
//        }else {
//            System.out.println("网关不存在");
//        }
    }

    private static String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

}
