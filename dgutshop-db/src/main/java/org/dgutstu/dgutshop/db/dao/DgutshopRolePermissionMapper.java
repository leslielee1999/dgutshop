package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopRolePermission;
import org.dgutstu.dgutshop.db.domain.DgutshopRolePermissionExample;

public interface DgutshopRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    long countByExample(DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int insert(DgutshopRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    DgutshopRolePermission selectOneByExample(DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    DgutshopRolePermission selectOneByExampleSelective(@Param("example") DgutshopRolePermissionExample example, @Param("selective") DgutshopRolePermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    List<DgutshopRolePermission> selectByExampleSelective(@Param("example") DgutshopRolePermissionExample example, @Param("selective") DgutshopRolePermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    List<DgutshopRolePermission> selectByExample(DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    DgutshopRolePermission selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopRolePermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    DgutshopRolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    DgutshopRolePermission selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopRolePermission record, @Param("example") DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopRolePermission record, @Param("example") DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_role_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}