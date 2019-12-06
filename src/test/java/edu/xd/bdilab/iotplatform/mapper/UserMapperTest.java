package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void selectByUserName() {
        System.out.println(userMapper.selectByUserName("user").getPassword());
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123456");
        System.out.println(userMapper.insertUser(user));
    }
}