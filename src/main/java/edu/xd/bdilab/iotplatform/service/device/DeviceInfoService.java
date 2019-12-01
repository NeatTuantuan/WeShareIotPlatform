package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;

import java.util.List;

public interface DeviceInfoService {
    List<DeviceInfo> selectAllDeviceInfo();
}
