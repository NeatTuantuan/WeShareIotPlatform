package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Override
    public List<DeviceInfo> selectAllDeviceInfo() {

        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectAllDevice();

        return deviceInfoList;
    }
}
