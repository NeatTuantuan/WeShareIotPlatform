package edu.xd.bdilab.iotplatform.service.product;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * 添加产品
     * @param productInfo
     * @return
     */
    int addProduct(ProductInfo productInfo);

    /**
     * 删除产品
     * @param productId
     * @return
     */
    int deleteProduct(String productId);

    /**
     * 修改产品
     * @param productInfo
     * @return
     */
    int modifyProduct(ProductInfo productInfo);

    /**
     * 查看所有产品
     * @return
     */
    List<ProductInfo> getAllProductsInfo();

    /**
     * 查看某一产品
     * @return
     */
    ProductInfo getProductInfo(String productId);

    /**
     * 根据关键字查询产品
     * @param keyWord
     * @return
     */
    List<ProductInfo> getAllProductInfoByKeyWord(String keyWord);

    /**
     * 按产品id查询产品信息
     */
    ProductInfo selectByPrimaryKey(String productId);

    /**
     * 根据产品名称查找产品
     * @param productName
     * @return
     */
    ProductInfo selectByProductName(String productName);

    /**
     * 统计信息统计，一共有多少产品，每个产品下有多少设备
     * @return
     */
    Map<String,Object> productInfoStatistics();


    /**
     * 统计每个产品的数据量
     * @return
     */
    Map<String, Integer> productDataStatistics();

}
