package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName SwitchLog
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/4 19:32
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class SwitchLog {
    /**
     * 开关日志id
     */
    private int id;
    /**
     * 设备id
     */
    private String fkDeviceId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
}
