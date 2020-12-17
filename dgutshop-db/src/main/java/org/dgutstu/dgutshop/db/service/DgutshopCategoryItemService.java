package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopCategoryItemMapper;
import org.dgutstu.dgutshop.db.dao.DgutshopProductMapper;
import org.dgutstu.dgutshop.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:50 2020/12/15
 */
@Service
public class DgutshopCategoryItemService {

    @Resource
    private DgutshopCategoryItemMapper categoryItemMapper;
    @Autowired
    private DgutshopProductService productService;
    @Autowired
    private DgutshopCategoryService categoryService;

    /**
     * 增加一个分类单项
     * @param categoryItem
     */
    public void add(DgutshopCategoryItem categoryItem){
        categoryItem.setCreateTime(LocalDateTime.now());
        categoryItem.setUpdateTime(LocalDateTime.now());
        categoryItemMapper.insertSelective(categoryItem);
    }

    /**
     * 删除第id个分类单项
     * @param id
     */
    public void deleteById(Integer id){
        categoryItemMapper.logicalDeleteByPrimaryKey(id);
    }


    /**
     * 根据index查询某个分类单项
     * @param index
     * @return
     */
    public List<DgutshopCategoryItem> findByIndex(Integer index){
        DgutshopCategoryItemExample example = new DgutshopCategoryItemExample();
        example.or().andIndexEqualTo(index).andDeletedEqualTo(false);
        return categoryItemMapper.selectByExample(example);
    }

    public void fill(List<DgutshopCategory> categoryList){
        for (DgutshopCategory category : categoryList) {
            fill(category);
        }
    }

    /**
     * 完成菜单分类与其分类项一对多关系的实现
     * @param category
     */
    public void fill(DgutshopCategory category){
        // 根据分类id找出归属于它的所有分类项
        DgutshopCategoryItemExample example = new DgutshopCategoryItemExample();
        example.createCriteria().andCidEqualTo(category.getId());
        example.setOrderByClause("id desc");
        List<DgutshopCategoryItem> categoryItems = categoryItemMapper.selectByExample(example);
        //  为所有分类项设置饮品属性
        setProduct(categoryItems);
        category.setCategoryItems(categoryItems);
    }


    public void setProduct(List<DgutshopCategoryItem> categoryItems){
        for (DgutshopCategoryItem categoryItem : categoryItems) {
            setProduct(categoryItem);
        }
    }

    public void setProduct(DgutshopCategoryItem categoryItem){
        DgutshopProduct product = productService.get(categoryItem.getPid());
        categoryItem.setProduct(product);
    }

    /**
     * 编辑一种分类单项
     * @param categoryItem
     * @return
     */
    public int update(DgutshopCategoryItem categoryItem){
        categoryItem.setUpdateTime(LocalDateTime.now());
        return categoryItemMapper.updateByPrimaryKeySelective(categoryItem);
    }
}
