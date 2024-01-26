package com.teemor.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * device 异常
 *
 * @author lujing
 * @date 2021/5/12 10:07
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class InvalidParameterException extends RuntimeException {
    private Integer code;
    private String msg;

    public InvalidParameterException() {
        this.code = ResultStatus.INVALID_PARAM.getResultCode();
        this.msg = ResultStatus.INVALID_PARAM.getMessage();
    }

    public InvalidParameterException(String message) {
        super(message);
        this.code = ResultCode.INVALID_PARAMETER_ERROR;
        this.msg = ResultStatus.INVALID_PARAM.getMessage() + message;
    }

    public InvalidParameterException(ResultStatus resultStatus) {
        this.code = resultStatus.getResultCode();
        this.msg = resultStatus.getMessage();
    }

    public InvalidParameterException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
