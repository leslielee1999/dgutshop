package org.dgutstu.dgutshop.db.service;

import org.dgutstu.dgutshop.db.dao.DgutshopDeliveryMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.domain.DgutshopCategoryExample;
import org.dgutstu.dgutshop.db.domain.DgutshopDelivery;
import org.dgutstu.dgutshop.db.domain.DgutshopDeliveryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 17:02 2020/12/19
 */
@Service
public class DgutshopDeliveryService {

    @Resource
    private DgutshopDeliveryMapper deliveryMapper;

    /**
     * 列出所有配送员
     * @return
     */
    public List<DgutshopDelivery> list(){
        DgutshopDeliveryExample example = new DgutshopDeliveryExample();
        example.or().andLogicalDeleted(false);
        return deliveryMapper.selectByExample(example);
    }
}
