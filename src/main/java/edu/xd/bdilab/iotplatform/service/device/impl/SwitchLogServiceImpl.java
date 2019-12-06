package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.SwitchLog;
import edu.xd.bdilab.iotplatform.mapper.SwitchLogMapper;
import edu.xd.bdilab.iotplatform.service.device.SwitchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwitchLogServiceImpl implements SwitchLogService {

    @Autowired
    private SwitchLogMapper switchLogMapper;

    @Override
    public int insertSwitchLog(SwitchLog switchLog) {
        return switchLogMapper.insertSelective(switchLog);
    }
}
