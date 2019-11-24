package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.DeviceData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName DeviceDataMapper
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/19 20:15
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Mapper
@Component
public interface DeviceDataMapper {
    DeviceData selectById(Integer id);

    int insertSelective(DeviceData deviceData);

    List<DeviceData> selectByTime(@Param("param") Map<String,String> params);

    List<DeviceData> selectAll(@Param("gatewayId")String gatewayId);
}
