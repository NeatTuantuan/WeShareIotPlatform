package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;

public interface DeviceStateInfoService {
    DeviceStateInfo selectByDeviceId(String deviceId);

    int updateDeviceState(DeviceStateInfo deviceStateInfo);

}
