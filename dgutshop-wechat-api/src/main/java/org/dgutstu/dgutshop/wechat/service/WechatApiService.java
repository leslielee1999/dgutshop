package org.dgutstu.dgutshop.wechat.service;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.wechat.util.WechatUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 10:25 2020/12/25
 */
@Slf4j
@Service
public class WechatApiService {

    public JSONObject authCode2Session(String appId, String secret, String jsCode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String str = WechatUtil.httpRequest(url, "GET", null);
        log.info("api/wx-mini/getSessionKey:" + str);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            System.out.println("authStr："+str);
            return JSONObject.parseObject(str);
        }

    }
}
