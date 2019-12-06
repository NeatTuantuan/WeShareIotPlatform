package edu.xd.bdilab.iotplatform.dao.rule;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName DeviceThresholdRule
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/6 10:41
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceThresholdRule extends Rule{
    /**
     *id，自增，唯一标识一条记录
     */
    private int id;
    /**
     *规则类型：
     * 0-通用规则
     * 1-产品特定规则
     */
    private int ruleType;
    /**
     *产品id
     */
    private String fkProductId;
    /**
     *一般阈值
     */
    private int generalLevel;
    /**
     *较重阈值
     */
    private int heavyLevel;
    /**
     *严重阈值
     */
    private int seriousLevel;
}
