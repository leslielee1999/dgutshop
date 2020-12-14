package org.dgutstu.dgutshop.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 16:32 2020/12/12
 */

@Service
public class AdminProductService {
    private final Log logger = LogFactory.getLog(AdminProductService.class);

    @Autowired
    private DgutshopProductService productService;

    public Object list(Integer productId, String name,
                       Integer page, Integer limit, String sort, String order){
        List<DgutshopProduct> productList = productService.querySelective(productId, name, page, limit, sort, order);
        return ResponseUtil.okList(productList);
    }

    //  校验添加饮品所提交数据
    private Object validate(DgutshopProduct dgutshopProduct){
        String name = dgutshopProduct.getName();//饮品名称
        if (StringUtils.isEmpty(name)){
            return ResponseUtil.badArgument();
        }
        String code = dgutshopProduct.getCode();//饮品编号
        if (StringUtils.isEmpty(code)){
            return ResponseUtil.badArgument();
        }

        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @Transactional
    public Object create(DgutshopProduct product){
        Object error = validate(product);
        if (error != null){
            return error;
        }
        productService.add(product);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object delete(Integer id){
        if (id == null){
            return ResponseUtil.badArgument();
        }
        productService.deleteById(id);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object update(DgutshopProduct product){
        Object error = validate(product);
        if (error != null){
            return error;
        }
        productService.update(product);
        return ResponseUtil.ok();
    }
}
