package edu.xd.bdilab.iotplatform.security;

import edu.xd.bdilab.iotplatform.controller.security.handler.*;
import edu.xd.bdilab.iotplatform.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.HeaderWriterFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HeaderWriter headerWriter = new HeaderWriter() {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                response.setHeader("X-Frame-Options", "ALLOWALL");
            }
        };
        List headerWriters = new ArrayList<>();
        headerWriters.add(headerWriter);
        HeaderWriterFilter headerWriterFilter = new HeaderWriterFilter(headerWriters);
        http.addFilter(headerWriterFilter);

        http
                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html","/swagger-resources/**","/images/**","/webjars/**","/v2/api-docs","/configuration/ui","/configuration/security","/configuration/security").permitAll()
                .antMatchers("/product/**","/user/**","/rule/**","/device/**").permitAll()
                .antMatchers("/license/**","/auth/**","/index.html").permitAll()

                .anyRequest().authenticated()//其他URL需要身份认证
                .and()

                .formLogin() //开启登录
                .loginPage("/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

//                .failureUrl("/login?error")
//                .successForwardUrl("/home"); //登录成功的URL


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
