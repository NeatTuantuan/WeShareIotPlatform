package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

@Data
public class User {
    /**
     * 用户id，主键
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}