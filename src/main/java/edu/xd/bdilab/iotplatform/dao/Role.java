package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

@Data
public class Role {
    /**
     * 角色id，主键
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;
}