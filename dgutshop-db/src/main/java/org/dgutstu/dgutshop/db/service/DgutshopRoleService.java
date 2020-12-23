package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopRoleMapper;
import org.dgutstu.dgutshop.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:18 2020/12/15
 */
@Service
public class DgutshopRoleService {

    @Autowired
    private DgutshopRoleMapper roleMapper;

    /**
     * 列出角色可根据角色名查询）
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<DgutshopRole> querySelective(String name, LocalDateTime start, LocalDateTime end, Integer page, Integer limit, String sort, String order){
        DgutshopRoleExample example = new DgutshopRoleExample();
        DgutshopRoleExample.Criteria criteria = example.createCriteria();

        //  判断是否为模糊查询
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }

        //  判断是否为逻辑删除的角色
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public boolean checkExistByName(String name){
        DgutshopRoleExample example = new DgutshopRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public void add(DgutshopRole role){
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void update(DgutshopRole role){
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 删除第id个角色
     * @param id
     */
    public void deleteById(Long id){
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public DgutshopRole findById(Long id){
        return roleMapper.selectByPrimaryKey(id);
    }
}
