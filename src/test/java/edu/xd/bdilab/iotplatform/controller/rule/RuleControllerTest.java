package edu.xd.bdilab.iotplatform.controller.rule;

import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class )
public class RuleControllerTest {

    @Autowired
    RuleController ruleController;
    @Test
    public void getAllRule() {
        Map<String,Object> map = new HashMap<>();
    }

    @Test
    public void addRules() {
    }

    @Test
    public void deleteRules() {
    }

//    @Test
//    public void addRulesToDevice() {
//        List<DeviceRuleRelationVO> list = new ArrayList<>();
//        DeviceRuleRelationVO deviceRuleRelationVO = new DeviceRuleRelationVO();
//        deviceRuleRelationVO.setFkRuleId(1);
//        deviceRuleRelationVO.setFkDeviceId("1");
//        deviceRuleRelationVO.setRuleClassification(1);
//        list.add(deviceRuleRelationVO);
//        System.out.println(ruleController.addRulesToDevice(list));
//    }

    @Test
    public void getAllDeviceRuleRelation() {
    }
}