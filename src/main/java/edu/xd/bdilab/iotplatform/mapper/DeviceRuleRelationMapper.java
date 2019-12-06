package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceRuleRelationMapper {
    int insertDeviceRuleRelation (DeviceRuleRelation deviceRuleRelation);
}
