package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopRole;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.dgutstu.dgutshop.db.service.DgutshopRoleService;
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
 * @Date: Create in 9:16 2020/12/15
 */
@RestController
@RequestMapping("/admin/role")
@Validated
public class AdminRoleController {

    @Autowired
    private DgutshopRoleService roleService;
    @Autowired
    private DgutshopAdminService adminService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        List<DgutshopRole> roleList = roleService.querySelective(name, start, end, page, limit, sort, order);
        return ResponseUtil.okList(roleList);
    }

    //  校验添加角色所提交数据
    private Object validate(DgutshopRole dgutshopRole) {
        String name = dgutshopRole.getName();//角色名称
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Object create(@RequestBody DgutshopRole role){
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        if (roleService.checkExistByName(role.getName())){
            return ResponseUtil.fail(ROLE_NAME_EXIST, "角色已经存在");
        }

        roleService.add(role);

        return ResponseUtil.ok(role);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object update(@RequestBody DgutshopRole role){
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        roleService.update(role);
        return ResponseUtil.ok(role);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/delete")
    public Object delete(@RequestParam Long id){
        if (id == null){
            return ResponseUtil.badArgument();
        }

        //  如果当前角色所对应管理员仍存在，那么拒绝删除角色
        List<DgutshopAdmin> adminList = adminService.all();
        for (DgutshopAdmin admin : adminList) {
            Long roleId = admin.getRoleId();
            if (id.equals(roleId)){
                return ResponseUtil.fail(ROLE_USER_EXIST, "当前角色存在管理员，无法删除");
            }
        }

        roleService.deleteById(id);
        return ResponseUtil.ok();
    }
}
