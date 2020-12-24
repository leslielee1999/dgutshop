package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.admin.service.AdminOrderService;
import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.service.DgutshopOrderItemService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:47 2020/12/24
 */
@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {

    @Autowired
    private DgutshopOrderService orderService;

    private static short UNPAID = 101;
    private static short PRODUCING = 201;
    private static short UNTAKEN = 301;
    private static short DELIVERING = 401;
    private static short FINISHED = 501;
    /**
     * 查询订单
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(){
        Map<String, Object> data = new HashMap<String, Object>();
        List<DgutshopOrder> orders = orderService.list();       //  获取前7天的订单
        int unpaid = 0;
        int producing = 0;
        int untaken = 0;
        int delivering = 0;
        int finished = 0;
        for (DgutshopOrder order : orders) {
            if (order.getOrderStatus() == UNPAID){
                unpaid++;
            }else if (order.getOrderStatus() == PRODUCING){
                producing++;
            }else if (order.getOrderStatus() == UNTAKEN){
                untaken++;
            }else if (order.getOrderStatus() == DELIVERING){
                delivering++;
            }else if (order.getOrderStatus() == FINISHED){
                finished++;
            }
        }
        data.put("unpaid", unpaid);
        data.put("producting", producing);
        data.put("untaken", untaken);
        data.put("delivering", delivering);
        data.put("finished", finished);
        return ResponseUtil.ok(data);
    }

}
