package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceStateRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName DeviceStateRuleMapper
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/6 10:02
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Mapper
@Component
public interface DeviceStateRuleMapper {
    /**
     * 插入规则
     * @param deviceStateRule
     * @return
     */
    int insertDeviceStateRule(DeviceStateRule deviceStateRule);

    /**
     * 查找所有规则
     * @return
     */
    List<DeviceStateRule> selectAllRule();

    /**
     * 修改规则
     * @param deviceStateRule
     * @return
     */
    int updateRule(DeviceStateRule deviceStateRule);

    /**
     * 删除规则
     * @param id
     * @return
     */
    int deleteById(int id);
}
