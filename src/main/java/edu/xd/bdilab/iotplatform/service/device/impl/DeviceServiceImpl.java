package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceDataMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.DeviceStateInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.ProductInfoMapper;
import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.vo.DeviceDataVO;
import edu.xd.bdilab.iotplatform.vo.DeviceReflectionVO;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private DeviceStateInfoMapper deviceStateInfoMapper;
    @Autowired
    private DeviceDataMapper deviceDataMapper;
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
     * 通过设备id查询某一设备VO
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
        logger.info("------DeviceId:"+deviceInfo.getDeviceId()+"------");
        deviceVO.setDeviceState(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).getDeviceState());
        logger.info("------FkProductId:"+deviceInfo.getFkProductId()+"------");
        deviceVO.setProductInfo(productInfoMapper.selectByPrimaryKey(deviceInfo.getFkProductId()));
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
     * 设备影子
     * @param deviceId
     * @return
     */
    @Override
    public DeviceReflectionVO deviceReflection(String deviceId) {
        DeviceReflectionVO deviceReflectionVO = new DeviceReflectionVO();
        DeviceInfo deviceInfo = deviceInfoMapper.selectByPrimaryKey(deviceId);

        deviceReflectionVO.setDeviceInfo(deviceInfo);
        deviceReflectionVO.setProductInfo(productInfoMapper.selectByPrimaryKey(deviceInfo.getFkProductId()));
        deviceReflectionVO.setDeviceSate(deviceStateInfoMapper.selectByDeviceId(deviceId).getDeviceState());
//        logger.info("DeviceDataVO:" + deviceDataMapper.selectRecent(deviceInfo.getGetwayId()));
        deviceReflectionVO.setDeviceDataVO(
                deviceDataToDeviceDataVO(deviceDataMapper.selectRecent(deviceInfo.getGetwayId()))
        );
        return deviceReflectionVO;
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
            logger.info("------DeviceId:"+deviceInfo.getDeviceId()+"------");
//            logger.info(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).toString());
            deviceVO.setDeviceState(deviceStateInfoMapper.selectByDeviceId(deviceInfo.getDeviceId()).getDeviceState());

            deviceVOList.add(deviceVO);
        }

        return deviceVOList;

    }

    /**
     * deviceData类型转DeviceDataVO
     * @param deviceData
     * @return
     */
    public DeviceDataVO deviceDataToDeviceDataVO(DeviceData deviceData){
        DeviceDataVO deviceDataVO = new DeviceDataVO();
        if (deviceData!=null){
            deviceDataVO.setGatewayId(deviceData.getGatewayId());
            deviceDataVO.setMetaData(deviceData.getMetaData());
            deviceDataVO.setFormatData(deviceData.getFormatData());
            deviceDataVO.setTimeStamp(deviceData.getTimeStamp());
        }

        return deviceDataVO;
    }


    @Override
    public Map<String, Integer> deviceInfoStatistics() {
        Map<String, Integer> map = new HashMap<>();
        List<DeviceVO> deviceVOList = this.selectAllDeviceVO();

        map.put("deviceCount",deviceVOList.size());

        if (deviceVOList.size() == 0){
            return map;
        }

        for (DeviceVO deviceVO : deviceVOList){
            map.put(deviceVO.getDeviceName(),
                        deviceDataMapper.selectAll(
                                deviceVO.getGetwayId()
                        ).size()
                    );
        }

        return map;
    }

}
