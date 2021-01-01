package org.dgutstu.dgutshop.admin;

import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopRole;
import org.dgutstu.dgutshop.db.domain.DgutshopUserRole;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.dgutstu.dgutshop.db.service.DgutshopRoleService;
import org.dgutstu.dgutshop.db.service.DgutshopUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 0:50 2020/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DgutshopAdminApplicationTest {

    @Autowired
    private DgutshopAdminService adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DgutshopUserRoleService userRoleService;


    /**
     * 注册用户
     */
    @Test
    public void contextLoads() {
        // 注册用户
        DgutshopAdmin admin = new DgutshopAdmin();
        admin.setNickname("leslie");
        admin.setPassword(bCryptPasswordEncoder.encode("123456"));
        admin.setStatus("NORMAL");
        admin.setRoleId(1);
        adminService.add(admin);
        // 分配角色 1:ADMIN 2:USER
        DgutshopUserRole userRole = new DgutshopUserRole();
        userRole.setRoleId(1);
        userRole.setUserId(admin.getId());
        userRoleService.add(userRole);
    }
}
