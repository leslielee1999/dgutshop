package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopAddressMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopAddress;
import org.dgutstu.dgutshop.db.domain.DgutshopAddressExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:用户地址业务
 * @Date: Create in 11:23 2020/12/18
 */
@Service
public class DgutshopAddressService {

    @Resource
    private DgutshopAddressMapper addressMapper;

    public List<DgutshopAddress> querySelective(Integer userId, String wechatId, String userName, String phone, LocalDateTime start, LocalDateTime end,
                                                Integer page, Integer limit, String sort, String order){
        DgutshopAddressExample example = new DgutshopAddressExample();
        DgutshopAddressExample.Criteria criteria = example.createCriteria();

        //  查找某个用户的地址
        if(userId != null){
            criteria.andUserIdEqualTo(userId);
        }

        if(!StringUtils.isEmpty(wechatId)){
            criteria.andWechatIdLike("%" + wechatId + "%");
        }

        //  查找收货人
        if(!StringUtils.isEmpty(userName)){
            criteria.andUserNameLike("%" + userName + "%");
        }

        //  查找收货手机号码
        if(!StringUtils.isEmpty(phone)){
            criteria.andUserPhoneLike("%" + phone + "%");
        }

        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }

        //  判断是否为逻辑删除的地址
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }



        //  设置分页
        PageHelper.startPage(page, limit);
        return addressMapper.selectByExample(example);
    }

    public DgutshopAddress query(Integer userId, Integer id){
        DgutshopAddressExample example = new DgutshopAddressExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);
    }
}
