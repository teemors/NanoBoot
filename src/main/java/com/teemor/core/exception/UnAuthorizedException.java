package com.teemor.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * User 未授权异常
 *
 * @author lujing
 * @date 2021/07/09 17:57
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class UnAuthorizedException extends RuntimeException {
    private Integer code;
    private String msg;

    public UnAuthorizedException() {
        this.code = ResultStatus.UNAUTHORIZED.getResultCode();
        this.msg = ResultStatus.UNAUTHORIZED.getMessage();
    }

    public UnAuthorizedException(String message) {
        super(message);
        this.code = ResultCode.UNAUTHORIZED_ERROR;
        this.msg = ResultStatus.UNAUTHORIZED.getMessage() + message;
    }

    public UnAuthorizedException(ResultStatus resultStatus) {
        this.code = resultStatus.getResultCode();
        this.msg = resultStatus.getMessage();
    }

    public UnAuthorizedException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
