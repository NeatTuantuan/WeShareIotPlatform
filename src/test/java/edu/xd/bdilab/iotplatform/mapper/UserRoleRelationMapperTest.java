package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.UserRoleRelation;
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
    }

    @Test
    public void insertUserRoleRelation() {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setFkUserId(1);
        userRoleRelation.setFkRoleId(1);
        System.out.println(userRoleRelationMapper.insertUserRoleRelation(userRoleRelation));
    }
}