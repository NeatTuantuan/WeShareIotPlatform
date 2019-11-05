package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

import java.util.Date;
@Data
public class ProductInfo {
    /**
     * 产品id，主键
     */
    private String productId;

    /**
     * 用户id
     */
    private Integer fkUserId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品型号
     */
    private String productType;

    /**
     * 产品厂商
     */
    private String productManufacturer;

    /**
     * 产品描述
     */
    private String productDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 产品类别
     * 0-智能城市
     * 1-智能生活
     * 2-智能工业
     * 3-边缘计算
     * 4-智能电力
     * 5-智能农业
     * 6-智慧建筑
     * 7-智能园区
     */
    private Byte productClassification;

}