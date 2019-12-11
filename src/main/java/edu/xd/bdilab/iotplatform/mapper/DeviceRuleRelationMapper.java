package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceRuleRelationMapper {
    /**
     * 插入
     * @param deviceRuleRelation
     * @return
     */
    int insertDeviceRuleRelation (DeviceRuleRelation deviceRuleRelation);

    /**
     * 查找
     * @return
     */
    List<DeviceRuleRelation> selectAllRelation();


    /**
     * 根据设备id查找关联关系
     * @param fkDeviceId
     * @return
     */
    List<DeviceRuleRelation> selectRelationByDeviceId(String fkDeviceId);


    /**
     * 根据设备id，规则分类和规则id删除规则
     * @param deviceRuleRelationVO
     * @return
     */
    int deleteByDeviceRuleVO(DeviceRuleRelationVO deviceRuleRelationVO);
}
