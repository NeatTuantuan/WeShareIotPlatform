package edu.xd.bdilab.iotplatform.dao;

public class Role {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_id
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_name
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_desc
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    private String roleDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_id
     *
     * @return the value of role.role_id
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_id
     *
     * @param roleId the value for role.role_id
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_name
     *
     * @return the value of role.role_name
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_name
     *
     * @param roleName the value for role.role_name
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_desc
     *
     * @return the value of role.role_desc
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_desc
     *
     * @param roleDesc the value for role.role_desc
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}