package com.teemor.service;

import com.teemor.admin.resp.AdminLoginResp;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * 缓存服务
 *
 * @author lujing
 * @since 2024/1/15 17:01
 */
@Service
public class CacheService {

    private static final String CAPTCHA_KEY = "com:teemor:captcha:";

    private static final String USER_ROLES_KEY = "com:teemor:user:roles:";
    private static final String USER_PERMS_KEY = "com:teemor:user:permission:";

    private static final String USER_TOKEN_KEY = "com:teemor:user:token:";

    @Resource
    private Redisson redisson;


    public void cacheCaptcha(String uuid, String code) {
        redisson.getBucket(CAPTCHA_KEY + uuid).set(code, Duration.of(2, ChronoUnit.MINUTES));
    }

    public String getCaptcha(String uuid) {
        RBucket<String> redissonBucket = redisson.getBucket(CAPTCHA_KEY + uuid);
        return redissonBucket.get();
    }

    public void removeCaptcha(String uuid) {
        RBucket<String> redissonBucket = redisson.getBucket(CAPTCHA_KEY + uuid);
        redissonBucket.delete();
    }

    public void cacheUserAuthInfo(AdminLoginResp resp) {
        redisson.getSetCache(USER_TOKEN_KEY + resp.getId()).add(resp.getToken());
        redisson.getSetCache(USER_ROLES_KEY + resp.getId()).addAll(resp.getRoles());
        redisson.getSetCache(USER_PERMS_KEY + resp.getId()).addAll(resp.getPermissions());
    }


    public Set<String> getUserTokenCache(String userId) {
        return redisson.getSetCache(USER_TOKEN_KEY + userId);
    }

    public Set<String> getUserPermission(String userId) {
        return redisson.getSetCache(USER_PERMS_KEY + userId);
    }
}
