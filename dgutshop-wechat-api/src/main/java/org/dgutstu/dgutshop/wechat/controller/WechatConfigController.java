package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.service.DgutshopSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     *  商场配置
     * @return
     */
    @GetMapping("/mall")
    public Object listMall() {
        Map<String, String> data = systemConfigService.listMall();
        return ResponseUtil.ok(data);
    }
}
