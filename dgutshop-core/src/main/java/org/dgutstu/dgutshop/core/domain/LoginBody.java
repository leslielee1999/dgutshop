package org.dgutstu.dgutshop.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: leesk
 * @Description: 封装用户登录对象，前台发后台json，直接转换
 * @Date: Create in 11:27 2020/12/21
 */
@Data
public class LoginBody implements Serializable {
    //  用户名
    private String username;
    //  用户密码
    private String password;
    //  验证码
//    private String code;
    //  唯一标识
    private String uuid = "";
}
