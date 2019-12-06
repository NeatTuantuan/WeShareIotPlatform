package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceStateRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceStateRuleMapperTest {

    @Autowired
    DeviceStateRuleMapper deviceStateRuleMapper;
    @Test
    public void insertDeviceStateRule() {
        DeviceStateRule deviceStateRule = new DeviceStateRule();
        deviceStateRule.setRuleType(0);
        deviceStateRule.setFkProductId("1");
        deviceStateRule.setOfflineThreshold(20);
        System.out.println(deviceStateRuleMapper.insertDeviceStateRule(deviceStateRule));
    }
    @Test
    public void selectAllRule() {
        System.out.println(deviceStateRuleMapper.selectAllRule());
    }

    @Test
    public void updateRule() {
        DeviceStateRule deviceStateRule = new DeviceStateRule();
        deviceStateRule.setId(1);
        deviceStateRule.setFkProductId("1");
        deviceStateRule.setOfflineThreshold(111);
        System.out.println(deviceStateRuleMapper.updateRule(deviceStateRule));
    }
}