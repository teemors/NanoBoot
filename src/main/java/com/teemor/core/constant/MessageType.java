package com.teemor.core.constant;

import lombok.Getter;

/**
 * 消息类型
 *
 * @author lujing
 * @since 2021-10-12 16:59
 */
@Getter
public enum MessageType {
    /**
     * 邮件
     */
    EMAIL("邮件"),
    /**
     * 短信
     */
    SMS("短信"),
    /**
     * default
     */
    DEFAULT("default");

    MessageType(String display) {
        this.display = display;
    }

    /**
     * 描述
     */
    private final String display;

}
