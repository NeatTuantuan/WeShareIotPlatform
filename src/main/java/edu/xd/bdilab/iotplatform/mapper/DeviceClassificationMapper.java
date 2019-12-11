package edu.xd.bdilab.iotplatform.mapper;


import edu.xd.bdilab.iotplatform.dao.DeviceClassification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceClassificationMapper {
    int insertSelective(DeviceClassification deviceClassification);

    List<Integer> selectCategoryByDeviceId(String deviceId);

    int updateCategoryById(DeviceClassification deviceClassification);

    DeviceClassification selectById(int id);

    int deleteDeviceCategory(String fkDeviceId,int fkCategoryId);

    int deleteByCategoryId(int fkCategoryId);
}
