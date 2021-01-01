package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.OrderListVo;
import org.dgutstu.dgutshop.wechat.annotation.LoginUser;
import org.dgutstu.dgutshop.wechat.service.WechatAuthService;
import org.dgutstu.dgutshop.wechat.service.WechatOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

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
    @Autowired
    private WechatAuthService wechatAuthService;
    /**
     * 订单列表
     * @return
     */
    @GetMapping("list")
    public Object list(@NotNull HttpServletRequest request,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        Object obj = wechatAuthService.validate(request);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
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
     *      1. 在订单状态为待支付状态下支持用户点击取消订单
     *          101【待支付】-->102【下单后未支付用户取消】
     *      2. 在订单状态为待支付状态下超时，系统自动取消
     *          101【待支付】-->103【下单后未支付超时，系统自动取消】
     *
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public Object cancel(@NotNull HttpServletRequest request, @RequestBody String body) {
        Object obj = wechatAuthService.validate(request);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        return wechatOrderService.cancel(userId, body);
    }
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
     *      1. 完成用户点击确认收货的操作
     *          301【待取货】-->501【已完成】
     *          402【骑手已到达】-->501【已完成】
     *      2. 另外，待取货或骑手到达后的时间阈值一旦超过则自动确认收货：
     *          301【待取货】-->502【已完成】
     *          402【骑手已到达】-->502【已完成】
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("confirm")
    public Object confirm(@NotNull HttpServletRequest request, @RequestBody String body) {
        Object obj = wechatAuthService.validate(request);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        return wechatOrderService.confirm(userId, body);
    }

    /**
     *      1、用户收货地址crud，设置默认（增加、修改时）
     *      TODO：
     *          微信小程序登录
     *
     */

    /**
     * 删除订单
     *
     *      1. 如果订单已经取消【102/103】或是已完成【501/502】，则可删除
     *      2. 如果订单已经支付，且已经收货，则可删除
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    public Object delete(@NotNull HttpServletRequest request,  @RequestBody String body) {
        Object obj = wechatAuthService.validate(request);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        return wechatOrderService.delete(userId, body);
    }

    /**
     * 模拟付款
     * @param body
     * @return
     */
    @PostMapping("pay")
    public Object pay(@NotNull HttpServletRequest request,  @RequestBody String body){
        Object obj = wechatAuthService.validate(request);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        return wechatOrderService.pay(userId, body);
    }

}
