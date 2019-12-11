package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceClassification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceClassificationMapperTest {


    @Autowired
    private DeviceClassificationMapper deviceClassificationMapper;

    @Test
    public void insertSelective() {
        DeviceClassification deviceClassification = new DeviceClassification();
        deviceClassification.setFkDeviceId("3");
        deviceClassification.setFkCategoryId(8);
        System.out.println(deviceClassificationMapper.insertSelective(deviceClassification));
    }

    @Test
    public void select() {
        System.out.println(deviceClassificationMapper.selectCategoryByDeviceId("4"));
    }

    @Test
    public void update(){
        DeviceClassification deviceClassification = deviceClassificationMapper.selectById(13);
        System.out.println(deviceClassification);
        deviceClassification.setFkCategoryId(15);
        System.out.println(deviceClassificationMapper.updateCategoryById(deviceClassification));
    }

    @Test
    public void delete(){
        System.out.println(deviceClassificationMapper.deleteDeviceCategory("3",16));
    }

}