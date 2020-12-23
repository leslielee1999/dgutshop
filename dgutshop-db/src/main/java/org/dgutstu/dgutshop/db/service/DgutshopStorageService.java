package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopStorageMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopStorage;
import org.dgutstu.dgutshop.db.domain.DgutshopStorageExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 23:22 2020/12/22
 */
@Service
public class DgutshopStorageService {
    @Resource
    private DgutshopStorageMapper storageMapper;


    public void deleteByKey(String key) {
        DgutshopStorageExample example = new DgutshopStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(DgutshopStorage storageInfo) {
        storageInfo.setCreateTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public DgutshopStorage findByKey(String key) {
        DgutshopStorageExample example = new DgutshopStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(DgutshopStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public DgutshopStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<DgutshopStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        DgutshopStorageExample example = new DgutshopStorageExample();
        DgutshopStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
