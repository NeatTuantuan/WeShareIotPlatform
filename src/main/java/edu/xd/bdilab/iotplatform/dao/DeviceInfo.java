package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class DeviceInfo {
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


}