package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName DeviceStateInfo
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 19:58
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceStateInfo {
    /**
     * 产品状态表id，唯一标识一条记录
     */
    private Integer id;
    /**
     * 设备id
     */
    private String fkDeviceId;
    /**
     * 设备状态（默认关闭）：
     * 1.开启
     * 0.关闭
     */
    private Byte deviceState;
}
