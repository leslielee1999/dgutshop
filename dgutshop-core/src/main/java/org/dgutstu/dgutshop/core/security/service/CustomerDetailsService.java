package org.dgutstu.dgutshop.core.security.service;

import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.service.DgutshopAdminService;
import org.dgutstu.dgutshop.db.service.DgutshopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 23:41 2020/12/25
 */
@Component
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private DgutshopUserService userService;

    /**
     * 查询用户信息
     * @Author Sans
     * @CreateTime 2019/9/13 17:23
     * @Param  username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public LoginUser loadUserByUsername(String openid) throws UsernameNotFoundException {
        // 查询用户信息
//        System.out.println("SelfUserDetailsService："+username);
        DgutshopUser user = userService.findByOpenId(openid);
//        System.out.println("SelfUserDetailsService："+admin);
        if (user != null){
//            System.out.println(admin.getId());
            // 组装参数
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(user.getId());
            loginUser.setUsername(user.getNickname());
            loginUser.setPassword(user.getPassword());
            loginUser.setStatus(user.getStatus());
//            BeanUtils.copyProperties(admin,loginUser);
//            System.out.println("SelfUserDetailsService的loginUser："+loginUser);
            return loginUser;
        }
        return null;
    }

}
