package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.SwitchLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @InterfaceName SwitchLogMapper
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/4 19:34
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Mapper
@Component
public interface SwitchLogMapper {
    SwitchLog selectById(int id);
}
