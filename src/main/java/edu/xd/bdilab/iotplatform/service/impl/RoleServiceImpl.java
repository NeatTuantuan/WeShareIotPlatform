package edu.xd.bdilab.iotplatform.service.impl;

import edu.xd.bdilab.iotplatform.dao.Role;
import edu.xd.bdilab.iotplatform.mapper.RoleMapper;
import edu.xd.bdilab.iotplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/4 20:25
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Role findRoleById(int role_id) {
        return roleMapper.findRoleById(role_id);
    }
}
