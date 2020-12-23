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
     * 取消订单
     *  TODO
     *      1. 在订单状态为待支付状态下支持用户点击取消订单
     *          101【待支付】-->102【下单后未支付用户取消】
     *      2. 在订单状态为待支付状态下超时，系统自动取消
     *          101【待支付】-->103【下单后未支付超时，系统自动取消】
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
//    @PostMapping("cancel")
//    public Object cancel(@LoginUser Integer userId, @RequestBody String body) {
//        return wechatOrderService.cancel(userId, body);
//    }
//
    /**
     * 支付功能
     * TODO
     *      1. 完成微信支付
     *      2. 支付后订单状态改变
     *          101【待支付】-->201【制作中】
     *
     */


    /**
     * 确认收货
     *  TODO
     *      1. 完成用户点击确认收货的操作
     *          301【待取货】-->501【已完成】
     *          402【骑手已到达】-->501【已完成】
     *      2. 另外，待取货或骑手到达后的时间阈值一旦超过则自动确认收货：
     *          301【待取货】-->502【已完成】
     *          402【骑手已到达】-->502【已完成】
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
//    @PostMapping("confirm")
//    public Object confirm(@LoginUser Integer userId, @RequestBody String body) {
//        return wechatOrderService.confirm(userId, body);
//    }



}