package com.teemor.core.model;

import com.teemor.core.constant.MessageType;
import com.teemor.core.constant.VerifyCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * message发送bean
 *
 * @author lujing
 * @date 2021/11/9 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Msg {

    /**
     * 发送验证码的账号
     */
    private String account;

    /**
     * 验证码类型
     */
    private VerifyCodeType verifyCodeType;

    /**
     * 消息发送类型
     */
    private MessageType messageType;

    /**
     * 发送需要的额外信息
     */
    private Object extra;
}
