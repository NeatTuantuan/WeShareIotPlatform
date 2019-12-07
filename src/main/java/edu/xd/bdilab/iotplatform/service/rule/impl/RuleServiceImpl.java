package edu.xd.bdilab.iotplatform.service.rule.impl;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceStateRule;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceThresholdRule;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateRuleMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceThresholdRuleMapper;
import edu.xd.bdilab.iotplatform.service.rule.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RuleServiceImpl
 * @Description TODO
 * @Author tuantuan
 * @Date 2019/12/6 下午10:57
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    DeviceThresholdRuleMapper deviceThresholdRuleMapper;
    @Autowired
    DeviceStateRuleMapper deviceStateRuleMapper;
    /**
     * 查看所有规则
     * @return
     */
    @Override
    public Map<String, Object> checkAllRules() {
        Map<String,Object> ruleMap = new HashMap<>();
        ruleMap.put("设备离线告警",deviceStateRuleMapper.selectAllRule());
        ruleMap.put("设备阈值告警",deviceThresholdRuleMapper.selectAllRule());
        return ruleMap;
    }

    /**
     * 添加规则
     * @param ruleMap
     * @return
     */
    @Override
    public int addRules(Map<String, Object> ruleMap){
        if (ruleMap.get("resultType") == new Integer(1)){
            return deviceStateRuleMapper.insertDeviceStateRule((DeviceStateRule) ruleMap.get("ruleInstance"));
        }else {
            return deviceThresholdRuleMapper.insertDeviceThresholdRule((DeviceThresholdRule) ruleMap.get("ruleInstance"))
        }
    }
}
