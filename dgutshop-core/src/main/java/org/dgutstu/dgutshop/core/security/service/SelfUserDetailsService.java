package org.dgutstu.dgutshop.core.security.service;

import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SpringSecurity用户的业务实现
 * @Author Sans
 * @CreateTime 2019/10/1 17:21
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private DgutshopAdminService adminService;

    /**
     * 查询用户信息
     * @Author Sans
     * @CreateTime 2019/9/13 17:23
     * @Param  username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        DgutshopAdmin admin = adminService.findAdminByNickName(username);
        if (admin != null){
            // 组装参数
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(admin.getId());
            loginUser.setUsername(admin.getNickname());
            loginUser.setPassword(admin.getPassword());
            loginUser.setStatus(admin.getStatus());
//            BeanUtils.copyProperties(admin,loginUser);
            return loginUser;
        }
        return null;
    }
}