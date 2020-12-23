package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.service.DgutshopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:无删除用户功能
 * @Date: Create in 14:58 2020/12/16
 */
@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    @Autowired
    private DgutshopUserService userService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(Integer id, String name, String phone, String wechatId, String nickname,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopUser> userList = userService.querySelective(id, name, phone, wechatId, nickname, start, end, page, limit, sort, order);
        return ResponseUtil.okList(userList);
    }

    /**
     * 根据id查询某个用户具体信息
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/detail")
    public Object userDetail(@NotNull Integer id) {
        DgutshopUser user=userService.findById(id);
        return ResponseUtil.ok(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object userUpdate(@RequestBody DgutshopUser user) {
        return ResponseUtil.ok(userService.update(user));
    }
}
