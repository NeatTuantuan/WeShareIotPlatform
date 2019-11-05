package edu.xd.bdilab.iotplatform.service.impl;

import edu.xd.bdilab.iotplatform.dao.DeviceInfo;
import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.mapper.DeviceInfoMapper;
import edu.xd.bdilab.iotplatform.mapper.ProductInfoMapper;
import edu.xd.bdilab.iotplatform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
