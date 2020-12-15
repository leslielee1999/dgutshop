package org.dgutstu.dgutshop.db.service;


import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopProductMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.domain.DgutshopProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:15 2020/12/11
 */
@Service
public class DgutshopProductService {

    @Resource
    private DgutshopProductMapper productMapper;

    public List<DgutshopProduct> list(){
        DgutshopProductExample example = new DgutshopProductExample();
        return productMapper.selectByExample(example);
    }

    public List<DgutshopProduct> querySelective(Integer productId, String name,
                                                Integer page, Integer limit, String sort, String order){
        DgutshopProductExample example = new DgutshopProductExample();
        DgutshopProductExample.Criteria criteria = example.createCriteria();

        //  判断是否为某个特定饮品
        if(productId != null){
            criteria.andIdEqualTo(productId);
        }

        //  判断是否为模糊查询
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        //  判断是否为逻辑删除的饮品
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);
        return productMapper.selectByExample(example);
    }

    /**
     * 增加一种饮品
     * @param product
     */
    public void add(DgutshopProduct product){
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        productMapper.insertSelective(product);
    }

    /**
     * 删除一种饮品
     * @param id
     */
    public void deleteById(Integer id){
        productMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 编辑一种饮品信息
     * @param product
     * @return
     */
    public int update(DgutshopProduct product){
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public boolean checkExistByName(String name){
        DgutshopProductExample example = new DgutshopProductExample();
        example.or().andNameEqualTo(name).andStatusEqualTo(true).andDeletedEqualTo(false);
        return productMapper.countByExample(example) != 0;
    }

    /**
     * 根据产品id获取某个产品
     * @param id
     * @return
     */
    public DgutshopProduct get(int id){
        return productMapper.selectByPrimaryKey(id);
    }

}
