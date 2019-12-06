package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleRelationMapper {

    int selectRoleIdByUserId(int user_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    int deleteByPrimaryKey(Integer relationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    int insert(UserRoleRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    int insertSelective(UserRoleRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    UserRoleRelation selectByPrimaryKey(Integer relationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    int updateByPrimaryKeySelective(UserRoleRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_relation
     *
     * @mbggenerated Mon Nov 04 20:19:41 CST 2019
     */
    int updateByPrimaryKey(UserRoleRelation record);

    int insertUserRoleRelation(UserRoleRelation userRoleRelation);
}