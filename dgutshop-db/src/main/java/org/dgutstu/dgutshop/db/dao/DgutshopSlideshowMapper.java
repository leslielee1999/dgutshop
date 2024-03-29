package org.dgutstu.dgutshop.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshow;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshowExample;

public interface DgutshopSlideshowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    long countByExample(DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int deleteByExample(DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int insert(DgutshopSlideshow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int insertSelective(DgutshopSlideshow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    DgutshopSlideshow selectOneByExample(DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    DgutshopSlideshow selectOneByExampleSelective(@Param("example") DgutshopSlideshowExample example, @Param("selective") DgutshopSlideshow.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    List<DgutshopSlideshow> selectByExampleSelective(@Param("example") DgutshopSlideshowExample example, @Param("selective") DgutshopSlideshow.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    List<DgutshopSlideshow> selectByExample(DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    DgutshopSlideshow selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DgutshopSlideshow.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    DgutshopSlideshow selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    DgutshopSlideshow selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DgutshopSlideshow record, @Param("example") DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DgutshopSlideshow record, @Param("example") DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DgutshopSlideshow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DgutshopSlideshow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") DgutshopSlideshowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_slideshow
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}