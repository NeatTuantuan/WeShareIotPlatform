package edu.xd.bdilab.iotplatform.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/12/16 17:34
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper mapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截器方法
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("user_id");

        logger.info("preHandle----" + user + " ::: " + httpServletRequest.getRequestURL());

        if (user == null) {
            ResponseResult result = new ResponseResult();
            result.setCode("402");
            result.setSuccess(false);
            result.setMessage("未登录");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
