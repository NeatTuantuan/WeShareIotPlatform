package edu.xd.bdilab.iotplatform.vo;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName DeviceReflectionVO
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/25 20:29
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Data
@ToString
public class DeviceReflectionVO {
    private DeviceInfo deviceInfo;
    private ProductInfo productInfo;
    private Byte deviceSate;
    private DeviceDataVO deviceDataVO;

}
