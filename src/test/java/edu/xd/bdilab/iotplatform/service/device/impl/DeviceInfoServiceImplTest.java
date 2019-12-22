package edu.xd.bdilab.iotplatform.service.device.impl;

        import edu.xd.bdilab.iotplatform.dao.DeviceClassification;
        import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
        import edu.xd.bdilab.iotplatform.service.device.DeviceInfoService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoServiceImplTest {

    @Autowired
    DeviceInfoService deviceInfoService;
    @Test
    public void selectAllDeviceInfo() {
        System.out.println(deviceInfoService.selectAllDeviceInfo().size());
    }

    @Test
    public void selectByid(){
        System.out.println(deviceInfoService.selectById("3"));
    }

    @Test
    public void insert(){
        DeviceClassification deviceClassification = new DeviceClassification();
        deviceClassification.setFkDeviceId("3");
        deviceClassification.setFkCategoryId(9);
        System.out.println(deviceInfoService.insertDeviceClassification(deviceClassification));
    }
}