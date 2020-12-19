package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.service.DgutshopToppingService;
import org.dgutstu.dgutshop.wechat.service.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 16:52 2020/12/18
 */
@RestController
@RequestMapping("/wechat/topping")
@Validated
public class WechatToppingController {

    @Autowired
    private DgutshopToppingService toppingService;

    @GetMapping("/list")
    public Object list(){
        //  优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.TOPPINGS)){
            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.TOPPINGS));
        }
        return toppingService.list();
    }
}
