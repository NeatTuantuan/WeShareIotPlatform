package edu.xd.bdilab.iotplatform.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DeviceDataVO
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/25 20:34
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceDataVO {
    private String gatewayId;
    private String metaData;
    private String formatData;
    private String timeStamp;
}
