package edu.xd.bdilab.iotplatform.dao.rule;

import lombok.Data;

/**
 * @ClassName DeviceRuleRelation
 * @Description TODO
 * @Author tuantuan
 * @Date 2019/12/6 下午10:39
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
@Data
public class DeviceRuleRelation {
    /**
     * 主键，id
     */
    private int id;
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
