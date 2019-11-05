package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface DeviceInfoMapper {
    /**
     * 根据设备id删除设备
     */
    int deleteByPrimaryKey(String deviceId);

    /**
     * 插入设备
     */
    int insert(DeviceInfo record);

    /**
     *插入设备
     */
    int insertSelective(DeviceInfo record);

    /**
     *按设备Id查询设备
     */
    DeviceInfo selectByPrimaryKey(String deviceId);

    /**
     * 更新设备信息
     */
    int updateByPrimaryKeySelective(DeviceInfo record);

    /**
     * 更新设备信息
     */
    int updateByPrimaryKey(DeviceInfo record);

    /**
     * 根据产品id获取所有设备
     * @param fkProductId
     * @return
     */

    List<DeviceInfo> selectByProduct(String fkProductId);

    /**
     * 根据名称查找设备
     * @param name
     * @return
     */
    List<DeviceInfo> selectDeviceByName(String name);
}