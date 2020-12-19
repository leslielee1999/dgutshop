package org.dgutstu.dgutshop.db.service;

import org.dgutstu.dgutshop.db.dao.DgutshopCartMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopCart;
import org.dgutstu.dgutshop.db.domain.DgutshopCartExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 18:45 2020/12/18
 */
@Service
public class DgutshopCartService {
    @Resource
    private DgutshopCartMapper cartMapper;

    public List<DgutshopCart> queryByUidAndChecked(Integer userId){
        DgutshopCartExample example = new DgutshopCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return cartMapper.selectByExample(example);
    }

    public DgutshopCart findById(Integer id){
        return cartMapper.selectByPrimaryKey(id);
    }
}
