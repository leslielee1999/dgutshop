package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.db.service.DgutshopSlideShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 13:04 2020/12/19
 */
@RestController
@RequestMapping("/wechat/slideshow")
@Validated
public class WechatSlideShowController {
    @Autowired
    private DgutshopSlideShowService slideShowService;

    @GetMapping("list")
    public Object list(){
        return slideShowService.list();
    }
}
