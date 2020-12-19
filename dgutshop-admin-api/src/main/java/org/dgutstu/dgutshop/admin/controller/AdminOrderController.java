package org.dgutstu.dgutshop.admin.controller;

import org.checkerframework.checker.units.qual.A;
import org.dgutstu.dgutshop.admin.service.AdminOrderService;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.service.DgutshopOrderItemService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description: 订单状态 【101待支付、201制作中、301待取货、401派送中、501已完成】
 * @Date: Create in 9:14 2020/12/16
 */
@RestController
@RequestMapping("/admin/order")
@Validated  //完成前后台交互的HTTP参数校验
//自定义两个校验注解@Sort与@Order
public class AdminOrderController {
    @Autowired
    private AdminOrderService adminOrderService;
    @Autowired
    private DgutshopOrderItemService orderItemService;
    /**
     * 查询订单
     * @param nickname 用户昵称
     * @param consignee 收货人名称
     * @param code 订单编号
     * @param start：订单创建时间
     * @param end：订单结束时间
     * @param orderStatusArray
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/list")
    public Object list(String nickname, String consignee, String code,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(required = false) List<Short> orderStatusArray,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        return adminOrderService.list(nickname, consignee, code, start, end, orderStatusArray, page, limit, sort, order);
    }

    /**
     * 发货
     *
     * @param body 订单信息，{ orderId：xxx, orderCode: xxx, , deliverymanName：xxx, deliverymanPhone：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/delivery")
    public Object delivery(@RequestBody String body) {
        return adminOrderService.delivery(body);
    }


}
