package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        redisUtil.set(deviceInfo.getDeviceId(), DateUtil.getDate(),1);


        return deviceInfoMapper.insertSelective(deviceInfo);
    }

    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String deviceId) {
        RedisUtil redisUtil = new RedisUtil();
        //删除设备状态表中设备队形的状态
        deviceStateInfoMapper.deleteByFkDeviceId(deviceId);
        //删除redis中该设备对应的开启时间
        redisUtil.deleteKey(deviceId,1);
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
        logger.info(deviceInfo.getDeviceId());
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
     *查询所有设备VO信息
     * @return
     */
    @Override
    public List<DeviceVO> selectAllDeviceVO() {
        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectAllDevice();
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
            logger.info(deviceInfo.getDeviceId());
//            logger.info(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).toString());
            deviceVO.setDeviceState(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).getDeviceState());

            deviceVOList.add(deviceVO);
        }

        return deviceVOList;

    }


}
