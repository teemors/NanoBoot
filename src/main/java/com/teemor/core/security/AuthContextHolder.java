package com.teemor.core.security;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 用户认证、鉴权上下文
 *
 * @author lujing
 * @since 2024/1/16 9:47
 */
public final class AuthContextHolder {

    public static final String USER_ID_ATTR = "USER_ID";


    private AuthContextHolder() {
    }

    public static void setUserId(String userId) {
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(USER_ID_ATTR, userId, RequestAttributes.SCOPE_REQUEST);
    }


    public void setAttribute(String key, Object value) {
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(key, value, RequestAttributes.SCOPE_REQUEST);
    }


}
