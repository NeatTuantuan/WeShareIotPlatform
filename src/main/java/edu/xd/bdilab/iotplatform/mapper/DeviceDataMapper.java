package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName DeviceDataMapper
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 20:15
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Mapper
public interface DeviceDataMapper {
    DeviceData selectById(Integer id);
}
