package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.service.DgutshopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @GetMapping("/list")
    public Object list(String username, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);
        return ResponseUtil.okList(userList);
    }

    /**
     * 根据id查询某个用户具体信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object userDetail(@NotNull Integer id) {
        DgutshopUser user=userService.findById(id);
        return ResponseUtil.ok(user);
    }

    @PostMapping("/update")
    public Object userUpdate(@RequestBody DgutshopUser user) {
        return ResponseUtil.ok(userService.update(user));
    }
}
