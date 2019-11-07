package edu.xd.bdilab.iotplatform.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}