package org.dgutstu.dgutshop.wechat.service;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.domain.AuthUserDto;
import org.dgutstu.dgutshop.core.domain.Constant;
import org.dgutstu.dgutshop.core.domain.Result;
import org.dgutstu.dgutshop.core.domain.WechatLoginUser;
import org.dgutstu.dgutshop.core.security.config.JwtSecurityProperties;
import org.dgutstu.dgutshop.core.security.util.JwtTokenUtils;
import org.dgutstu.dgutshop.core.util.IpUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.domain.DgutshopUserExample;
import org.dgutstu.dgutshop.db.service.DgutshopUserService;
import org.dgutstu.dgutshop.wechat.util.WechatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 10:21 2020/12/25
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WechatAuthService {
    @Value("${wx.appid}")
    private String appId;
    @Value("${wx.secret}")
    private String secret;

    private final WechatApiService wechatApiService;
    private final DgutshopUserService userService;

    public WechatAuthService(WechatApiService wechatApiService, DgutshopUserService userService) {
        this.wechatApiService = wechatApiService;
        this.userService = userService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<AuthUserDto> login(WechatLoginUser wechatLoginUser, HttpServletRequest request) {
        AuthUserDto authUserDto = new AuthUserDto();
        Result<AuthUserDto> result = new Result<>();
        System.out.println("code："+wechatLoginUser.getCode());
        //authType=1代表是微信登录
//        if (!StringUtils.isEmpty(authUserDto.getAuthType()) && authUserDto.getAuthType() == 1) {
        JSONObject jsonObject = wechatApiService.authCode2Session(appId, secret, wechatLoginUser.getCode());
        if (jsonObject == null) {
            throw new RuntimeException("调用微信端授权认证接口错误");
        }
        String openId = jsonObject.getString(Constant.OPEN_ID);
        System.out.println("openid："+openId);
        String sessionKey = jsonObject.getString(Constant.SESSION_KEY);
        System.out.println("sessionKey："+sessionKey);
//        String unionId = jsonObject.getString(Constant.UNION_ID);
        if (StringUtils.isEmpty(openId)) {
            return result.error(jsonObject.getString(Constant.ERR_CODE), jsonObject.getString(Constant.ERR_MSG));
        }
        authUserDto.setOpenId(openId);

        //判断用户表中是否存在该用户，不存在则进行解密得到用户信息，并进行新增用户
        Result<DgutshopUser> resultUser = findByOpenId(openId);
        if (resultUser.getModule() == null) {
            String userInfo = WechatUtil.decryptData(wechatLoginUser.getEncryptedData(), sessionKey, wechatLoginUser.getIv());
            System.out.println(userInfo);
            if (StringUtils.isEmpty(userInfo)) {
                throw new RuntimeException("解密用户信息错误");
            }
            DgutshopUser user = JSONObject.parseObject(userInfo, DgutshopUser.class);
            if (user == null) {
                throw new RuntimeException("填充用户对象错误");
            }
//                user.setUnionId(unionId);
            user.setName(openId);
            user.setPassword(openId);
            user.setStatus("0");
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            create(user);
//            authUserDto.setUserInfo(user);

        } else {
//            authUserDto.setUserInfo(resultUser.getModule());
        }

        //创建token
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("roles", "user");
        String token = JwtTokenUtils.createToken(authUserDto);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("生成token错误");
        }
        authUserDto.setToken(JwtSecurityProperties.tokenPrefix + token);

//        }
        return result.ok(authUserDto);
    }

    public Result<DgutshopUser> findByOpenId(String openid) {
        return new Result<DgutshopUser>().ok(userService.findByOpenId(openid));
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(DgutshopUser user) {
        userService.save(user);
    }

    public String checkLogin(HttpServletRequest request, HttpServletResponse response){
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JwtSecurityProperties.tokenHeader);
        //  获取登录ip
        String requestRri = request.getRequestURI();

        if (null != tokenHeader && tokenHeader.startsWith(JwtSecurityProperties.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JwtSecurityProperties.tokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtSecurityProperties.secret)
                        .parseClaimsJws(token)
                        .getBody();
                String openid = claims.getSubject();
                if (openid.equals("") || openid == null || !claims.getIssuer().equals("leesk_wechat")){
                    return "unlogin";
                }else {
                    return openid;
                }
            }catch (ExpiredJwtException e) {
                log.info("Token过期");
            } catch (Exception e) {
                log.info("Token无效");
            }
        }
        return null;
    }

    public Object validate(HttpServletRequest request, HttpServletResponse response){
        DgutshopUser user = null;
        String openid = checkLogin(request, response);
        if (openid == null || openid.equals("unlogin")){
            return ResponseUtil.unlogin();
        }
        user = userService.findByOpenId(openid);
        if (user == null){
            return ResponseUtil.fail(901, "系统未记录此微信用户，请先退出并重新登录");
        }else
            return user;
    }

    public Integer getUserId(Object obj){
        DgutshopUser user = null;
        if (obj instanceof DgutshopUser){
            user = (DgutshopUser)obj;
        }
        Integer userId = user.getId();
        return userId;
    }

}
