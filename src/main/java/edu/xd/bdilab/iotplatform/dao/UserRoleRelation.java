package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

@Data
public class UserRoleRelation {
    /**
     * 用户角色关系id
     */
    private Integer relationId;

    /**
     * 用户id
     */
    private Integer fkUserId;

    /**
     * 角色id
     */
    private Integer fkRoleId;
}