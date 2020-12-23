package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.admin.service.AdminToppingService;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.service.DgutshopToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:24 2020/12/18
 */
@RestController
@RequestMapping("/admin/toppings")
@Validated  //完成前后台交互的HTTP参数校验
//自定义两个校验注解@Sort与@Order
public class AdminToppingController {
    @Autowired
    private AdminToppingService toppingService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(Integer toppingId, String code, String name, Byte status,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        return toppingService.list(toppingId, code, name, status, start, end, page, limit, sort, order);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Object create(@RequestBody DgutshopTopping topping){
        return toppingService.create(topping);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/delete")
    public Object delete(@RequestParam Integer id){
        return toppingService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object update(@RequestBody DgutshopTopping topping){
        return toppingService.update(topping);
    }
}
