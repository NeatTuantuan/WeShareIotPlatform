package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceDataServiceImpl implements DeviceDataService  {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Autowired
    DeviceInfoMapper deviceInfoMapper;
    @Autowired
    DeviceStateInfoMapper deviceStateInfoMapper;
    @Override
    public int insertSelective(DeviceData deviceData) {
        return deviceDataMapper.insertSelective(deviceData);
    }

    @Override
    public List<DeviceData> selectByTime(String gatewayId,String startTime,String endTime) {
        return deviceDataMapper.selectByTime(gatewayId,startTime,endTime);
    }

    @Override
    public List<DeviceData> selectAll(String gatewayId) {
        return selectAll(gatewayId);
    }

    @Override
    public Map<String, Object> SelectAllDataByDeviceId(String deviceId) {
        String gatewayId = deviceInfoMapper.selectByPrimaryKey(deviceId).getGetwayId();
        List<DeviceData> deviceDataList = deviceDataMapper.selectTop(gatewayId);
//        List<DeviceData> deviceDataList = deviceDataMapper.selectAll(gatewayId);
        Map<String, Object> deviceDataMap = new HashMap<>();
        deviceDataMap.put("deviceDataList",deviceDataList);
        deviceDataMap.put("count",deviceDataList.size());
        return deviceDataMap;
    }

    @Override
    public DeviceData getRecentData(String deviceId) {
        return deviceDataMapper.selectRecent(deviceInfoMapper.selectByPrimaryKey(deviceId).getGetwayId());
    }

    @Override
    public int selectCount() {
        return deviceDataMapper.selectCount();
    }

    @Override
    public int updateDeviceState(DeviceStateInfo deviceStateInfo) {
        return deviceStateInfoMapper.updateDeviceState(deviceStateInfo);
    }

}
