package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopUserRole;
import org.dgutstu.dgutshop.db.domain.DgutshopUserRoleExample;

public interface DgutshopUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    long countByExample(DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int insert(DgutshopUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    DgutshopUserRole selectOneByExample(DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    DgutshopUserRole selectOneByExampleSelective(@Param("example") DgutshopUserRoleExample example, @Param("selective") DgutshopUserRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    List<DgutshopUserRole> selectByExampleSelective(@Param("example") DgutshopUserRoleExample example, @Param("selective") DgutshopUserRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    List<DgutshopUserRole> selectByExample(DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    DgutshopUserRole selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopUserRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    DgutshopUserRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    DgutshopUserRole selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopUserRole record, @Param("example") DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopUserRole record, @Param("example") DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user_role
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}