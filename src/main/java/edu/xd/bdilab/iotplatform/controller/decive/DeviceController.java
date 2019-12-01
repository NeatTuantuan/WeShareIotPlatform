package edu.xd.bdilab.iotplatform.controller.decive;


import edu.xd.bdilab.iotplatform.controller.decive.DeviceCode;
import edu.xd.bdilab.iotplatform.controller.response.MetaData;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.DeviceData;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.DeviceStateInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;

import edu.xd.bdilab.iotplatform.netty.redis.RedisUtil;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.RadomUtil;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;

import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.service.device.DeviceStateInfoService;
import edu.xd.bdilab.iotplatform.service.product.ProductService;
import edu.xd.bdilab.iotplatform.vo.DeviceReflectionVO;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Api(tags = {"设备相关功能"})
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProductService productService;
    @Autowired
    ResponseResult responseResult;
    @Autowired
    DeviceDataService deviceDataService;
    @Autowired
    DeviceStateInfoService deviceStateInfoService;

    RedisUtil redisUtil = new RedisUtil();

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "device/getDeviceByProduct")
    @ApiImplicitParam(name = "productName", value = "产品名称")
    @ApiOperation(value = "根据产品名称查看设备")
    public ResponseResult getDeviceByProduct(@RequestParam("productName") String productName){
        List<DeviceVO> deviceVOList = new ArrayList<>();

        try{

            if (productName.equals("全部产品")){
                deviceVOList = deviceService.selectAllDeviceVO();
            }else {
                String productId = productService.selectByProductName(productName).getProductId();
                logger.info("productId : "+productId);
                deviceVOList = deviceService.getDeviceByProduct(productId);
                logger.info("deviceVOList : "+deviceVOList);
            }
        }catch (NullPointerException e){
            responseResult.setData(deviceVOList);
            responseResult.setCode(DeviceCode.GET_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_FAILURE.getMessage());
            responseResult.setSuccess(true);
        }

        responseResult.setSuccess(true);
        responseResult.setData(deviceVOList);
        responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
        responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());

        return responseResult;
    }


    @PostMapping(value = "device/addDevice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fkProductId", value = "产品ID"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称"),
            @ApiImplicitParam(name = "createTime", value = "创建时间"),
            @ApiImplicitParam(name = "getwayId", value = "网关id"),
    })
    @ApiOperation(value = "添加设备")
    public ResponseResult addDevice(@RequestParam String fkProductId,
                                    @RequestParam String deviceName,
                                    @RequestParam String createTime,
                                    @RequestParam String getwayId){
        if (!deviceService.getDeviceByName(deviceName).isEmpty()){
            responseResult = new ResponseResult(null,new MetaData(false,"500","已存在同名设备"));
        }else {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setFkProductId(fkProductId);
            deviceInfo.setDeviceName(deviceName);
            deviceInfo.setCreateTime(DateUtil.stringToDate(createTime));
            deviceInfo.setGetwayId(getwayId);
            deviceInfo.setDeviceId(new RadomUtil().getRandomString());
            deviceService.insertSelective(deviceInfo);
            logger.info("deviceInfo : "+deviceInfo.toString());

            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.ADD_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.ADD_DEVICE_SUCCESS.getMessage());
        }


        return  responseResult;
    }


    @PostMapping(value = "device/getDeviceByKeyWord")
    @ApiImplicitParam(name = "keyWord", value = "设备名称")
    @ApiOperation(value = "根据设备关键字查看设备")
    public ResponseResult getDeviceByName(@RequestParam("keyWord")String keyWord){
        List<DeviceVO> DeviceVOList = deviceService.getDeviceByName(keyWord);

        if (DeviceVOList.size() != 0){
            responseResult.setData(DeviceVOList);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());
        }else {
            responseResult.setData(DeviceVOList);
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.NO_REQUESTED_DEVICE.getCode());
            responseResult.setMessage(DeviceCode.NO_REQUESTED_DEVICE.getMessage());
        }

        return responseResult;
    }


    @PostMapping(value = "device/getDeviceInfo")
    @ApiImplicitParam(name = "deviceId", value = "设备ID")
    @ApiOperation(value = "根据设备ID查看设备")
    public ResponseResult getDeviceInfo(@RequestParam("deviceId") String deviceId){
        DeviceVO deviceVO = deviceService.selectById(deviceId);
        logger.info("deviceInfo : "+deviceVO.toString());
        if (deviceVO != null){
            responseResult.setSuccess(true);
            responseResult.setData(deviceVO);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setData(deviceVO);
            responseResult.setCode(DeviceCode.NO_REQUESTED_DEVICE.getCode());
            responseResult.setMessage(DeviceCode.NO_REQUESTED_DEVICE.getMessage());
        }

        return responseResult;

    }


    @PostMapping(value = "device/modifyDevice")
    @ApiOperation(value = "修改设备信息")
    public ResponseResult modifyDevice(@RequestBody DeviceInfo deviceInfo){

        DeviceInfo deviceInfoModify = deviceService.selectInfoById(deviceInfo.getDeviceId());
        deviceInfoModify.setGetwayId(deviceInfo.getGetwayId());
        deviceInfoModify.setDeviceName(deviceInfo.getDeviceName());

        int temp = deviceService.updateDeviceInfo(deviceInfoModify);

        if (temp == 1){
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.UPDATE_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.UPDATE_DEVICE_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.UPDATE_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.UPDATE_DEVICE_FAILURE.getMessage());
        }

        return responseResult;
    }

    @PostMapping(value = "device/deleteDevice")
    @ApiImplicitParam(name = "deviceId", value = "设备ID")
    @ApiOperation(value = "删除设备")
    public ResponseResult deleteDevice(@RequestParam("deviceId") String deviceId){
        deviceService.deleteByPrimaryKey(deviceId);
        responseResult.setSuccess(true);
        responseResult.setCode(DeviceCode.DELETE_DEVICE_SUCCESS.getCode());
        responseResult.setMessage(DeviceCode.DELETE_DEVICE_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 采集功能，用户按下开始时采集数据
     * @param deviceId
     * @param startTime
     * @return
     */
    @PostMapping(value = "device/startGetData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId",value = "设备id"),
            @ApiImplicitParam(name = "startTime",value = "开始时间")
    })
    @ApiOperation(value = "采集数据")
    public ResponseResult startGetData(@RequestParam("deviceId")String deviceId,
                                          @RequestParam("startTime")String startTime){
        String gatewayId = deviceService.selectById(deviceId).getGetwayId();
        //将网关和当前时间存入redis
        redisUtil.setTime(gatewayId,startTime);
        //根据设备id获取设备状态信息
        DeviceStateInfo deviceStateInfo = deviceStateInfoService.selectByDeviceId(deviceId);
        //修改状态
        deviceStateInfo.setDeviceState((byte)1);
        int res = deviceStateInfoService.updateDeviceState(deviceStateInfo);
        if (res >0){
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.UPDATE_DEVICE_STATE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.UPDATE_DEVICE_STATE_SUCCESS.getMessage());
            return responseResult;
        }else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.UPDATE_DEVICE_STATE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.UPDATE_DEVICE_STATE_FAILURE.getMessage());
            return responseResult;
        }

    }


    /**
     * 根据设备id和时间段查询数据
     * @param deviceId
     * @return
     */
    @PostMapping(value = "device/getData")
    @ApiImplicitParam(name = "deviceId",value = "设备id")
    @ApiOperation(value = "获得一次采集的所有数据")
    public ResponseResult getDeviceDataByTime(@RequestParam("deviceId")String deviceId){

        String gatewayId = deviceService.selectById(deviceId).getGetwayId();
        Map<String,String> params = new HashMap<>();
        params.put("gatewayId",gatewayId);
        //根据网关id获取开始时间；
        String startTime = redisUtil.getTime(gatewayId);
        params.put("startTime",startTime);
        params.put("endTime", DateUtil.getDate());
        Map<String,Object> deviceDataMap = new HashMap<>();
        List<DeviceData> deviceDataList = deviceDataService.selectByTime(params);

        deviceDataMap.put("size",deviceDataList.size());
        deviceDataMap.put("deviceDataList",deviceDataList);
        if (deviceDataList.size()>0){
            responseResult.setSuccess(true);
            responseResult.setData(deviceDataMap);
            responseResult.setCode(DeviceCode.GET_DEVICE_DATA_BY_TIME_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_DATA_BY_TIME_SUCCESS.getMessage());
            return responseResult;
        }else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.GET_DEVICE_DATA_BY_TIME_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_DATA_BY_TIME_FAILURE.getMessage());
            return responseResult;
        }

    }

    @GetMapping(value = "device/getOnlineDeviceCount")
    @ApiOperation(value = "获得所有在线设备信息及数量")
    public ResponseResult getOnlineDeviceCount(){
        responseResult.setData(deviceStateInfoService.selectDeviceStateInfoByState(1));
        responseResult.setSuccess(true);
        responseResult.setCode("001");
        responseResult.setMessage("成功获得所有在线设备信息");
        return responseResult;
    }

    //根据设备id获取该设备所有的数据以及数量
    @PostMapping(value = "device/getOneDeviceAllData")
    @ApiImplicitParam(name = "deviceId", value = "设备ID")
    @ApiOperation(value = "获得一个设备的所有数据信息")
    public ResponseResult getOneDeviceAllData(@RequestParam String deviceId){
        responseResult.setData(deviceDataService.SelectAllDataByDeviceId(deviceId));
        responseResult.setSuccess(true);
        responseResult.setCode("001");
        responseResult.setMessage("获取设备数据信息成功");
        return  responseResult;
    }

    //查询数据库中所有的数据数量
    @GetMapping(value = "device/selectCount")
    @ApiOperation(value = "获取所有设备数据数量")
    public ResponseResult selectCount(){
        return new ResponseResult(deviceDataService.selectCount(),new MetaData(true,"001","查询成功"));
    }


    @PostMapping(value = "device/deviceReflection")
    @ApiImplicitParam(name = "deviceId", value = "设备ID")
    @ApiOperation(value = "获取设备影子")
    public ResponseResult deviceReflection(@RequestParam String deviceId){
        DeviceReflectionVO deviceReflectionVO = deviceService.deviceReflection(deviceId);
        responseResult.setData(deviceReflectionVO);
        responseResult.setSuccess(true);
        responseResult.setCode("001");
        responseResult.setMessage("查询设备影子成功");
        return responseResult;
    }

    @PostMapping(value = "device/getRecentData")
    @ApiImplicitParam(name = "deviceId", value = "设备ID")
    @ApiOperation(value = "获取设备最新一条数据")
    public ResponseResult getRecentData(@RequestParam String deviceId){
        responseResult.setData(deviceDataService.getRecentData(deviceId));
        responseResult.setSuccess(true);
        responseResult.setCode("001");
        responseResult.setMessage("获取设备最新一条数据");
        return responseResult;
    }




}
