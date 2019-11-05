package edu.xd.bdilab.iotplatform.security;

import edu.xd.bdilab.iotplatform.controller.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 9:49
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    UserDetailsService userService() {
        return new UserDetailsServiceImpl();
    }
}
