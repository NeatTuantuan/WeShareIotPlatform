package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceDataServiceImpl implements DeviceDataService  {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Override
    public int insertSelective(DeviceData deviceData) {
        return deviceDataMapper.insertSelective(deviceData);
    }

    @Override
    public List<DeviceData> selectByTime(Map<String, String> params) {
        return deviceDataMapper.selectByTime(params);
    }

    @Override
    public List<DeviceData> selectAll(String gatewayId) {
        return selectAll(gatewayId);
    }

}
