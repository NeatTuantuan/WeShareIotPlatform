package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceClassification;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceClassificationMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.service.device.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private DeviceClassificationMapper deviceClassificationMapper;


    @Override
    public List<DeviceInfo> selectAllDeviceInfo() {

        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectAllDevice();

        return deviceInfoList;
    }

    @Override
    public int insertDeviceClassification(DeviceClassification deviceClassification) {
        return deviceClassificationMapper.insertSelective(deviceClassification);
    }

    @Override
    public DeviceInfo selectById(String id) {
        return deviceInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> getAllCategory(String deviceId) {
        return deviceClassificationMapper.selectCategoryByDeviceId(deviceId);
    }

    @Override
    public int updateCategory(DeviceClassification deviceClassification) {
        return deviceClassificationMapper.updateCategoryById(deviceClassification);
    }

    @Override
    public DeviceClassification getDeviceClassificationById(int id) {
        return deviceClassificationMapper.selectById(id);
    }

    @Override
    public int deleteDeviceCategory(String deviceId,int categoryId) {
        return  deviceClassificationMapper.deleteDeviceCategory(deviceId,categoryId);
    }

    @Override
    public int deleteByCategoryId(int categoryId) {
        return deviceClassificationMapper.deleteByCategoryId(categoryId);
    }
}
