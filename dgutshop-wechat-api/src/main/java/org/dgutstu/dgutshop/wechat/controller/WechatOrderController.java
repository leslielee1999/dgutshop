package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.OrderListVo;
import org.dgutstu.dgutshop.wechat.annotation.LoginUser;
import org.dgutstu.dgutshop.wechat.service.WechatOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 17:01 2020/12/18
 */
@RestController
@RequestMapping("/wechat/order")
@Validated
public class WechatOrderController {

    @Autowired
    private WechatOrderService wechatOrderService;

    /**
     * 订单列表
     * @param userId 用户ID
     * @return
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        return wechatOrderService.list(userId, sort, order);
    }

    /**
     * 提交订单
     * @param orderListVo 订单信息
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public Object submit(@RequestBody OrderListVo orderListVo){
        return wechatOrderService.submit(orderListVo);
    }

    /**
     * 确认收货
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
//    @PostMapping("confirm")
//    public Object confirm(@LoginUser Integer userId, @RequestBody String body) {
//        return wechatOrderService.confirm(userId, body);
//    }
}
