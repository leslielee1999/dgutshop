package org.dgutstu.dgutshop.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.domain.AuthUserDto;
import org.dgutstu.dgutshop.wechat.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 10:17 2020/12/25
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(tags = "安全接口")
public class WechatAuthController {
    @Autowired
    private WechatAuthService authService;

    @ApiOperation("登录授权")
    @PostMapping(value = "/wechat")
    public ResponseEntity login(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(authService.login(authUserDto));
    }

}
