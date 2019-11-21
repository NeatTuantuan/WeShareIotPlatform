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
    /**
     * 根据id查看状态设备
     * @param id
     * @return
     */
    DeviceStateInfo selectById(Integer id);

    /**
     * 根据设备id查看设备状态
     * @param fkDeviceId
     * @return
     */
    DeviceStateInfo selectByDeviceId(String fkDeviceId);

    /**
     * 插入设备状态
     * @param deviceStateInfo
     * @return
     */
    int insertDeviceState(DeviceStateInfo deviceStateInfo);

    /**
     * 根据设备id删除设备状态
     * @param fkDeviceId
     * @return
     */
    int deleteByFkDeviceId(String fkDeviceId);
}
