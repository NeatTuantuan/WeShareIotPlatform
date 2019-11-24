package edu.xd.bdilab.iotplatform.controller.decive;


import edu.xd.bdilab.iotplatform.controller.decive.DeviceCode;
import edu.xd.bdilab.iotplatform.controller.response.MetaData;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import edu.xd.bdilab.iotplatform.netty.util.RadomUtil;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.service.product.ProductService;
import edu.xd.bdilab.iotplatform.vo.DeviceVO;
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

import java.util.ArrayList;
import java.util.Date;
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



}
