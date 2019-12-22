package edu.xd.bdilab.iotplatform.controller.product;

import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.Category;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;

import edu.xd.bdilab.iotplatform.service.device.DeviceDataService;
import edu.xd.bdilab.iotplatform.service.device.DeviceService;
import edu.xd.bdilab.iotplatform.service.device.CategoryService;
import edu.xd.bdilab.iotplatform.service.device.DeviceStateInfoService;
import edu.xd.bdilab.iotplatform.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 18:44
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@RestController
@Api(tags = {"产品相关功能"})
public class ProductController {
    //controller层是用来检验service层的
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProductService productService;
    @Autowired
    ResponseResult responseResult;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DeviceStateInfoService deviceStateInfoService;
    @Autowired
    DeviceDataService deviceDataService;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "product/addProduct")
    @ApiOperation(value = "添加产品")
    public ResponseResult insertProduct(@RequestBody ProductInfo productInfo){
        productService.addProduct(productInfo);
        logger.info("productInfo : " + productInfo.toString());

        responseResult.setSuccess(true);
        responseResult.setCode(ProductCode.ADD_PRODUCT_SUCCESS.getCode());
        responseResult.setMessage(ProductCode.ADD_PRODUCT_SUCCESS.getMessage());

        return  responseResult;
    }

    @PostMapping(value = "product/deleteProduct")
    @ApiImplicitParam(name = "productId", value = "产品ID")//参数说明
    @ApiOperation(value = "删除产品")
    public ResponseResult delProduct(@RequestParam("productId") String productId){
        productService.deleteProduct(productId);
        responseResult.setSuccess(true);
        responseResult.setCode(ProductCode.DELETE_PRODUCT_SUCCESS.getCode());
        responseResult.setMessage(ProductCode.DELETE_PRODUCT_SUCCESS.getMessage());

        return responseResult;
    }

    @PostMapping(value = "product/modifyProduct")
    @ApiOperation(value = "修改产品信息")
    public ResponseResult updateProduct(@RequestBody ProductInfo productInfo){
        int temp = productService.modifyProduct(productInfo);

        if (temp == 1){
            responseResult.setSuccess(true);
            responseResult.setCode(ProductCode.UPDATE_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.UPDATE_PRODUCT_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setCode(ProductCode.UPDATE_PRODUCT_FAILURE.getCode());
            responseResult.setMessage(ProductCode.UPDATE_PRODUCT_FAILURE.getMessage());
        }

        return responseResult;
    }

    @GetMapping(value = "product/getAllProductsInfo")
    @ApiOperation(value = "查看所有产品信息")
    public ResponseResult getAllProducts(){
        List<ProductInfo> productInfoList = productService.getAllProductsInfo();
        /*
        Iterator var = productInfoList.iterator();
        if(var.hasNext()){
            responseResult.setSuccess(true);
            responseResult.setCode(ProductCode.GET_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_SUCCESS.getMessage());
            while(var.hasNext()){
                logger.info("productInfo :" + var.toString());
                responseResult.setData(var);
            }
        }*/
        if(productInfoList != null){
            responseResult.setSuccess(true);
            responseResult.setCode(ProductCode.GET_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_SUCCESS.getMessage());
            responseResult.setData(productInfoList);
        }
        else {
            responseResult.setSuccess(false);
            responseResult.setCode(ProductCode.GET_PRODUCT_FAILURE.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_FAILURE.getMessage());
        }
        return responseResult;

    }

    @PostMapping(value = "product/getProductInfo")
    @ApiImplicitParam(name = "productId", value = "产品ID")
    @ApiOperation(value = "查看某一产品信息")
    public ResponseResult getProduct(@RequestParam("productId") String productId){
        ProductInfo productInfo = productService.getProductInfo(productId);
        logger.info("productInfo : " + productInfo.toString());
        if (productInfo != null){
            responseResult.setSuccess(true);
            responseResult.setData(productInfo);
            responseResult.setCode(ProductCode.GET_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setData(productInfo);
            responseResult.setCode(ProductCode.NO_REQUESTED_PRODUCT.getCode());
            responseResult.setMessage(ProductCode.NO_REQUESTED_PRODUCT.getMessage());
        }

        return responseResult;
    }

    @PostMapping(value = "product/getProductInfoByKeyword")
    @ApiImplicitParam(name = "keyword", value = "产品名称关键字")
    @ApiOperation(value = "根据关键字查看产品信息")
    public ResponseResult getProductByKeyword(@RequestParam("keyword") String keyWord){
        List<ProductInfo> productInfoList = productService.getAllProductInfoByKeyWord(keyWord);

        if (productInfoList.size() != 0){
            responseResult.setData(productInfoList);
            responseResult.setSuccess(true);
            responseResult.setCode(ProductCode.GET_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_SUCCESS.getMessage());
        }else {
            responseResult.setData(productInfoList);
            responseResult.setSuccess(false);
            responseResult.setCode(ProductCode.NO_REQUESTED_PRODUCT.getCode());
            responseResult.setMessage(ProductCode.NO_REQUESTED_PRODUCT.getMessage());
        }

        return responseResult;
    }

    //语雀没有请求的URL
    @PostMapping(value = "product/getProductInfoByProductName")
    @ApiImplicitParam(name = "productName", value = "产品名称")
    @ApiOperation(value = "根据产品名称查看产品信息")
    public ResponseResult getProductByProductName(@RequestParam("productName") String productName){
        ProductInfo productInfo = productService.selectByProductName(productName);

        logger.info("productInfo : " + productInfo.toString());
        if (productInfo != null){
            responseResult.setSuccess(true);
            responseResult.setData(productInfo);
            responseResult.setCode(ProductCode.GET_PRODUCT_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_PRODUCT_SUCCESS.getMessage());
        }else {
            responseResult.setSuccess(false);
            responseResult.setData(productInfo);
            responseResult.setCode(ProductCode.NO_REQUESTED_PRODUCT.getCode());
            responseResult.setMessage(ProductCode.NO_REQUESTED_PRODUCT.getMessage());
        }

        return responseResult;
    }

    @GetMapping(value = "product/productInfoStatistics")
    @ApiOperation(value = "所有产品的基础统计信息")
    public ResponseResult productInfoStatistics(){
        Map<String, Object> map = productService.productInfoStatistics();
        ResponseResult responseResult = new ResponseResult(true,"001","统计结果成功",map);
        return responseResult;
    }


    @GetMapping(value = "product/productDataStatistics")
    @ApiOperation(value = "所有产品的数据统计信息")
    public ResponseResult productDataStatistics(){
        return new ResponseResult(true,"001","统计成功",productService.productDataStatistics());
    }

    @GetMapping(value = "product/productDeviceData")
    @ApiOperation(value = "概览界面接口")
    public ResponseResult overviewInformation(){
        List<Map> mapList = new ArrayList<>();
        //产品总数
        Map<String, Object> productInfoMap = productService.productInfoStatistics();
        //产品对应的设备数
        List<ProductInfo> productInfoList = productService.getAllProductsInfo();
        Map<String,Integer> productDeviceNum = new HashMap<>();
        for (ProductInfo productInfo:productInfoList){
            productDeviceNum.put(productInfo.getProductName(),
                    deviceService.getDeviceByProduct(productInfo.getProductId()).size());
        }
        //产品对应的采集数和设备总数
        Map<String,Integer> productDataStatistics = productService.productDataStatistics();
        //在线设备
        Map<String,Object> onlineDeviceNumber = deviceStateInfoService.selectDeviceStateInfoByState(1);
        //设备总采集量
        Map<String,Long> allDeviceDataCount = new HashMap<>();
        allDeviceDataCount.put("设备总采集量", (long) deviceDataService.selectCount());

        mapList.add(productInfoMap);
        mapList.add(productDeviceNum);
        mapList.add(productDataStatistics);
        mapList.add(onlineDeviceNumber);
        mapList.add(allDeviceDataCount);

        ResponseResult responseResult = new ResponseResult(true,"001","成功",mapList);
    return responseResult;
    }


}

