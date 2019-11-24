package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceStateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceStateInfoServiceImpl implements DeviceStateInfoService {

    @Autowired
    DeviceStateInfoMapper deviceStateInfoMapper;

    @Override
    public DeviceStateInfo selectByDeviceId(String deviceId) {
        return deviceStateInfoMapper.selectByDeviceId(deviceId);
    }

    @Override
    public int updateDeviceState(DeviceStateInfo deviceStateInfo) {
        return deviceStateInfoMapper.updateDeviceState(deviceStateInfo);
    }


}
