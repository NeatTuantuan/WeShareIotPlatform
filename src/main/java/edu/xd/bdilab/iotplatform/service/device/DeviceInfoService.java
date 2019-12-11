package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceClassification;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;

import java.util.List;

public interface DeviceInfoService {
    List<DeviceInfo> selectAllDeviceInfo();

    int insertDeviceClassification(DeviceClassification deviceClassification);

    DeviceInfo selectById(String id);

    List<Integer> getAllCategory(String deviceId);

    int updateCategory(DeviceClassification deviceClassification);

    DeviceClassification getDeviceClassificationById(int id);

    int deleteDeviceCategory(String deviceId,int categoryId);

    int deleteByCategoryId(int categoryId);
}
