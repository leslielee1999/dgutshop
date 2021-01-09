package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopAddress;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.service.DgutshopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:用户地址信息只有查询功能
 * @Date: Create in 11:20 2020/12/18
 */
@RestController
@RequestMapping("/admin/address")
@Validated  //完成前后台交互的HTTP参数校验
//自定义两个校验注解@Sort与@Order
public class AdminAddressController {

    @Autowired
    private DgutshopAddressService addressService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(Integer userId, String wechatId, String userName, String phone,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        List<DgutshopAddress> addressList = addressService.querySelective(userId, wechatId, userName, phone, start, end, page, limit, sort, order);
        return ResponseUtil.okList(addressList);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getAllAddress")
    public Object getAllAddress() {
        List<DgutshopAddress> addressList = addressService.list();
        return addressList;
    }
}
