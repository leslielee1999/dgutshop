package org.dgutstu.dgutshop.core.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: leesk
 * @Description:JWT用户相关封装
 * @Date: Create in 11:46 2020/12/21
 */
@Data
public class LoginUser implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    //  用户唯一标识
    private String token;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;
    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 账户是否过期
     */
    private boolean isAccountNonExpired = false;
    /**
     * 账户是否被锁定
     */
    private boolean isAccountNonLocked = false;
    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;
    /**
     * 账户是否有效
     */
    private boolean isEnabled = true;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
