package org.dgutstu.dgutshop.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.admin.service.AdminProductService;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:后台管理系统的产品控制层
 * @Date: Create in 16:07 2020/12/12
 */
@RestController
@RequestMapping("/admin/products")
@Validated  //完成前后台交互的HTTP参数校验
//自定义两个校验注解@Sort与@Order
public class AdminProductController {
    private final Log logger = LogFactory.getLog(AdminProductController.class);
    @Autowired
    private AdminProductService productService;

    @Autowired
    private DgutshopProductService dgutshopProductService;

    /**
     * 查询饮品
     * @param productId
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(Integer productId, String name,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        return productService.list(productId, name, start, end, page, limit, sort, order);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getAllProduct")
    public Object getAllProduct() {
        List<DgutshopProduct> productList = dgutshopProductService.list();
        return productList;
    }

    /**
     * 新增饮品
     * @param product
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Object create(@RequestBody DgutshopProduct product){
        System.out.println(product.getBigOriginalPrice());
        return productService.create(product);
    }

    /**
     * 删除饮品
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/delete")
    public Object delete(@RequestParam Integer id){
        System.out.println(id);
        return productService.delete(id);
    }

    /**
     * 编辑饮品
     * @param product
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object update(@RequestBody DgutshopProduct product){
        return productService.update(product);
    }
}
