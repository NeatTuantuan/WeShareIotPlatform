package edu.xd.bdilab.iotplatform.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRelationMapperTest {
    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;
    @Test
    public void selectRoleIdByUserId() {
        System.out.println(userRoleRelationMapper.selectRoleIdByUserId(1));
    }
}