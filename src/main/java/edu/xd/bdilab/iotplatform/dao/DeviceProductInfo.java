package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

@Data
public class DeviceProductInfo {
    /**
     * id,自增，唯一标识一条记录
     */
    private Integer id;
    /**
     * 网关id
     */
    private String gatewayId;
    /**
     * 元数据
     */
    private String metaData;
    /**
     * 解析数据
     */
    private String formatData;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 产品名称
     */
    private String productName;
}
