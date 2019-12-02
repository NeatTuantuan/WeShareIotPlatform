package edu.xd.bdilab.iotplatform.netty.decode.impl;


import com.alibaba.fastjson.JSONObject;
import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.netty.decode.Decoder;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import edu.xd.bdilab.iotplatform.service.device.impl.DeviceDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class THDecoder implements Decoder {
    @Autowired
    private DeviceDataMapper deviceDataMapper;

    public static THDecoder thDecoder;

    @PostConstruct
    public void init(){
        thDecoder = this;
        thDecoder.deviceDataMapper = this.deviceDataMapper;
    }

    @Override
    public void decode(byte[] bytes, String channelId) {


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

        logger.info("温度 "+temperatur);
        logger.info("湿度 "+humility);



        //将结果定义为json串
        JSONObject json = new JSONObject();
        json.put("温度",temperatur);
        json.put("湿度",humility);
       String result= json.toString();

        //通过id找到对应网关
        RedisUtil redisUtil = new RedisUtil();
        String gate = redisUtil.get(channelId);
        logger.info("redis get gate "+gate);
        //网关数据转换
        byte[] gateBytes = DataUtil.deocde(gate);
        String gateWay = StringUtil.getString(gateBytes);
        logger.info("转换后的gateway "+gateWay);

        //存入数据库
        if (gateWay!=null){
            DeviceData deviceData = new DeviceData();
            deviceData.setGatewayId(gateWay);
            deviceData.setMetaData(data);
            deviceData.setFormatData(result);
            deviceData.setTimeStamp(DateUtil.getDate());
            int insertRes = thDecoder.deviceDataMapper.insertSelective(deviceData);
            if (insertRes>0){
                logger.info(gateWay+" 存入数据成功");
            }else {
                logger.info(gateWay+" 存入数据失败");
            }
        }else {
            logger.info("网关不存在");
        }
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


    public static void main(String[] args) {
        String s = "020304010A00CBA95A";
        byte[] bytes = DataUtil.deocde(s);
        String channelid = "4f353555";
        THDecoder thDecoder = new THDecoder();
        thDecoder.init();
        thDecoder.decode(bytes,channelid);

    }
}
