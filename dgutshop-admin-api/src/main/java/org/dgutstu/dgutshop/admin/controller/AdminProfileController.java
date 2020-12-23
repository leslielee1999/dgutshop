package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.core.security.service.SelfUserDetailsService;
import org.dgutstu.dgutshop.core.security.util.SecurityUtil;
import org.dgutstu.dgutshop.core.util.JacksonUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.util.bcrypt.BCryptPasswordEncoder;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:05 2020/12/23
 */
@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
    @Autowired
    private DgutshopAdminService adminService;
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/password")
    public Object create(@RequestBody String body) {
        String oldPassword = JacksonUtil.parseString(body, "oldPassword");
        String newPassword = JacksonUtil.parseString(body, "newPassword");
        if (StringUtils.isEmpty(oldPassword)) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(newPassword)) {
            return ResponseUtil.badArgument();
        }
        //  TODO：哪种比较好？
        //  另一种方法：解析token；
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        /**
         * @Note UserDetails里没有密码，只有名字；
         *          需要根据这个名字去查询数据库的加密密码，然后拿出来解析并进行比较
         */
        LoginUser userDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        LoginUser userInfo = selfUserDetailsService.loadUserByUsername(userDetails.getUsername());
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!encoder.matches(oldPassword, userInfo.getPassword())) {
            return ResponseUtil.fail(605, "账号密码不对");
        }
        String encodedNewPassword = encoder.encode(newPassword);
        DgutshopAdmin admin = adminService.findById(userDetails.getUserId());
        admin.setPassword(encodedNewPassword);
        adminService.update(admin);
        return ResponseUtil.ok();
    }

    /**
     * 获取个人信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/getinfo")
    public Object getInfo(){
        LoginUser userDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        LoginUser userInfo = selfUserDetailsService.loadUserByUsername(userDetails.getUsername());
        System.out.println("userDetails："+userDetails.getPassword());
        System.out.println("userInfo："+userInfo.getPassword());
        Long id = userDetails.getUserId();
        DgutshopAdmin admin = adminService.findById(id);
        return ResponseUtil.ok(admin);
    }

}
