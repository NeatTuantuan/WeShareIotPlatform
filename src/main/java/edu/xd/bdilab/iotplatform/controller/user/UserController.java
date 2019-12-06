package edu.xd.bdilab.iotplatform.controller.user;

import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.User;
import edu.xd.bdilab.iotplatform.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/27 19:41
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@RestController
@Api(tags = {"用户相关功能"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ResponseResult responseResult;

    @PostMapping(value = "user/userRegister")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "用户名"),
        @ApiImplicitParam(name = "password", value = "密码"),
    })
    @ApiOperation(value = "用户注册")
    public ResponseResult userRegister(@RequestParam String userName, @RequestParam String password){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        if (userService.userRegister(user) == 0){
            return new ResponseResult(false,"001","该用户已存在",null);
        }else {
            responseResult.setSuccess(true);
            responseResult.setCode("001");
            responseResult.setMessage("用户注册成功");
        }
       return responseResult;
    }
}
