package com.teemor.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * errorResult
 *
 * @author lujing
 * @date 2021/5/18 14:00
 */
@AllArgsConstructor
@Getter
public enum ResultStatus {


    /**
     * 成功
     */

    SUCCESS(ResultCode.SUCCESS, ResultCode.SUCCESS_MSG),


    /**
     * 参数错误
     */
    INVALID_PARAM(ResultCode.INVALID_PARAMETER_ERROR, ResultCode.INVALID_ERROR_MSG),

    /**
     * 资源找不到
     */
    RESOURCE_NOT_FOUND(ResultCode.NOT_FOUND_ERROR, ResultCode.NOT_FOUND_ERROR_MSG),

    /**
     * body参数错误
     */
    BAD_REQUEST(ResultCode.BAD_REQUEST, ResultCode.BODY_ERROR_MSG),

    /**
     * body参数错误
     */
    ERROR_URI(ResultCode.ERROR_URI, ResultCode.PATH_ERROR_MSG),

    /**
     * 未授权
     */
    UNAUTHORIZED(ResultCode.UNAUTHORIZED_ERROR, ResultCode.UNAUTHORIZED_ERROR_MSG);


    private final Integer resultCode;

    private final String message;

}
