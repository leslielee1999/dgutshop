package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopDelivery;
import org.dgutstu.dgutshop.db.service.DgutshopDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 16:59 2020/12/19
 */
@RestController
@RequestMapping("/admin/delivery")
@Validated
public class AdminDeliveryController {

    @Autowired
    private DgutshopDeliveryService deliveryService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(){
        List<DgutshopDelivery> list = deliveryService.list();
        return ResponseUtil.okList(list);
    }
}
