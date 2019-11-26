package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface DeviceDataService {
    int insertSelective(DeviceData deviceData);

    List<DeviceData> selectByTime(Map<String,String> params);

    List<DeviceData> selectAll(String gatewayId);

    Map<String,Object> SelectAllDataByDeviceId(String deviceId);
    /**
     * 根据设备id查询最新一条数据
     * @param deviceId
     * @return
     */
    DeviceData getRecentData(String deviceId);

    /**
     * 统计所有数据条数
     * @return
     */
    int selectCount();
}
