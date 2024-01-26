package com.teemor.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * iot 设备异常
 *
 * @author lujing
 * @date 2021/5/12 10:07
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class ApiServiceException extends RuntimeException {
    private Integer code;
    private String msg;

    public ApiServiceException() {
    }

    public ApiServiceException(String message) {
        super(message);
        this.msg = message;
    }

    public ApiServiceException(ResultStatus resultStatus) {
        this.code = resultStatus.getResultCode();
        this.msg = resultStatus.getMessage();
    }

    public ApiServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
