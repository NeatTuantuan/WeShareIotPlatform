package edu.xd.bdilab.iotplatform.service.product.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;

import edu.xd.bdilab.iotplatform.mapper.ProductInfoMapper;
import edu.xd.bdilab.iotplatform.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public int addProduct(ProductInfo productInfo) {
        return productInfoMapper.insertSelective(productInfo);
    }

    //在删除产品前需要删除产品下的设备
    @Override
    public int deleteProduct(String productId) {
        //查询该产品id下是否有设备
        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectByProduct(productId);
        //删除设备
        if (!deviceInfoList.isEmpty()){
            for (DeviceInfo deviceInfo:deviceInfoList){
                deviceInfoMapper.deleteByPrimaryKey(deviceInfo.getDeviceId());
            }
        }
        return productInfoMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public int modifyProduct(ProductInfo productInfo) {
        return productInfoMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public List<ProductInfo> getAllProductsInfo() {
        return productInfoMapper.selectAllProduct();
    }

    @Override
    public ProductInfo getProductInfo(String productId) {
        return productInfoMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<ProductInfo> getAllProductInfoByKeyWord(String keyWord) {
        return productInfoMapper.selectAllProductByKeyWord(keyWord);
    }

    @Override
    public ProductInfo selectByPrimaryKey(String productId) {
        return productInfoMapper.selectByPrimaryKey(productId);
    }

    @Override
    public ProductInfo selectByProductName(String productName) {
        return productInfoMapper.selectByProductName(productName);
    }

    @Override
    public Map<String, Object> productInfoStatistics() {
        Map<String, Object> map = new HashMap<>();
        String name = null;
        List<ProductInfo> productInfoList = this.getAllProductsInfo();
        map.put("productCount",productInfoList.size());

        if (productInfoList.size() == 0){
            return map;
        }

        for (ProductInfo productInfo : productInfoList){
            name = productInfo.getProductName();
            map.put(name,
                    deviceInfoMapper.selectByProduct(
                            productInfo.getProductId()
                    ).size()
            );
        }

        return map;


    }




}
