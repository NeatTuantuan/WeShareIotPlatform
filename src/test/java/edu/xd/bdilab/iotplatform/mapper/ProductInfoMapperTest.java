package edu.xd.bdilab.iotplatform.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(productInfoMapper.selectByProductName("亮度传感器").getProductId());
    }
}