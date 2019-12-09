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
     */
    private int ruleClassification;
}
