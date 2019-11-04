package edu.xd.bdilab.iotplatform.service;

import edu.xd.bdilab.iotplatform.dao.Role;

/**
 * @InterfaceName RoleService
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/4 20:24
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public interface RoleService {
    Role findRoleById(int role_id);
}
