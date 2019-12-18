package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoMapperTest {
    @Autowired
    DeviceInfoMapper deviceInfoMapper;
    @Test
    public void selectDeviceByName() {
        System.out.println(deviceInfoMapper.deleteByPrimaryKey("5"));
    }
    @Test
    public void selectAllDevice(){
        System.out.println(deviceInfoMapper.selectAllDevice());
    }

    @Test
    public void insert(){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId("5");
        deviceInfo.setGetwayId("test");
        deviceInfo.setFkProductId("2");
        deviceInfo.setDeviceName("test");
        deviceInfo.setCreateTime(new Date());
        deviceInfoMapper.insertSelective(deviceInfo);
    }
}