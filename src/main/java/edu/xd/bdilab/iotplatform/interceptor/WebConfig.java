package edu.xd.bdilab.iotplatform.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 11:12
 * @Version 1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LicenseUploadInterceptor licenseUploadInterceptor;

    @Autowired
    private LicenseCheckInterceptor licenseCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
//        interceptorRegistry.addInterceptor(licenseUploadInterceptor).excludePathPatterns("/license/uploadLicense")
//                .excludePathPatterns("/license/generateCheckFile").excludePathPatterns("/auth/downloadCheckfile")
//                .excludePathPatterns("/swagger-ui.html","/swagger-resources/**","/images/**","/webjars/**","/v2/api-docs");
//        interceptorRegistry.addInterceptor(licenseCheckInterceptor).excludePathPatterns("/license/uploadLicense")
//                .excludePathPatterns("/license/generateCheckFile").excludePathPatterns("/auth/downloadCheckfile")
//                .excludePathPatterns("/swagger-ui.html","/swagger-resources/**","/images/**","/webjars/**","/v2/api-docs");
    }
}
