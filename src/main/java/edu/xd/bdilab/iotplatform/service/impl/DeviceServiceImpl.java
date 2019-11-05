package edu.xd.bdilab.iotplatform.service.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public List<DeviceInfo> getDeviceByProduct(String fkProductId) {
        return deviceInfoMapper.selectByProduct(fkProductId);
    }

    @Override
    public int insertSelective(DeviceInfo deviceInfo) {
        return deviceInfoMapper.insertSelective(deviceInfo);
    }

    @Override
    public int deleteByPrimaryKey(String deviceId) {
        return deviceInfoMapper.deleteByPrimaryKey(deviceId);
    }

    @Override
    public DeviceInfo selectById(String deviceId) {
        return deviceInfoMapper.selectByPrimaryKey(deviceId);
    }

    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
        return deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }

    @Override
    public List<DeviceInfo> getDeviceByName(String name) {
        return deviceInfoMapper.selectDeviceByName(name);
    }


}
