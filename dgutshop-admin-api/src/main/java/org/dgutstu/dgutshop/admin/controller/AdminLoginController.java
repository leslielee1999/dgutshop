package org.dgutstu.dgutshop.admin.controller;


import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.dgutstu.dgutshop.core.security.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * 用户认证：
 *      用户通过localhost:8083/login/userLogin（【核心配置类WebSecurityConfig】放行登录地址）地址登录账号，传递账号与密码给到服务器；
 *      -->进入自定义登录验证UserAuthenticationProvider（【核心配置类WebSecurityConfig】配置登录校验逻辑），校验账号密码是否正确
 *          若正确-->进入自定义用户登录成功处理类JwtLoginSuccessHandler（【核心配置类WebSecurityConfig】配置登录成功自定义处理类），使用JwtTokenUtils工具生成并组装token，返回成功信息给前台
 *          若失败-->进入自定义用户登录失败处理类JwtLoginFailureHandler（【核心配置类WebSecurityConfig】配置登录失败自定义处理类），生成对应的失败信息（UserAuthenticationProvider中会有相应的逻辑判断进行不同异常的抛出），返回失败信息给前台
 * 权限验证：
 *      前台访问服务器的api接口
 *      -->进入JWT接口请求校验拦截器JwtAuthenticationTokenFilter，进行JWT的token解析，获得登录的用户所具有的角色的权限（可判断Token是否过期或无效）
 *      -->判断PreAuthorize注解中的权限表达式，返回true或false（【核心配置类WebSecurityConfig】配置权限校验逻辑），校验当前登录用户是否具有访问该api的权限
 *          若具有权限true-->服务器返回前台所需要的信息
 *          若不具有权限false-->进入自定义无权限访问类JwtAccessDeniedHandler，返回无权限信息，不允许访问该api
 *
 * @Date: Create in 11:29 2020/12/21
 */
@RestController
@RequestMapping("/")
public class AdminLoginController {

    /**
     * 管理端信息
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "admin/info",method = RequestMethod.GET)
    public Map<String,Object> adminLogin(){
        Map<String,Object> result = new HashMap<>();
        LoginUser userDetails = SecurityUtil.getUserInfo();
        System.out.println("controller：" + userDetails);
        result.put("title","管理端信息");
        result.put("data",userDetails);
        return ResultUtil.resultSuccess(result);
    }

    /**
     * 用户端信息
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "user/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        Map<String,Object> result = new HashMap<>();
        LoginUser userDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        result.put("title","用户端信息");
        result.put("data",userDetails);
        return ResultUtil.resultSuccess(result);
    }
}
