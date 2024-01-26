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
public class ResourceNotFoundException extends RuntimeException {
    private Integer code;
    private String msg;

    public ResourceNotFoundException() {
        this.code = ResultStatus.RESOURCE_NOT_FOUND.getResultCode();
        this.msg = ResultStatus.RESOURCE_NOT_FOUND.getMessage();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.code = ResultCode.NOT_FOUND_ERROR;
        this.msg = ResultStatus.RESOURCE_NOT_FOUND.getMessage() + message;
    }

    public ResourceNotFoundException(ResultStatus resultStatus) {
        this.code = resultStatus.getResultCode();
        this.msg = resultStatus.getMessage();
    }

    public ResourceNotFoundException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
