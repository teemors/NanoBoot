package com.teemor.core.verify.cache;


import com.teemor.core.verify.AbstractSmsService;

/**
 * 验证码管理的组件
 *
 * @author lujing
 * @date 2021/10/13 10:27
 */
public interface VerifyCodeManager {


    /**
     * 查询缓存的验证码
     *
     * @param cacheKey 缓存键
     * @return 验证码
     */
    AbstractSmsService.SecurityCode getSecurityCode(String cacheKey);

    /**
     * 删除存储的验证码
     *
     * @param key 缓存键
     * @return 是否删除成功
     */
    Boolean remove(String key);

    /**
     * 保存账户验证码
     *
     * @param key          缓存键
     * @param securityCode 验证码以及过期时间
     * @return
     */
    Boolean put(String key, AbstractSmsService.SecurityCode securityCode);
}
