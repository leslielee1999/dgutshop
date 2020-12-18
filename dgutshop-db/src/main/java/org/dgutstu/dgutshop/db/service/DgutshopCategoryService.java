package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopCategoryMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopAdminExample;

import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.domain.DgutshopCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 11:28 2020/12/15
 */
@Service
public class DgutshopCategoryService {

    @Resource
    private DgutshopCategoryMapper categoryMapper;

    /**
     * 列出菜单分类（可根据菜单名查询）
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<DgutshopCategory> querySelective(String name, Integer index, LocalDateTime start, LocalDateTime end, Integer page, Integer limit, String sort, String order){
        DgutshopCategoryExample example = new DgutshopCategoryExample();
        DgutshopCategoryExample.Criteria criteria = example.createCriteria();

        //  判断是否为模糊查询
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        if (index != null){
            criteria.andIndexEqualTo(index);
        }

        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }

        //  匹配不为逻辑删除的分类
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);
        return categoryMapper.selectByExampleSelective(example);
    }


    /**
     * 列出菜单分类
     * @return
     */
    public List<DgutshopCategory> list(){
        DgutshopCategoryExample example = new DgutshopCategoryExample();
        return categoryMapper.selectByExample(example);
    }

    /**
     * 增加一种分类
     * @param category
     */
    public void add(DgutshopCategory category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    /**
     * 删除第id种分类
     * @param id
     */
    public void deleteById(Integer id){
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 编辑一种分类
     * @param category
     * @return
     */
    public int update(DgutshopCategory category){
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 根据id查询某种分类
     * @param id
     * @return
     */
    public DgutshopCategory get(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据index查询某种分类
     * @param index
     * @return
     */
    public List<DgutshopCategory> findByIndex(Integer index){
        DgutshopCategoryExample example = new DgutshopCategoryExample();
        example.or().andIndexEqualTo(index).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    /**
     * 返回分类名为name的分类
     * @param name
     * @return
     */
    public List<DgutshopCategory> findByName(String name){
        DgutshopCategoryExample example = new DgutshopCategoryExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

}
