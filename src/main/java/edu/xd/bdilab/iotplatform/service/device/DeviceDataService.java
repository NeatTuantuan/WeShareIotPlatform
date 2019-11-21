package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import org.springframework.stereotype.Service;


public interface DeviceDataService {
    int insertSelective(DeviceData deviceData);
}
