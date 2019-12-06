package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceThresholdRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class )
public class DeviceThresholdRuleMapperTest {
    @Autowired
    DeviceThresholdRuleMapper deviceThresholdRuleMapper;

    @Test
    public void selectAllRule() {
        System.out.println(deviceThresholdRuleMapper.selectAllRule());
    }

    @Test
    public void insertDeviceThresholdRule() {
        DeviceThresholdRule deviceThresholdRule = new DeviceThresholdRule();
        deviceThresholdRule.setRuleType(1);
        deviceThresholdRule.setFkProductId("1");

        deviceThresholdRule.setGeneralLevel(1);
        deviceThresholdRule.setHeavyLevel(2);
        deviceThresholdRule.setSeriousLevel(3);

        deviceThresholdRuleMapper.insertDeviceThresholdRule(deviceThresholdRule);

    }

    @Test
    public void updateRule() {
        DeviceThresholdRule deviceThresholdRule = new DeviceThresholdRule();
        deviceThresholdRule.setFkProductId("2");
        deviceThresholdRuleMapper.updateRule(deviceThresholdRule);


    }

    @Test
    public void deleteById() {
    }
}