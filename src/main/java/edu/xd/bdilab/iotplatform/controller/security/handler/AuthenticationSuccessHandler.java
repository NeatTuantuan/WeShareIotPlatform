package edu.xd.bdilab.iotplatform.controller.security.handler;

import com.alibaba.fastjson.JSON;
import edu.xd.bdilab.iotplatform.controller.response.MetaData;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.controller.security.SecurityCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationSuccessHandler
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 9:57
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        MetaData metaData = new MetaData(true, SecurityCode.LOGIN_SUCCESSFUL.getCode(),SecurityCode.LOGIN_SUCCESSFUL.getMessage());
        MetaData metaData = new MetaData(true,"001","登录成功");
        ResponseResult responseResult = new ResponseResult(null,metaData);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
    }
}
