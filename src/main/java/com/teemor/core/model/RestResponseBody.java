package com.teemor.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teemor.core.exception.ResultCode;
import com.teemor.core.exception.ResultStatus;
import lombok.Data;
import lombok.ToString;

/**
 * Base wrapper for RESTFul response.
 *
 * @param <T> Real data for response.
 * @author lujing
 * @date 2021/5/21 15:40
 */
@ToString
@Data
public final class RestResponseBody<T> {

    private Integer code;
    private String msg;
    private T data;


    private RestResponseBody() {
    }

    private RestResponseBody(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private RestResponseBody(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResponseBody(ResultStatus status) {
        this.msg = status.getMessage();
        this.code = status.getResultCode();
    }

    public RestResponseBody(ResultStatus status, T data) {
        this.msg = status.getMessage();
        this.code = status.getResultCode();
        this.data = data;
    }

    public static <T> RestResponseBody<T> success(T data) {
        return new RestResponseBody<>(ResultStatus.SUCCESS, data);
    }

    public static RestResponseBody<String> success() {
        return new RestResponseBody<>(ResultStatus.SUCCESS);
    }

    public static <T> RestResponseBody<T> failure(Integer code, String msg) {
        return new RestResponseBody<>(code, msg);
    }

    public static <T> RestResponseBody<T> failure(ResultStatus status) {

        return new RestResponseBody<>(status);
    }

    public static <T> RestResponseBody<T> failure(ResultStatus status, String msg) {
        return new RestResponseBody<>(status.getResultCode(), status.getMessage() + msg);
    }


    public RestResponseBody<T> msg(String message) {
        this.msg = message;
        return this;
    }

    public RestResponseBody<T> code(int outCode) {
        this.code = outCode;
        return this;
    }

    public RestResponseBody<T> data(T response) {
        this.data = response;
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return ResultCode.SUCCESS.equals(this.code);
    }
}
