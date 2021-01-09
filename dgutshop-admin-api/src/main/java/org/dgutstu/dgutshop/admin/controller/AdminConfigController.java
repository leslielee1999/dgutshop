package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.system.SystemConfig;
import org.dgutstu.dgutshop.core.util.JacksonUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.service.DgutshopSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 14:20 2020/12/18
 */
@RestController
@RequestMapping("/admin/config")
@Validated
public class AdminConfigController {

    @Autowired
    private DgutshopSystemConfigService systemConfigService;


    /**
     *  商场配置
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mall")
    public Object listMall() {
        Map<String, String> data = systemConfigService.listMall();
        return ResponseUtil.ok(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/mall")
    public Object updateMall(@RequestBody String body) {
        Map<String, String> data = JacksonUtil.toMap(body);
        systemConfigService.updateConfig(data);
        SystemConfig.updateConfigs(data);
        return ResponseUtil.ok();
    }

    /**
     *  订单配置
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/order")
    public Object listOrder() {
        Map<String, String> data = systemConfigService.listOrder();
        return ResponseUtil.ok(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/order")
    public Object updateOrder(@RequestBody String body) {
        Map<String, String> data = JacksonUtil.toMap(body);
        systemConfigService.updateConfig(data);
        SystemConfig.updateConfigs(data);
        return ResponseUtil.ok();
    }
}
