package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class )
public class DeviceRuleRelationMapperTest {
    @Autowired
    DeviceRuleRelationMapper deviceRuleRelationMapper;
    @Test
    public void insertDeviceRuleRelation() {
        DeviceRuleRelation deviceRuleRelation = new DeviceRuleRelation();
        deviceRuleRelation.setFkDeviceId("1");
        deviceRuleRelation.setFkRuleId(1);
        deviceRuleRelation.setRuleClassification(0);
        deviceRuleRelationMapper.insertDeviceRuleRelation(deviceRuleRelation);
    }

    @Test
    public void selectAllRelation() {
        System.out.println(deviceRuleRelationMapper.selectAllRelation());
    }

    @Test
    public void selectRelationByDeviceId() {
        System.out.println(deviceRuleRelationMapper.selectRelationByDeviceId("1"));
    }

    @Test
    public void deleteByDeviceRuleVO() {
        DeviceRuleRelationVO deviceRuleRelationVO = new DeviceRuleRelationVO("123",2,43);
        System.out.println(deviceRuleRelationMapper.deleteByDeviceRuleVO(deviceRuleRelationVO));
    }




}