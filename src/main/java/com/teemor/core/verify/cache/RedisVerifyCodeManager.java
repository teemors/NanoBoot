package com.teemor.core.verify.cache;


import com.teemor.core.verify.AbstractSmsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author lujing
 * @date 2021/10/13 13:41
 */
public class RedisVerifyCodeManager implements VerifyCodeManager {


    public RedisVerifyCodeManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate redisTemplate;

    /**
     * 查询缓存的验证码
     *
     * @param cacheKey 缓存键
     * @return 验证码
     */
    @Override
    public AbstractSmsService.SecurityCode getSecurityCode(String cacheKey) {
        ValueOperations<String, AbstractSmsService.SecurityCode> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(cacheKey);
    }

    /**
     * 删除存储的验证码
     *
     * @param key 缓存键
     * @return 是否删除成功
     */
    @Override
    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 保存账户验证码
     *
     * @param key          缓存键
     * @param securityCode 验证码以及过期时间
     * @return true for success
     */
    @Override
    public Boolean put(String key, AbstractSmsService.SecurityCode securityCode) {
        redisTemplate.opsForValue().set(key, securityCode, AbstractSmsService.EXPIRED_MINUTES, TimeUnit.MILLISECONDS);
        return true;
    }
}
