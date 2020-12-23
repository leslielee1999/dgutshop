package org.dgutstu.dgutshop.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: leesk
 * @Description: 用户登录失败处理类
 * @Date: Create in 16:02 2020/12/21
 */
@Component
@Slf4j
public class JwtLoginFailureHandler implements AuthenticationFailureHandler {
    /**
     * 用户登录失败，根据不同异常返回不同处理
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException){
            log.info("【登录失败】" + e.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名不存在"));
        }
        if (e instanceof LockedException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户被冻结"));
        }
        if (e instanceof BadCredentialsException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户名密码不正确"));
        }
        ResultUtil.responseJson(response,ResultUtil.resultCode(500,"登录失败"));
    }
}
