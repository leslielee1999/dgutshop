package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopUserMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.domain.DgutshopUserExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 10:07 2020/12/16
 */
@Service
public class DgutshopUserService {

    @Resource
    private DgutshopUserMapper userMapper;

    public DgutshopUser findById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int update(DgutshopUser user){
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<DgutshopUser> querySelective(Integer id, String name, String phone, String wechatId, String nickname, LocalDateTime start, LocalDateTime end,
                                             Integer page, Integer size, String sort, String order){
        DgutshopUserExample example = new DgutshopUserExample();
        DgutshopUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)){
            criteria.andIdEqualTo(id);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }
        if (!StringUtils.isEmpty(wechatId)) {
            criteria.andOpenIdLike("%" + wechatId + "%");
        }
        if (!StringUtils.isEmpty(nickname)) {
            criteria.andNicknameLike("%" + nickname + "%");
        }
        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);
    }

    public DgutshopUser findByOpenId(String openid){
        DgutshopUserExample example = new DgutshopUserExample();
        DgutshopUserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openid).andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    public void save(DgutshopUser user){
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    public Long countUser(){
        DgutshopUserExample example = new DgutshopUserExample();
        DgutshopUserExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return userMapper.countByExample(example);
    }
}
