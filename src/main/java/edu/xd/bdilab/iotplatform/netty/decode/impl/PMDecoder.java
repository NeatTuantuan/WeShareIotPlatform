package edu.xd.bdilab.iotplatform.netty.decode.impl;


import com.alibaba.fastjson.JSONObject;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.netty.decode.Decoder;
import edu.xd.bdilab.iotplatform.netty.hbase.HbaseUtil;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class PMDecoder implements Decoder {
    @Autowired
    DeviceDataMapper deviceDataMapper;

    public static PMDecoder pmDecoder;

    @PostConstruct
    public void init(){
        pmDecoder = this;
        pmDecoder.deviceDataMapper = this.deviceDataMapper;
    }

    public void decode(byte[] bytes,String channelId){

        String data = DataUtil.encode(bytes);

        String dataStr = data.substring(6);
        int lenData = dataStr.length();

        String[] dataArry = new String[lenData];
        for (int i=0;i<lenData/2;i++){
            dataArry[i]=dataStr.substring(0,2);
            dataStr = dataStr.substring(2);
        }

        //co2[0,1] 计算方法 co2_H*256+co2_L
        String co2hStr = dataArry[0];
        String c02lStr = dataArry[1];
        int co2_H = Integer.parseInt(co2hStr,16);
        int co2_L = Integer.parseInt(c02lStr,16);
        int co2 = co2_H*256 +co2_L;

        logger.info("co2 "+co2);

        //TVOC[2,3]
        String tvochStr = dataArry[2];
        String tvoclStr = dataArry[3];
        int tvoc_H = Integer.parseInt(tvochStr,16);
        int tvoc_L = Integer.parseInt(tvoclStr,16);
        int TVOC = tvoc_H*256+tvoc_L;

        logger.info("TVOC "+TVOC);

        //ch2o[4,5]
        String ch2ohStr = dataArry[4];
        String ch2olStr = dataArry[5];
        int ch2o_H = Integer.parseInt(ch2ohStr,16);
        int ch2o_L = Integer.parseInt(ch2olStr,16);
        int CH2O = ch2o_H*256+ch2o_L;

        logger.info("CH2O "+CH2O);

        //PM2.5[6,7]
        String pmhStr = dataArry[6];
        String pmlStr = dataArry[7];
        int pm_H = Integer.parseInt(pmhStr,16);
        int pm_L = Integer.parseInt(pmlStr,16);
        int PM = pm_H*256+pm_L;

        logger.info("PM2.5 "+PM);

        //结果定义为json
        JSONObject json = new JSONObject();
        json.put("co2",co2);
        json.put("TVOC",TVOC);
        json.put("CH2O",CH2O);
        json.put("PM2.5",PM);
        String result = json.toString();

        //拿到网关
        RedisUtil redisUtil = new RedisUtil();
        String gate = redisUtil.get(channelId);
        //将网关转换
        byte[] gateWayBytes = DataUtil.deocde(gate);
        String gateWay = StringUtil.getString(gateWayBytes);

        if (gateWay!=null) {
//            //通过网关存储到hbase相关表
//            HbaseUtil hbaseUtil = new HbaseUtil();
//            List<Put> putList = new ArrayList<Put>();
//            Put put = new Put(Bytes.toBytes(getDate()));
//            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("source data"), Bytes.toBytes(data));
//            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("data"), Bytes.toBytes(result));
//            putList.add(put);
//            hbaseUtil.insert(gateWay, putList);

            //存储到mysql
            DeviceData deviceData = new DeviceData();
            deviceData.setGatewayId(gateWay);
            deviceData.setMetaData(data);
            deviceData.setFormatData(result);
            deviceData.setTimeStamp(DateUtil.getDate());
            int insertRes = pmDecoder.deviceDataMapper.insertSelective(deviceData);
            if (insertRes>0) {
                logger.info(gateWay + " 存入数据成功");
            }else {
                logger.info(gateWay +" 存入数据失败");
            }
        }else {
            System.out.println("网关不存在");
        }


    }

}
