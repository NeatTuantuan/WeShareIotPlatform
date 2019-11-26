package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;

import java.util.List;
import java.util.Map;

public interface DeviceStateInfoService {
    DeviceStateInfo selectByDeviceId(String deviceId);

    int updateDeviceState(DeviceStateInfo deviceStateInfo);

    Map<String, Object> selectDeviceStateInfoByState(int deviceState);

}
