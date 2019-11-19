package edu.xd.bdilab.iotplatform.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DeviceVO
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 20:26
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceVO {
    /**
     * 设备id，主键
     */
    private String deviceId;

    /**
     * 产品id
     */
    private String fkProductId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 网关id
     */
    private String getwayId;
    /**
     * 设备状态（默认关闭）：
     * 1.开启
     * 0.关闭
     */
    private Byte deviceState;
}
