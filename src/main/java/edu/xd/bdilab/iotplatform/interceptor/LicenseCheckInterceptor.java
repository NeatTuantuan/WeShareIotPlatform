package edu.xd.bdilab.iotplatform.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.service.license.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 11:16
 * @Version 1.0
 **/
@Component
public class LicenseCheckInterceptor implements HandlerInterceptor {
    @Autowired
    LicenseService licenseService;

    @Autowired
    private ObjectMapper mapper;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String,String> data = licenseService.checkLicense(licenseService.decryptLicense());

        String isAccessible = "isAccessible";
        String isTrue = "true";

        if(data ==null){
            ResponseResult result = new ResponseResult();
            result.setCode("403");
            result.setSuccess(false);
            result.setMessage("license文件解析失败" );
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        if(data.containsKey(isAccessible)){
            if(data.get(isAccessible).equals(isTrue)){
                return true;
            }
        }
        ResponseResult result = new ResponseResult();
        result.setCode("402");
        result.setSuccess(false);
        result.setMessage("license文件失效" );
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(mapper.writeValueAsString(result));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
