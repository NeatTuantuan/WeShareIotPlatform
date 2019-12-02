package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DeviceData
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 20:11
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceData {
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
}
