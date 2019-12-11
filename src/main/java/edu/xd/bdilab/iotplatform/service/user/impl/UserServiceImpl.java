package edu.xd.bdilab.iotplatform.service.user.impl;

import edu.xd.bdilab.iotplatform.dao.auth.User;
import edu.xd.bdilab.iotplatform.dao.auth.UserRoleRelation;
import edu.xd.bdilab.iotplatform.mapper.UserMapper;
import edu.xd.bdilab.iotplatform.mapper.UserRoleRelationMapper;
import edu.xd.bdilab.iotplatform.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/27 20:38
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public int userRegister(User user) {
        if (userMapper.selectByUserName(user.getUserName()) != null){
            return 0;
        }else {
            userMapper.insertUser(user);

            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setFkRoleId(1);
            userRoleRelation.setFkUserId(
                    userMapper.selectByUserName(user.getUserName()).getUserId()
            );
            userRoleRelationMapper.insertUserRoleRelation(userRoleRelation);
        }

        return 1;
    }
}
