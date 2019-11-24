package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;

import java.util.List;

public interface DeviceService {
    /**
     * 通过产品Id获取该产品下的所有设备
     * @param fkProductId
     * @return
     */
    List<DeviceVO> getDeviceByProduct(String fkProductId);

    /**
     * 添加设备
     * @param deviceInfo
     * @return
     */
    int insertSelective(DeviceInfo deviceInfo);

    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    int deleteByPrimaryKey(String deviceId);

    /**
     * 通过设备id查询某一设备
     * @param deviceId
     * @return
     */
    DeviceVO selectById(String deviceId);


    DeviceInfo selectInfoById(String deviceId);

    /**
     * 更新设备
     * @param deviceInfo
     * @return
     */
    int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 根据设备名称查找所有设备(模糊查询)
     * @param name
     * @return
     */
    List<DeviceVO> getDeviceByName(String name);

    /**
     * 查询所有设备信息
     * @return
     */
    List<DeviceVO> selectAllDeviceVO();

}
