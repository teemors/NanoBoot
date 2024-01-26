package com.teemor.core.constant;

/**
 * 公共使用的常量
 *
 * @author lujing
 * @date 2017/10/29
 */
public final class CommonConstant {
    private CommonConstant() {
    }


    public static final String TOKEN_PERFIX = "Bearer ";

    /**
     * 默认拦截器中用户token缓存的key
     */
    public static final String CHACHE_TOEKN_KEY = "app:userTokenCache:";


    /**
     * 默认拦截器中用户权限缓存的key
     */
    public static final String CHACHE_PERMISSION_KEY = "app:userPermissionCache:";


    /**
     * 校验短信验证码的缓存key
     */
    public static final String CHACHE_VERIFYCODE_KEY = "security:verifycode:";


    /**
     * 内容支付成功
     */
    public static final String PAY_SUCCESS = "SUCCESS";


}
