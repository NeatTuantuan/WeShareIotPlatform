package edu.xd.bdilab.iotplatform.service.security;

import edu.xd.bdilab.iotplatform.dao.auth.Role;
import edu.xd.bdilab.iotplatform.dao.auth.User;
import edu.xd.bdilab.iotplatform.mapper.RoleMapper;
import edu.xd.bdilab.iotplatform.mapper.UserMapper;
import edu.xd.bdilab.iotplatform.mapper.UserRoleRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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

    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(userName);

        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        Role role = roleMapper.findRoleById(userRoleRelationMapper.selectRoleIdByUserId(user.getUserId()));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        logger.info("role:"+role.getRoleName());
        logger.info("userName:"+userName);
        logger.info("userId:"+user.getUserId());
        logger.info("userName:"+user.getUserName()+"  password:"+new BCryptPasswordEncoder().encode(user.getPassword()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), grantedAuthorities
        );
    }
}
