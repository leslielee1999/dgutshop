package org.dgutstu.dgutshop.core.security.handler;

import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: leesk
 * @Description: 无权限访问类
 * @Date: Create in 0:56 2020/12/21
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResultUtil.responseJson(response,ResultUtil.resultCode(403,"未授权"));
    }
}
