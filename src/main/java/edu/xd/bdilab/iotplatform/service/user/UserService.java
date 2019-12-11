package edu.xd.bdilab.iotplatform.service.user;

import edu.xd.bdilab.iotplatform.dao.auth.User;
import org.springframework.stereotype.Service;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/27 20:11
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Service
public interface UserService {
    int userRegister(User user);
}
