package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceStateInfoMapperTest {

    @Autowired
    DeviceStateInfoMapper deviceStateInfoMapper;
    @Test
    public void selectById() {
        System.out.println(deviceStateInfoMapper.selectById(1));
    }
    @Test
    public void insertDeviceState(){
        DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
        deviceStateInfo.setFkDeviceId("asd");
//        deviceStateInfo.setDeviceState(new Integer(0).byteValue());
        System.out.println(deviceStateInfoMapper.insertDeviceState(deviceStateInfo));
    }

    @Test
    public void delete(){
        System.out.println(deviceStateInfoMapper.deleteByFkDeviceId("5"));
    }

    @Test
    public void update(){
        DeviceStateInfo deviceStateInfo = deviceStateInfoMapper.selectByDeviceId("1");
        deviceStateInfo.setDeviceState((byte) 1);
        deviceStateInfoMapper.updateDeviceState(deviceStateInfo);
    }
    @Test
    public void selectDeviceStateInfoByState(){
        System.out.println(deviceStateInfoMapper.selectDeviceStateInfoByState(1));
    }
}