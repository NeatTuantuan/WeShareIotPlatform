package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.SwitchLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class )
public class SwitchLogMapperTest {
    @Autowired
    SwitchLogMapper switchLogMapper;
    @Test
    public void selectById() {
        System.out.println(switchLogMapper.selectById(1));
    }
}