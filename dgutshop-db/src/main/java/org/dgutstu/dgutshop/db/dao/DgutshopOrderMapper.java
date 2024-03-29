package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.domain.DgutshopOrderExample;

public interface DgutshopOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    long countByExample(DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int insert(DgutshopOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    DgutshopOrder selectOneByExample(DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    DgutshopOrder selectOneByExampleSelective(@Param("example") DgutshopOrderExample example, @Param("selective") DgutshopOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    List<DgutshopOrder> selectByExampleSelective(@Param("example") DgutshopOrderExample example, @Param("selective") DgutshopOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    List<DgutshopOrder> selectByExample(DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    DgutshopOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    DgutshopOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    DgutshopOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopOrder record, @Param("example") DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopOrder record, @Param("example") DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}