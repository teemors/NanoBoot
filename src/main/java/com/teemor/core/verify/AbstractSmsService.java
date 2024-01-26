package com.teemor.core.verify;

import cn.hutool.core.util.StrUtil;
import com.teemor.core.constant.VerifyCodeType;
import com.teemor.core.model.Msg;
import com.teemor.core.verify.cache.RedisVerifyCodeManager;
import com.teemor.core.verify.cache.VerifyCodeManager;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * 发送、校验短信验证码
 *
 * @author lujing
 * @date 2021-10-12 17:38:58
 */
@Slf4j
public abstract class AbstractSmsService {


    public static final Long EXPIRED_MINUTES = 5 * 60 * 1000L;
    private static final Long SEND_INTERVAL_TIME = 60 * 1000L;
    private static final String CACHE_KEY_PREFIX = "security:verifycode:";

    private static final String DEFAULT_TENANT_ID = "default:tenant";
    /**
     * 验证码失效的时间
     */
    private long expiredTime = EXPIRED_MINUTES;

    /**
     * 重复发送间隔时间
     */
    private long intervalTime = SEND_INTERVAL_TIME;


    private final VerifyCodeManager verifyCodeManager;

    /**
     * 可以自定义验证码管理器 进行注入
     *
     * @param verifyCodeManager
     */
    public AbstractSmsService(VerifyCodeManager verifyCodeManager) {
        this.verifyCodeManager = verifyCodeManager;
    }

    /**
     * default inject redis cache
     *
     * @param redisTemplate
     */
    public AbstractSmsService(RedisTemplate redisTemplate) {
        this.verifyCodeManager = new RedisVerifyCodeManager(redisTemplate);
    }


    /**
     * 发送验证码
     *
     * @param msg 消息内容
     */
    public void commonSendSecurityCode(Msg msg) {
        this.sendSecurityCode(msg);
    }


    /**
     * 判断是否发送过
     *
     * @param account 手机号
     * @param type    验证码类型
     * @return
     */
    public Boolean hasSendVerifyCode(String account, String tenantId, VerifyCodeType type) {
        String key = this.getCacheKey(account, tenantId, type);

        SecurityCode securityCode = verifyCodeManager.getSecurityCode(key);
        if (securityCode == null || securityCode.getIntervalTime() == null) {
            return false;
        }
        return securityCode.getIntervalTime() > System.currentTimeMillis();
    }


    /**
     * 校验验证码
     *
     * @param account 发送验证码的账号
     * @param type    验证码类型
     * @param code    待校验的验证码
     */

    public void validSecurityCode(String account, String tenantId, VerifyCodeType type, String code) {
        this.validAccountAndCode(account, tenantId, type, code);
        this.deleteCode(account, tenantId, type);
    }


    /**
     * 验证码检验
     *
     * @param account 发送验证码的账号
     * @param type    验证类型
     * @param code    待校验的验证码
     * @param removed 是否让验证码失效
     */
    public void validSecurityCode(String account, String tenantId, VerifyCodeType type, String code, boolean removed) {
        this.validAccountAndCode(account, tenantId, type, code);
        if (removed) {
            this.deleteCode(account, tenantId, type);
        }
    }


    /**
     * 校验验证码
     *
     * @param account  发送验证码的账号
     * @param tenantId 租户Id
     * @param type     验证码类型
     * @param code     待校验的验证码
     */
    private void validAccountAndCode(String account, String tenantId, VerifyCodeType type, String code) {
        if (StrUtil.isBlank(code)) {
            throw new IllegalArgumentException("verify code must be not null");
        }
        String key = this.getCacheKey(account, tenantId, type);
        SecurityCode securityCode = verifyCodeManager.getSecurityCode(key);
        if (securityCode == null) {
            throw this.throwVerifyCodeExpiredException();
        }
        if (!securityCode.getCode().equals(code)) {
            log.error("验证码错误：{} ， 内容：{}", account, securityCode);
            throw this.throwInValidVerifyCodeExpiredException();
        }
    }

    private String getCacheKey(String account, String tenantId, VerifyCodeType type) {
        if (null == tenantId) {
            return CACHE_KEY_PREFIX + type.name() + StrUtil.UNDERLINE + DEFAULT_TENANT_ID + StrUtil.UNDERLINE + account;
        } else {
            return CACHE_KEY_PREFIX + type.name() + StrUtil.UNDERLINE + tenantId + StrUtil.UNDERLINE + account;
        }

    }


    /**
     * 删除验证码
     *
     * @param account  发送验证码的账号
     * @param tenantId 租户Id
     * @param type     验证类型
     */
    public void deleteCode(String account, String tenantId, VerifyCodeType type) {
        String key = this.getCacheKey(account, tenantId, type);
        SecurityCode securityCode = verifyCodeManager.getSecurityCode(key);
        if (securityCode != null) {
            verifyCodeManager.remove(key);
        }
    }

    /**
     * 发送验证码
     *
     * @param msg 消息内容
     */
    private void sendSecurityCode(Msg msg) {
        String tenantId = this.getTenantId(msg);
        Boolean hasSend = this.hasSendVerifyCode(msg.getAccount(), tenantId, msg.getVerifyCodeType());
        if (hasSend) {
            throw this.throwHasSentException();
        }
        String code = this.sendMessages(msg);

        String key = this.getCacheKey(msg.getAccount(), tenantId, msg.getVerifyCodeType());
        long currentTimeMillis = System.currentTimeMillis();
        SecurityCode securityCode = SecurityCode.builder()
                .code(code)
                .account(msg.getAccount())
                .expiredTime(currentTimeMillis + expiredTime)
                .intervalTime(currentTimeMillis + intervalTime)
                .build();

        if (!verifyCodeManager.put(key, securityCode)) {
            throw new IllegalArgumentException("call put verifyCodeCacheManager into cache error");
        }

    }

    /**
     * 验证码错误的异常
     *
     * @return RuntimeException
     */
    protected abstract RuntimeException throwInValidVerifyCodeExpiredException();

    /**
     * 验证码失效的异常
     *
     * @return RuntimeException
     */
    protected abstract RuntimeException throwVerifyCodeExpiredException();

    /**
     * 已经发送过验证码的异常
     *
     * @return RuntimeException
     */
    protected abstract RuntimeException throwHasSentException();

    /**
     * 发送验证码
     *
     * @param msg 消息内容
     * @return 实际发送的验证码
     */
    public abstract String sendMessages(Msg msg);


    /**
     * 获取租户id
     *
     * @param msg 消息内容
     * @return 租户Id
     */
    public String getTenantId(Msg msg) {
        return DEFAULT_TENANT_ID;
    }

    /**
     * 缓存验证码和过期时间的对象
     */
    @Data
    @Builder
    @ToString
    public static class SecurityCode implements Serializable {


        /**
         * 手机号
         */
        private String account;


        /**
         * 验证码
         */
        private String code;


        /**
         * 验证码过期
         */
        private Long expiredTime;


        /**
         * 重发间隔时间
         */
        private Long intervalTime;

    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
