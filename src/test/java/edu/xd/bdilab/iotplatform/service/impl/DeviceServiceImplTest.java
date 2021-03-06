package edu.xd.bdilab.iotplatform.service.impl;


import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class )
public class DeviceServiceImplTest {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Test
    public void getDeviceByProduct() {
        List<DeviceVO> deviceInfoList = deviceService.getDeviceByProduct("1");
        for (DeviceVO deviceInfo:deviceInfoList){
            System.out.println(deviceInfo.getDeviceName());
        }
    }

    @Test
    @Transactional
    public void insert(){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId("1");
        deviceInfo.setFkProductId("1");
        deviceInfo.setDeviceName("温湿度传感器");
        deviceInfo.setCreateTime(new Date());
        deviceInfo.setGetwayId("192.168.0.230");

        int res = deviceService.insertSelective(deviceInfo);
        System.out.println(res);

    }

    @Test
    @Transactional
    public void deleteByPrimaryKey() {
        int res = deviceService.deleteByPrimaryKey("1");
        Assert.assertEquals(res,1);
    }

    @Test
    public void selectById() {
        DeviceVO deviceVO = deviceService.selectById("3");
        System.out.println(deviceVO);
    }

    @Test
    public void updateDeviceInfo() {
//        DeviceInfo deviceInfo = deviceService.selectById("2");
//        deviceInfo.setGetwayId("192.168.0.131");
//        int res = deviceService.updateDeviceInfo(deviceInfo);
//        System.out.println(res);
//        Assert.assertEquals(1,res);
    }

    @Test
    public void selectByName(){
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectDeviceByName("检测仪");
        for (DeviceInfo deviceInfo:deviceInfos){
            System.out.println(deviceInfo.getDeviceId());
        }
    }

    @Test
    public void getDeviceByName(){
        List<DeviceVO> deviceInfos = deviceService.getDeviceByName("test");
        System.out.println(deviceInfos);
    }

    @Test
    public void deviceInfoStatistics(){
        System.out.println(deviceService.deviceInfoStatistics());
    }

}