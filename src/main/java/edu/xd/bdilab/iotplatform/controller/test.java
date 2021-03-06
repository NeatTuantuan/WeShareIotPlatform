package edu.xd.bdilab.iotplatform.controller;

import edu.xd.bdilab.iotplatform.mapper.UserMapper;
import edu.xd.bdilab.iotplatform.service.user.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName test
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/4 20:26
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@RestController
public class test {
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    UserMapper userMapper;

    @GetMapping(value = "/test")
    public String test() {
        String temp = userMapper.selectByUserName("user").getPassword();
        return temp;
    }
}
