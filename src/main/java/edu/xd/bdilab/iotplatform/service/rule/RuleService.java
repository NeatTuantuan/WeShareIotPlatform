package edu.xd.bdilab.iotplatform.service.rule;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;

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
     * @return
     */
    int deleteRules(int ruleId, int ruleClassification);

    //////////////////////////////////////////////////
    /**
     * 删除设备绑定规则
     * @param deviceRuleRelationVO
     * @return
     */
    int deleteByDeviceRuleVO(DeviceRuleRelationVO deviceRuleRelationVO);

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


    /**
     * 根据设备id查找关系
     * @param fkDeviceId
     * @return
     */
    List<DeviceRuleRelation> selectRelationByDeviceId(String fkDeviceId);

//    int updateRules();





}
