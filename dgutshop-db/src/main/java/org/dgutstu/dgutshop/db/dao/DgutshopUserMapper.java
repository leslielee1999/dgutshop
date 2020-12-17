package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.domain.DgutshopUserExample;

public interface DgutshopUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    long countByExample(DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int insert(DgutshopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    DgutshopUser selectOneByExample(DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    DgutshopUser selectOneByExampleSelective(@Param("example") DgutshopUserExample example, @Param("selective") DgutshopUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    List<DgutshopUser> selectByExampleSelective(@Param("example") DgutshopUserExample example, @Param("selective") DgutshopUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    List<DgutshopUser> selectByExample(DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    DgutshopUser selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    DgutshopUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    DgutshopUser selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopUser record, @Param("example") DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopUser record, @Param("example") DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_user
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}