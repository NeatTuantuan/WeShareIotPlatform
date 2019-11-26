package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.service.device.DeviceStateInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class )
public class DeviceStateInfoServiceImplTest {
    @Autowired
    DeviceStateInfoService deviceStateInfoService;

    @Test
    public void selectByDeviceId() {
    }

    @Test
    public void updateDeviceState() {
    }

    @Test
    public void selectDeviceStateInfoByState() {
        System.out.println(deviceStateInfoService.selectDeviceStateInfoByState(1));
    }
}