package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceCategory {
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

    //category
    private int id;

    private int mainTitle;

    private String subTitle;
}
