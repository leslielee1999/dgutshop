package org.dgutstu.dgutshop.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.admin.util.LogHelper;
import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.util.bcrypt.BCryptPasswordEncoder;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.dgutstu.dgutshop.admin.util.AdminResponseCode.*;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:40 2020/12/14
 */
@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {
    private final Log logger = LogFactory.getLog(AdminAdminController.class);

    @Autowired
    private DgutshopAdminService adminService;
    @Autowired
    private LogHelper logHelper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public Object list(String name, String nickname,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopAdmin> adminList = adminService.querySelective(name, nickname, start, end, page, limit, sort, order);
        return ResponseUtil.okList(adminList);
    }

    //  校验添加管理员所提交数据
    private Object validate(DgutshopAdmin dgutshopAdmin) {
        String name = dgutshopAdmin.getNickname();//管理员昵称
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员昵称不符合规定");
        }
        String code = dgutshopAdmin.getCode();//管理员编号
        if (StringUtils.isEmpty(code)) {
            return ResponseUtil.badArgument();
        }


//        String password = dgutshopAdmin.getPassword();
//        if (StringUtils.isEmpty(password) || password.length() < 6) {
//            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
//        }

        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Object create(@RequestBody DgutshopAdmin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        String nickname = admin.getNickname();
        List<DgutshopAdmin> adminList = adminService.findAdmin(nickname);
        if (adminList.size() > 0) {
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "管理员已经存在");
        }


        String rawPassword = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(rawPassword);
        System.out.println(encoderPassword);
        admin.setPassword(encoderPassword);
        adminService.add(admin);
        //  记录日志
        //  改用spring security实现
//        logHelper.logAuthSucceed("添加管理员", username);
        return ResponseUtil.ok(admin);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public Object delete(@RequestParam Long id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        //  管理员不能删除自身账号
        //  用spring security 实现
//        Subject currentUser = SecurityUtils.getSubject();
//        LitemallAdmin currentAdmin = (LitemallAdmin) currentUser.getPrincipal();
//        if (currentAdmin.getId().equals(anotherAdminId)) {
//            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "管理员不能删除自己账号");
//        }

        adminService.deleteById(id);

        //  记录日志
        //  用spring security 实现
//        DgutshopAdmin admin = adminService.findById(id);
//        logHelper.logAuthSucceed("删除管理员", admin.getUsername());

        return ResponseUtil.ok();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public Object update(@RequestBody DgutshopAdmin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        Long adminId = admin.getId();
        if (adminId == null) {
            return ResponseUtil.badArgument();
        }

        //  不允许管理员通过编辑接口修改密码
        //  返回前台数据也需不可见
        admin.setPassword(null);

        if (adminService.update(admin) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        //  记录日志
        //  用spring security 实现
//        logHelper.logAuthSucceed("编辑管理员", admin.getNickname());
        return ResponseUtil.ok(admin);
    }
}
