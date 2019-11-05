package edu.xd.bdilab.iotplatform.service;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;

import java.util.List;

public interface DeviceService {
    /**
     * 通过产品Id获取该产品下的所有设备
     * @param fkProductId
     * @return
     */
    List<DeviceInfo> getDeviceByProduct(String fkProductId);

    /**
     * 添加产品
     * @param deviceInfo
     * @return
     */
    int insertSelective(DeviceInfo deviceInfo);

    /**
     * 删除产品
     * @param deviceId
     * @return
     */
    int deleteByPrimaryKey(String deviceId);

    /**
     * 查询某一产品
     * @param deviceId
     * @return
     */
    DeviceInfo selectById(String deviceId);

    /**
     * 修改产品
     * @param deviceInfo
     * @return
     */
    int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 根据设备名称查找设备
     * @param name
     * @return
     */
    List<DeviceInfo> getDeviceByName(String name);

}
