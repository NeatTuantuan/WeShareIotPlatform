package edu.xd.bdilab.iotplatform.service;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;

import java.util.List;

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


}
