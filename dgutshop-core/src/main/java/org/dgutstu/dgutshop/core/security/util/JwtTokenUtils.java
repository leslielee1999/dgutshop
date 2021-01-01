package org.dgutstu.dgutshop.core.security.util;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.domain.AuthUserDto;
import org.dgutstu.dgutshop.core.security.config.JwtSecurityProperties;
import org.dgutstu.dgutshop.core.domain.LoginUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: leesk
 * @Description: JWT工具类：实现创建token功能
 * @Date: Create in 1:02 2020/12/21
 */
@Slf4j
@Component
public class JwtTokenUtils{
    /**
     * 生成管理后台访问所需token
     * @param loginUser
     * @return
     */
    //  TODO：HS512
    public  static String createToken (LoginUser loginUser) {
        return Jwts.builder()
                //  放入用户名和id
                .setId(loginUser.getUserId() + "")
                .setSubject(loginUser.getUsername())
                //  发布时间
                .setIssuedAt(new Date())
                //  签发者
                .setIssuer("leesk_web")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(loginUser.getAuthorities()))
                //  失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtSecurityProperties.expiration))
                //  （签名算法与）密钥
                .signWith(SignatureAlgorithm.HS512, JwtSecurityProperties.secret)
                .compact();
    }

    /**
     * 生成微信小程序端访问所需token
     * @return
     */
    //  TODO: 1. 改了这个createToken参数，原来是String subject, Map<String, Object> claims
    //
    public static String createToken(AuthUserDto authUserDto) {
        return Jwts.builder()
                .setSubject(authUserDto.getOpenId())
                .claim("authorities", "ROLE_CUSTOMER")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                //  签发者
                .setIssuer("leesk_wechat")
                .setExpiration(new Date(System.currentTimeMillis() + JwtSecurityProperties.expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, JwtSecurityProperties.secret)
                .compact();
    }

}
