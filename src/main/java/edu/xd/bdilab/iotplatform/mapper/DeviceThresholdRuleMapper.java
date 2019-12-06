package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.rule.DeviceThresholdRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Mapper
@Component
public interface DeviceThresholdRuleMapper {
    /**
     * 获取全部规则
     * @return
     */
    List<DeviceThresholdRule> selectAllRule();

    /**
     * 插入
     * @param deviceThresholdRule
     * @return
     */
    int insertDeviceThresholdRule(DeviceThresholdRule deviceThresholdRule);

    /**
     * 更新
     * @param deviceThresholdRule
     * @return
     */
    int updateRule(DeviceThresholdRule deviceThresholdRule);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(int id);
}
