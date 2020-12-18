package org.dgutstu.dgutshop.admin.service;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.dgutstu.dgutshop.db.service.DgutshopToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:43 2020/12/18
 */
@Service
public class AdminToppingService {


    @Autowired
    private DgutshopToppingService toppingService;

    public Object list(Integer toppingId, String code, String name, Byte status, LocalDateTime start, LocalDateTime end,
                       Integer page, Integer limit, String sort, String order){
        List<DgutshopTopping> toppingList = toppingService.querySelective(toppingId, code, name, status, start, end, page, limit, sort, order);
        return ResponseUtil.okList(toppingList);
    }

    //  校验添加饮小料所提交数据
    private Object validate(DgutshopTopping topping){
        String name = topping.getName();//小料名称
        if (StringUtils.isEmpty(name)){
            return ResponseUtil.badArgument();
        }
        String code = topping.getCode();//小料编号
        if (StringUtils.isEmpty(code)){
            return ResponseUtil.badArgument();
        }
        BigDecimal price = topping.getPrice();
        if (StringUtils.isEmpty(price)){
            return ResponseUtil.badArgument();
        }

        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @Transactional
    public Object create(DgutshopTopping topping){
        Object error = validate(topping);
        if (error != null){
            return error;
        }
        toppingService.add(topping);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object delete(Integer id){
        if (id == null){
            return ResponseUtil.badArgument();
        }
        toppingService.deleteById(id);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object update(DgutshopTopping topping){
        Object error = validate(topping);
        if (error != null){
            return error;
        }
        toppingService.update(topping);
        return ResponseUtil.ok();
    }
}
