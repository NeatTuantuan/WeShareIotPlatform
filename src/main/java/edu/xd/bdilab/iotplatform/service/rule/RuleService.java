package edu.xd.bdilab.iotplatform.service.rule;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;

import java.util.List;
import java.util.Map;

public interface RuleService {
    /**
     * 查询所有规则
     */
    Map<String,Object> checkAllRules();

    /**
     * 添加规则
     */
    int addRules(Map<String, Object> ruleMap);

    /**
     * 删除规则
     * @param ruleMap
     * @return
     */
    int deleteRules(Map<String, Object> ruleMap);

    /**
     * 添加设备规则关系
     * @param deviceRuleRelation
     * @return
     */
    int addDeviceRuleRelation(DeviceRuleRelation deviceRuleRelation);

    /**
     * 查找所有设备规则关系
     * @return
     */
    List<DeviceRuleRelation> selectAllRelation();



//    int updateRules();





}
