package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDataMapperTest {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Test
    public void selectById() {



    }

    @Test
    @Transactional
    public void insert(){
        DeviceData deviceData = new DeviceData();
        deviceData.setGetwayId("test");
        deviceData.setMetaData("test");
        deviceData.setFormatData("test");
        deviceData.setTimeStamp(DateUtil.getDate());
        System.out.println(deviceDataMapper.insertSelective(deviceData));
    }
}