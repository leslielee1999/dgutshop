package org.dgutstu.dgutshop.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 15:41 2020/12/21
 */
@Data
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jwttoken;
    public LoginResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
