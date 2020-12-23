package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopStorage;
import org.dgutstu.dgutshop.db.domain.DgutshopStorageExample;

public interface DgutshopStorageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    long countByExample(DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int insert(DgutshopStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    DgutshopStorage selectOneByExample(DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    DgutshopStorage selectOneByExampleSelective(@Param("example") DgutshopStorageExample example, @Param("selective") DgutshopStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    List<DgutshopStorage> selectByExampleSelective(@Param("example") DgutshopStorageExample example, @Param("selective") DgutshopStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    List<DgutshopStorage> selectByExample(DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    DgutshopStorage selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    DgutshopStorage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    DgutshopStorage selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopStorage record, @Param("example") DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopStorage record, @Param("example") DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}