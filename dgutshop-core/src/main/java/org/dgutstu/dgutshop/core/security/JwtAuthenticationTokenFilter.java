package org.dgutstu.dgutshop.core.security;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.security.config.JwtSecurityProperties;
import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        //  TODO
        System.out.println(tokenHeader);
        if (null != tokenHeader && tokenHeader.startsWith(JwtSecurityProperties.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JwtSecurityProperties.tokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtSecurityProperties.secret)
                        .parseClaimsJws(token)
                        .getBody();
//                System.out.println(claims.toString());
                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
//                System.out.println("filter："+username + " " + userId);
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
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
            } catch (ExpiredJwtException e) {
                log.info("Token过期");
            } catch (Exception e) {
                log.info("Token无效");
            }
        }
        filterChain.doFilter(request, response);
        return;

    }


}
