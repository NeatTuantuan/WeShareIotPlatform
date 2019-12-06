package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

import java.util.Date;

/**
 * 设备信息和该设备对应的产品名称
 */

@Data
public class DeviceProductInfo {
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
     * 产品名称
     */
    private String productName;
    /**
     * 设备状态（默认关闭）：
     * 1.开启
     * 0.关闭
     */
    private Byte deviceState;
}
