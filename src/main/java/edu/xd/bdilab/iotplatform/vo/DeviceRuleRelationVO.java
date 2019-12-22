package edu.xd.bdilab.iotplatform.vo;

import lombok.Data;

/**
 * @ClassName DeviceRuleRelationVO
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/8 19:48
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
public class DeviceRuleRelationVO {
    /**
     * 设备id
     */
    private String fkDeviceId;
    /**
     * 规则id
     */
    private int fkRuleId;
    /**
     * 规则种类
     * 0-设备状态告警规则
     * 1-设备阈值告警规则
     */
    private int ruleClassification;

    public DeviceRuleRelationVO(String fkDeviceId,int fkRuleId,int ruleClassification){
        this.fkRuleId = fkRuleId;
        this.fkDeviceId = fkDeviceId;
        this.ruleClassification = ruleClassification;
    }
}
