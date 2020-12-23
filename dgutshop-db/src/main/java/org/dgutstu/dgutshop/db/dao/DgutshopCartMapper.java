package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopCart;
import org.dgutstu.dgutshop.db.domain.DgutshopCartExample;

public interface DgutshopCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    long countByExample(DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int insert(DgutshopCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    DgutshopCart selectOneByExample(DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    DgutshopCart selectOneByExampleSelective(@Param("example") DgutshopCartExample example, @Param("selective") DgutshopCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    List<DgutshopCart> selectByExampleSelective(@Param("example") DgutshopCartExample example, @Param("selective") DgutshopCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    List<DgutshopCart> selectByExample(DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    DgutshopCart selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    DgutshopCart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    DgutshopCart selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopCart record, @Param("example") DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopCart record, @Param("example") DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}