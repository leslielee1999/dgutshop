package org.dgutstu.dgutshop.core.security.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 0:45 2020/12/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtSecurityProperties {
    /** Request Headers ： Authorization */
    public static String tokenHeader;

    /**
     * Token前缀字符
     */
    public static String tokenPrefix;

    /**
     * 密钥KEY
     */
    public static String secret;

    /** 令牌过期时间 此处单位/毫秒 */
    public static Integer expiration;

    /**
     * 不需要认证的接口
     */
    public static String antMatchers;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration * 1000;
    }

    public void setAntMatchers(String antMatchers) {
        this.antMatchers = antMatchers;
    }
}
