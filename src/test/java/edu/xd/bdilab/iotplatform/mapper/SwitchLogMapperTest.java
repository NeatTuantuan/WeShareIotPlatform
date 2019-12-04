package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.SwitchLog;
import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import edu.xd.bdilab.iotplatform.netty.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class )
public class SwitchLogMapperTest {
    @Autowired
    SwitchLogMapper switchLogMapper;
    @Test
    public void selectById() {
        System.out.println(DateUtil.stringToDate(DateUtil.getDate()));

    }

    @Test
    public void insert(){
        SwitchLog switchLog = new SwitchLog();
        switchLog.setFkDeviceId("1");
        switchLog.setStartTime(DateUtil.stringToDate("2019-11-26 21:10:10"));
        switchLog.setEndTime(DateUtil.stringToDate(DateUtil.getDate()));

        System.out.println(switchLogMapper.insertSelective(switchLog));
    }
}