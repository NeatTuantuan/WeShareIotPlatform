package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductInfoMapper {
    /**
     * 按产品id删除产品
     */
    int deleteByPrimaryKey(String productId);

    /**
     *插入产品
     */
    int insert(ProductInfo record);

    /**
     *插入产品
     */
    int insertSelective(ProductInfo record);

    /**
     * 按产品id查询产品信息
     */
    ProductInfo selectByPrimaryKey(String productId);

    /**
     * 更新产品
     */
    int updateByPrimaryKeySelective(ProductInfo record);

    /**
     * 更新产品
     */
    int updateByPrimaryKey(ProductInfo record);

    /**
     * 查询所有产品
     * @return
     */
    List<ProductInfo> selectAllProduct();

    /**
     * 关键字查询
     * @param
     * @return
     */
    List<ProductInfo> selectAllProductByKeyWord(String productName);
}