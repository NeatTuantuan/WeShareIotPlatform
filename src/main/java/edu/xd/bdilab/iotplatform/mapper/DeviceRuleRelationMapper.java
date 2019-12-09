package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
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
}
