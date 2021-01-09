package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopToppingMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.domain.DgutshopProductExample;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.domain.DgutshopToppingExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:小料业务
 * @Date: Create in 9:27 2020/12/18
 */
@Service
public class DgutshopToppingService {
    @Resource
    private DgutshopToppingMapper toppingMapper;

    public List<DgutshopTopping> list(){
        DgutshopToppingExample example = new DgutshopToppingExample();
        DgutshopToppingExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false).andStatusEqualTo(new Byte("1"));
        return toppingMapper.selectByExample(example);
    }

    public List<DgutshopTopping> querySelective(Integer toppingId, String code, String name, Byte status, LocalDateTime start, LocalDateTime end,
                                                Integer page, Integer limit, String sort, String order){
        DgutshopToppingExample example = new DgutshopToppingExample();
        DgutshopToppingExample.Criteria criteria = example.createCriteria();

        //  判断是否为某个特定小料
        if(toppingId != null){
            criteria.andIdEqualTo(toppingId);
        }

        //  判断是否为模糊查询
        if(!StringUtils.isEmpty(code)){
            criteria.andCodeLike("%" + code + "%");
        }

        //  判断是否为模糊查询
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        if(!StringUtils.isEmpty(status)){
            criteria.andStatusEqualTo(status);
        }

        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }

        //  判断是否为逻辑删除的饮品
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);
        return toppingMapper.selectByExample(example);
    }

    /**
     * 增加一种小料
     * @param topping
     */
    public void add(DgutshopTopping topping){
        topping.setCreateTime(LocalDateTime.now());
        topping.setUpdateTime(LocalDateTime.now());
        toppingMapper.insertSelective(topping);
    }

    /**
     * 删除一种小料
     * @param id
     */
    public void deleteById(Integer id){
        toppingMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 编辑一种小料
     * @param topping
     * @return
     */
    public int update(DgutshopTopping topping){
        topping.setUpdateTime(LocalDateTime.now());
        return toppingMapper.updateByPrimaryKeySelective(topping);
    }

    /**
     * 根据小料id获取某个小料
     * @param id
     * @return
     */
    public DgutshopTopping get(int id){
        return toppingMapper.selectByPrimaryKey(id);
    }

    public Long countTopping(){
        DgutshopToppingExample example = new DgutshopToppingExample();
        DgutshopToppingExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return toppingMapper.countByExample(example);
    }
}
