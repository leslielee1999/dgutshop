package org.dgutstu.dgutshop.core.security;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.dgutstu.dgutshop.core.security.config.JwtSecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: leesk
 * @Description: JWT接口请求校验拦截器, 请求接口时会进入这里验证Token是否合法和过期
 * @Date: Create in 1:19 2020/12/21
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JwtSecurityProperties.tokenHeader);
        //  获取登录ip
        String requestRri = request.getRequestURI();
//        System.out.println("filter：" + tokenHeader.startsWith(JwtSecurityProperties.tokenPrefix));
        if (null != tokenHeader && tokenHeader.startsWith(JwtSecurityProperties.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JwtSecurityProperties.tokenPrefix, "");
//                System.out.println("filter：" + token);
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtSecurityProperties.secret)
                        .parseClaimsJws(token)
                        .getBody();
                //                System.out.println(claims.toString());
                String authority = claims.get("authorities").toString();
//                System.out.println("filter authority：" + authority);
//                System.out.println("filter issuer" + claims.getIssuer());
                if (claims.getIssuer().equals("leesk_web")) {       //  后台管理系统端token验证
                    // 获取用户名
                    String username = claims.getSubject();
                    String userId = claims.getId();
//                System.out.println("filter："+username + " " + userId);
                    if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                        // 获取角色
                        List<GrantedAuthority> authorities = new ArrayList<>();

//                    System.out.println(authority);
                        if (!StringUtils.isEmpty(authority)) {
                            List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                            for (Map<String, String> role : authorityMap) {
                                if (!StringUtils.isEmpty(role)) {
                                    authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                                }
                            }
                        }
                        //组装参数
                        LoginUser loginUser = new LoginUser();
                        loginUser.setUsername(claims.getSubject());
                        loginUser.setUserId(Long.parseLong(claims.getId()));
                        loginUser.setAuthorities(authorities);
//                    System.out.println(loginUser);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, userId, authorities);
//                    System.out.println(authentication);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else if (claims.getIssuer().equals("leesk_wechat")) {     //  微信小程序端token验证
                    //  微信小程序端token验证
                    // 获取openid
                    String openid = claims.getSubject();
                    System.out.println("filter openid：" + openid);
                    Authentication authentication = null;
                    if (claims.get("authorities") != null) {
                        Collection<? extends GrantedAuthority> authorities =
                                Arrays.stream(claims.get("authorities").toString().split(","))
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList());

                        User principal = new User(claims.getSubject(), "", authorities);
                        authentication = new UsernamePasswordAuthenticationToken(principal, token, authorities);
                    }
                    System.out.println("filter authentication：" + authentication);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
                    }
                }
            } catch (ExpiredJwtException e) {
                log.info("Token过期");
            } catch (Exception e) {
                log.info("Token无效");
            }
        } else {
            System.out.println("filter header为空！");
        }
        filterChain.doFilter(request, response);
        return;
    }
}
