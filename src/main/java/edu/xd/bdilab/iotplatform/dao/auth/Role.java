package edu.xd.bdilab.iotplatform.dao.auth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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