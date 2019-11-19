package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName DeviceStateInfoMapper
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 20:02
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Mapper
public interface DeviceStateInfoMapper {
    DeviceStateInfo selectById(Integer id);
}
