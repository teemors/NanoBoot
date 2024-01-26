package com.teemor.core.constant;

import lombok.Getter;

/**
 * 验证码类型
 *
 * @author lujing
 * @since 2021-10-12 16:59
 */
@Getter
public enum VerifyCodeType {
    /**
     * 注册
     */
    REGISTER("注册"),
    /**
     * 重置密码
     */
    REST_PASSWORD("重置密码"),
    /**
     * 更换手机号
     */
    CHANGE_MOBILE("更换手机号"),
    /**
     * 绑定手机号
     */
    BIND_MOBILE("绑定手机号"),
    /**
     * 设置密码
     */
    SET_PASSWORD("设置密码"),
    /**
     * 校验身份
     */
    CHECK_AUTH("校验身份"),

    /**
     * 其他
     */
    OTHER("其他");

    VerifyCodeType(String display) {
        this.display = display;
    }

    /**
     * 描述
     */
    private final String display;

}
