package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceStateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceStateInfoServiceImpl implements DeviceStateInfoService {

    @Autowired
    DeviceStateInfoMapper deviceStateInfoMapper;
    @Autowired
    DeviceInfoMapper deviceInfoMapper;

    @Override
    public DeviceStateInfo selectByDeviceId(String deviceId) {
        return deviceStateInfoMapper.selectByDeviceId(deviceId);
    }


    @Override
    public int updateDeviceState(DeviceStateInfo deviceStateInfo) {
        return deviceStateInfoMapper.updateDeviceState(deviceStateInfo);
    }

    @Override
    public Map<String, Object> selectDeviceStateInfoByState(int deviceState) {
        Map<String, Object> deviceStateInfoMap = new HashMap<>();
        List<DeviceStateInfo> deviceStateInfoList = deviceStateInfoMapper.selectDeviceStateInfoByState(deviceState);
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        if (!deviceStateInfoList.isEmpty()){
            for (DeviceStateInfo deviceStateInfo : deviceStateInfoList){
                deviceInfoList.add(deviceInfoMapper.selectByPrimaryKey(deviceStateInfo.getFkDeviceId()));
            }
            deviceStateInfoMap.put("deviceStateInfo",deviceInfoList);
            deviceStateInfoMap.put("onlineDeviceNumber",deviceStateInfoList.size());
        }else {
            deviceStateInfoMap.put("deviceStateInfo",null);
            deviceStateInfoMap.put("onlineDeviceNumber",0);
        }

        return deviceStateInfoMap;
    }


}
