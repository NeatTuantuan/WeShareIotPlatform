package edu.xd.bdilab.iotplatform.service.security;

import edu.xd.bdilab.iotplatform.mapper.RoleMapper;
import edu.xd.bdilab.iotplatform.mapper.UserMapper;
import edu.xd.bdilab.iotplatform.mapper.UserRoleRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 10:01
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {



        return null;
    }
}
