package edu.xd.bdilab.iotplatform.service.rule.impl;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceStateRule;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceThresholdRule;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.mapper.DeviceRuleRelationMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateRuleMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceThresholdRuleMapper;
import edu.xd.bdilab.iotplatform.service.rule.RuleService;
import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

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
    @Autowired
    DeviceRuleRelationMapper deviceRuleRelationMapper;
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
     * {
     *     "resultType":0/1
     *     "ruleInstance":DeviceThresholdRule/DeviceStateRule
     * }
     * @return
     */
    @Override
    public int addRules(Map<String, Object> ruleMap){
        HashMap hashMap = (HashMap) ruleMap.get("ruleInstance");
        if (ruleMap.get("resultType") .equals("1")){
            DeviceStateRule deviceStateRule = new DeviceStateRule();
            deviceStateRule.setRuleType((Integer) hashMap.get("ruleType"));
            deviceStateRule.setOfflineThreshold((Integer) hashMap.get("offlineThreshold"));

            return deviceStateRuleMapper.insertDeviceStateRule(deviceStateRule);
        }else {
            DeviceThresholdRule deviceThresholdRule = new DeviceThresholdRule();
            deviceThresholdRule.setFkProductId((String)hashMap.get("fkProductId"));
            deviceThresholdRule.setRuleType((Integer) hashMap.get("ruleType"));
            deviceThresholdRule.setGeneralLevel((Integer) hashMap.get("generalLevel"));
            deviceThresholdRule.setHeavyLevel((Integer) hashMap.get("heavyLevel"));
            deviceThresholdRule.setSeriousLevel((Integer) hashMap.get("seriousLevel"));

            return deviceThresholdRuleMapper.insertDeviceThresholdRule(deviceThresholdRule);
        }
    }

    @Override
    public int deleteRules(int ruleId, int ruleClassification) {
        if (ruleClassification == 0){
            return deviceStateRuleMapper.deleteById(ruleId);
        }else {
            return deviceThresholdRuleMapper.deleteById(ruleId);
        }
    }

    @Override
    public int deleteByDeviceRuleVO(DeviceRuleRelationVO deviceRuleRelationVO) {
        return deviceRuleRelationMapper.deleteByDeviceRuleVO(deviceRuleRelationVO);
    }


    @Override
    public int addDeviceRuleRelation(DeviceRuleRelation deviceRuleRelation) {
        return deviceRuleRelationMapper.insertDeviceRuleRelation(deviceRuleRelation);
    }

    @Override
    public List<DeviceRuleRelation> selectAllRelation() {
        return deviceRuleRelationMapper.selectAllRelation();
    }

    @Override
    public List<DeviceRuleRelation> selectRelationByDeviceId(String fkDeviceId) {
        return deviceRuleRelationMapper.selectRelationByDeviceId(fkDeviceId);
    }
}
