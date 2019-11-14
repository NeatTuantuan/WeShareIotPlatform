package edu.xd.bdilab.iotplatform.controller.decive;


import edu.xd.bdilab.iotplatform.controller.decive.DeviceCode;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"设备相关功能"})
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProductService productService;
    @Autowired
    ResponseResult responseResult;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "device/getDeviceByProduct")
    @ApiImplicitParam(name = "productName", value = "产品名称")
    @ApiOperation(value = "根据产品名称查看设备")
    public ResponseResult getDeviceByProduct(@RequestParam("productName") String productName){
        List<DeviceInfo> deviceInfoList = null;

        try{
            String productId = productService.selectByProductName(productName).getProductId();
            logger.info("productId : "+productId);
            deviceInfoList = deviceService.getDeviceByProduct(productId);
            logger.info("deviceInfoList : "+deviceInfoList);
        }catch (NullPointerException e){
            responseResult.setData(deviceInfoList);
            responseResult.setCode(DeviceCode.GET_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_FAILURE.getMessage());
            responseResult.setSuccess(true);
        }

        responseResult.setData(deviceInfoList);
        responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
        responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());

        return responseResult;
    }


    @PostMapping(value = "device/addDevice")
    @ApiOperation(value = "添加设备")
    public ResponseResult addDevice(@RequestBody DeviceInfo deviceInfo){
        deviceService.insertSelective(deviceInfo);
        logger.info("deviceInfo : "+deviceInfo.toString());

        responseResult.setSuccess(true);
        responseResult.setCode(DeviceCode.ADD_DEVICE_SUCCESS.getCode());
        responseResult.setMessage(DeviceCode.ADD_DEVICE_SUCCESS.getMessage());

        return  responseResult;
    }


    @PostMapping(value = "device/getDeviceByKeyWord")
    @ApiImplicitParam(name = "keyWord", value = "设备名称")
    @ApiOperation(value = "根据设备关键字查看设备")
    public ResponseResult getDeviceByName(@RequestParam("keyWord")String keyWord){
        List<DeviceInfo> deviceInfoList = deviceService.getDeviceByName(keyWord);

        if (deviceInfoList.size() != 0){
            responseResult.setData(deviceInfoList);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());
        }else {
            responseResult.setData(deviceInfoList);
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
        DeviceInfo deviceInfo = deviceService.selectById(deviceId);
        logger.info("deviceInfo : "+deviceInfo.toString());
        if (deviceInfo != null){
            responseResult.setSuccess(true);
            responseResult.setData(deviceInfo);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setData(deviceInfo);
            responseResult.setCode(DeviceCode.NO_REQUESTED_DEVICE.getCode());
            responseResult.setMessage(DeviceCode.NO_REQUESTED_DEVICE.getMessage());
        }

        return responseResult;

    }


    @PostMapping(value = "device/modifyDevice")
    @ApiOperation(value = "修改设备信息")
    public ResponseResult modifyDevice(@RequestBody DeviceInfo deviceInfo){

        DeviceInfo deviceInfoModify = deviceService.selectById(deviceInfo.getDeviceId());
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
        return null;
    }



}
