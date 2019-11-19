package edu.xd.bdilab.iotplatform.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDataMapperTest {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Test
    public void selectById() {
        System.out.println(deviceDataMapper.selectById(1));
    }
}