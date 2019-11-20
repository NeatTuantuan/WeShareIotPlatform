package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataServiceImpl implements DeviceDataService  {
    @Autowired
    DeviceDataMapper deviceDataMapper;
    @Override
    public int insertSelective(DeviceData deviceData) {
        return deviceDataMapper.insertSelective(deviceData);
    }

}
