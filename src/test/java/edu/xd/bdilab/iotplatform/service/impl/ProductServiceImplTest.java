package edu.xd.bdilab.iotplatform.service.impl;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import edu.xd.bdilab.iotplatform.mapper.ProductInfoMapper;
import edu.xd.bdilab.iotplatform.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

   @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    @Transactional
    public void addProduct() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductType("5");
        productInfo.setCreateTime(new Date());
        productInfo.setFkUserId(1);
        productInfo.setProductClassification(new Byte("0"));
        productInfo.setProductType("test");
        productInfo.setProductDesc("test");
        productInfo.setProductId("8");
        productInfo.setProductName("test");

        System.out.println(productInfoMapper.insertSelective(productInfo));
    }

    @Test
    public void deleteProduct() {
        int res = productInfoMapper.deleteByPrimaryKey("1");
        System.out.println(res);
    }

    @Test
    public void modifyProduct() {
        ProductInfo productInfo = productService.getProductInfo("1");
        productInfo.setProductType("6");
        int res = productInfoMapper.updateByPrimaryKeySelective(productInfo);
        System.out.println(res);
    }

    @Test
    public void getAllProductsInfo() {
        List<ProductInfo> productInfos = productService.getAllProductsInfo();
        for (ProductInfo productInfo:productInfos){
            System.out.println(productInfo.getProductName());
        }
    }

    @Test
    public void getProductInfo() {
        ProductInfo productInfo = productService.getProductInfo("1");
        System.out.println(productInfo.getProductName());
    }

    @Test
    public void getProductInfoByKeyWord() {
        String s = "传感器";
        List<ProductInfo> productInfos = productInfoMapper.selectAllProductByKeyWord(s);
        System.out.println(productInfos.size());
        for (ProductInfo productInfo:productInfos){
            System.out.println(productInfo.getProductName());
        }
    }
}