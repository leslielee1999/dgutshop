package org.dgutstu.dgutshop.core.domain;

import lombok.Data;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 22:31 2021/1/1
 */
@Data
public class WechatLoginUser {
    /**
     * 传入参数：临时登录凭证
     */
    private String code;

    /**
     * 传入参数: 解密算法的向量
     */
    private String iv;

    /**
     * 传入参数: 用户非敏感信息
     */
//    private String rawData;

    /**
     * 传入参数: 用户敏感信息
     */
    private String encryptedData;
}
