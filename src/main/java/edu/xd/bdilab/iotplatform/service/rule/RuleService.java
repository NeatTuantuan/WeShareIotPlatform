package edu.xd.bdilab.iotplatform.service.rule;

import edu.xd.bdilab.iotplatform.dao.rule.Rule;

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





}
