package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private DeviceStateInfoMapper deviceStateInfoMapper;

    /**
     * 通过产品Id获取该产品下的所有设备
     * @param fkProductId
     * @return
     */
    @Override
    public List<DeviceVO> getDeviceByProduct(String fkProductId) {
        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectByProduct(fkProductId);
        return deviceInfoToDeviceVO(deviceInfoList);
    }

    /**
     * 添加设备,同时向redis缓存中添加设备id与状态缓存
     * @param deviceInfo
     * @return
     */
    @Override
    public int insertSelective(DeviceInfo deviceInfo) {
        DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
        RedisUtil redisUtil = new RedisUtil();

        deviceStateInfo.setFkDeviceId(deviceInfo.getDeviceId());
        deviceStateInfoMapper.insertDeviceState(deviceStateInfo);

        //选择1号数据库
        redisUtil.getJedis().select(1);
        redisUtil.set(deviceInfo.getDeviceId(), );


        return deviceInfoMapper.insertSelective(deviceInfo);
    }

    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String deviceId) {
        deviceStateInfoMapper.deleteByFkDeviceId(deviceId);
        return deviceInfoMapper.deleteByPrimaryKey(deviceId);
    }

    /**
     * 通过设备id查询某一设备
     * @param deviceId
     * @return
     */
    @Override
    public DeviceVO selectById(String deviceId) {
        DeviceInfo deviceInfo = deviceInfoMapper.selectByPrimaryKey(deviceId);

        DeviceVO deviceVO = new DeviceVO();

        deviceVO.setDeviceId(deviceInfo.getDeviceId());
        deviceVO.setDeviceName(deviceInfo.getDeviceName());
        deviceVO.setFkProductId(deviceInfo.getFkProductId());
        deviceVO.setGetwayId(deviceInfo.getGetwayId());
        deviceVO.setCreateTime(deviceInfo.getCreateTime());
        deviceVO.setDeviceState(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).getDeviceState());
        return deviceVO;
    }

    @Override
    public DeviceInfo selectInfoById(String deviceId) {
        return deviceInfoMapper.selectByPrimaryKey(deviceId);
    }


    /**
     * 更新设备
     * @param deviceInfo
     * @return
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
        return deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }


    /**
     * 根据设备名称查找所有设备(模糊查询)
     * @param name
     * @return
     */
    @Override
    public List<DeviceVO> getDeviceByName(String name) {
        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectDeviceByName(name);
        return deviceInfoToDeviceVO(deviceInfoList);
    }

    /**
     * deviceInfo实体类转deviceVO
     * @param deviceInfoList
     * @return
     */
    public List<DeviceVO> deviceInfoToDeviceVO (List<DeviceInfo> deviceInfoList) throws NullPointerException{
        List<DeviceVO> deviceVOList = new LinkedList<>();

        for(DeviceInfo deviceInfo : deviceInfoList){
            DeviceVO deviceVO = new DeviceVO();

            deviceVO.setDeviceId(deviceInfo.getDeviceId());
            deviceVO.setFkProductId(deviceInfo.getFkProductId());
            deviceVO.setDeviceName(deviceInfo.getDeviceName());
            deviceVO.setCreateTime(deviceInfo.getCreateTime());
            deviceVO.setGetwayId(deviceInfo.getGetwayId());
            deviceVO.setDeviceState(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).getDeviceState());

            deviceVOList.add(deviceVO);
        }

        return deviceVOList;

    }


}
