package org.dgutstu.dgutshop.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.security.config.JwtSecurityProperties;
import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.core.security.util.JwtTokenUtils;
import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leesk
 * @Description: 用户登录成功处理类
 * @Date: Create in 16:12 2020/12/21
 */
@Component
@Slf4j
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登录成功返回结果
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //  组装JWT
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
//        System.out.println("loginSuccessHandler.loginUser："+loginUser);
        String token = JwtTokenUtils.createToken(loginUser);
//        System.out.println("loginSuccessHandler.token："+token);
        token = JwtSecurityProperties.tokenPrefix + token;
        //  封装返回参数
        Map<String , Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("msg", "登录成功");
        resultData.put("token", token);
        ResultUtil.responseJson(response, resultData);
    }
}
