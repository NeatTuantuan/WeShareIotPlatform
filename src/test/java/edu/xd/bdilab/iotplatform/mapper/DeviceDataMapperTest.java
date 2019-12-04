package edu.xd.bdilab.iotplatform.mapper;

import com.alibaba.fastjson.JSON;
import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDataMapperTest {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Test
    public void selectById() throws JSONException {
      DeviceData deviceData = deviceDataMapper.selectById(21);
        JSONObject jsonObject = new JSONObject(deviceData.getFormatData());
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            System.out.println(key+" "+value);
            result.put(key, value);

        }


    }

    @Test
    @Transactional
    public void insert(){
        DeviceData deviceData = new DeviceData();
        deviceData.setGatewayId("test");
        deviceData.setMetaData("test");
        deviceData.setFormatData("test");
        deviceData.setTimeStamp(DateUtil.getDate());
        System.out.println(deviceDataMapper.insertSelective(deviceData));
    }

    @Test
    public void selectByTime(){
        Map<String,String> params = new HashMap<>();
        params.put("gatewayId","@@@864376049834723");
        params.put("beginTime ","2019-11-20 16:26:14");
        params.put("endTime","2019-11-20 16:26:28");


        List<DeviceData> list = deviceDataMapper.selectByTime(params);
        System.out.println(list.size());
    }

    @Test
    public void selectAll(){
        List<DeviceData> list = deviceDataMapper.selectAll("@@@864376049834723");
        System.out.println(list.size());
    }

    @Test
    public void selectRecent(){
        System.out.println(deviceDataMapper.selectRecent("@@@864376049834723"));
    }

    @Test
    public void selectCount(){
        System.out.println(deviceDataMapper.selectCount());
    }

    @Test
    public void selectByParams(){
        Map<String, Object> params = new HashMap<>();
        params.put(new String("gatewayId"),new String("@@@864376049817702"));
        params.put("beginTime","2019-11-26 20:01:53");
        params.put("endTime","2019-11-26 21:10:10");
        System.out.println(deviceDataMapper.selectByParams(params).size());
    }




}