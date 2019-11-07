package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 通过产品Id获取该产品下的所有设备
     * @param fkProductId
     * @return
     */
    @Override
    public List<DeviceInfo> getDeviceByProduct(String fkProductId) {
        return deviceInfoMapper.selectByProduct(fkProductId);
    }

    /**
     * 添加设备
     * @param deviceInfo
     * @return
     */
    @Override
    public int insertSelective(DeviceInfo deviceInfo) {
        return deviceInfoMapper.insertSelective(deviceInfo);
    }

    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String deviceId) {
        return deviceInfoMapper.deleteByPrimaryKey(deviceId);
    }

    /**
     * 通过设备id查询某一设备
     * @param deviceId
     * @return
     */
    @Override
    public DeviceInfo selectById(String deviceId) {
        return deviceInfoMapper.selectByPrimaryKey(deviceId);
    }


    /**
     * 更新设备
     * @param deviceInfo
     * @return
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
        return deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }


    /**
     * 根据设备名称查找所有设备(模糊查询)
     * @param name
     * @return
     */
    @Override
    public List<DeviceInfo> getDeviceByName(String name) {
        return deviceInfoMapper.selectDeviceByName(name);
    }


}
