package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

public class DeviceDataServiceImplTest {


    @Test
    public void insertSelective() {
        DeviceData deviceData = new DeviceData();
        deviceData.setGatewayId("test");
        deviceData.setMetaData("test");
        deviceData.setFormatData("test");
        deviceData.setTimeStamp(DateUtil.getDate());

    }
}