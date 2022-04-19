package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.dgutstu.dgutshop.db.service.DgutshopSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 19:26 2020/12/19
 */
@RestController
@RequestMapping("/wechat/config")
@Validated
public class WechatConfigController {

    @Autowired
    private DgutshopSystemConfigService systemConfigService;

    @Autowired
    private DgutshopAdminService adminService;

    /**
     *  商场配置
     * @return
     */
    @GetMapping("/mall")
    public Object listMall() {
        Map<String, String> data = systemConfigService.listMall();
        return ResponseUtil.ok(data);
    }

    /**
     *  订单配置
     * @return
     */
    @GetMapping("/order")
    public Object listOrder() {
        Map<String, String> data = systemConfigService.listOrder();
        return ResponseUtil.ok(data);
    }

    /**
     * 获取店铺员工体温信息
     * @return
     */
    @GetMapping("/getAllAdmin")
    public Object getAllAdmin() {
        List<DgutshopAdmin> adminList = adminService.list();
        return adminList;
    }
}
