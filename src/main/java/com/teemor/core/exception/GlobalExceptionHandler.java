package com.teemor.core.exception;


import cn.hutool.core.util.StrUtil;
import com.teemor.core.model.RestResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * common exception handler, handle the follow exceptions:
 *
 * @author Gary.Jin
 * {@link RuntimeException}
 * {@link Throwable}
 * {@link MissingServletRequestParameterException}
 * {@link HttpMessageNotReadableException}
 * {@link NoHandlerFoundException}
 * {@link MethodArgumentNotValidException}
 * {@link BindException}
 * {@link javax.validation.ConstraintViolationException}
 * @date 2021/8/20 3:06 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponseBody<String> handleRunTimeException(RuntimeException e) {
        log.error("RuntimeException==>{}", e.getMessage(), e);
        return RestResponseBody.failure(ResultStatus.BAD_REQUEST, e.getMessage());
    }


    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponseBody<String> handleThrowable(Throwable e) {
        log.error("Throwable==>{}", e.getMessage(), e);
        return RestResponseBody.failure(ResultStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseBody<String> handleMissingRequestParameterException(Exception e) {
        log.error("MissingServletRequestParameterException == >", e);
        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, e.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseBody<String> handleBadRequest(Exception e) {
        log.error("Bad Request Execption == >", e);
        return RestResponseBody.failure(
                ResultStatus.BAD_REQUEST, "unable to parse request or unacceptable body");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponseBody<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("NoHandlerFoundException==>{}", e.getMessage(), e);
        return RestResponseBody.failure(ResultStatus.RESOURCE_NOT_FOUND, e.getHttpMethod() + " " + e.getRequestURL());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public RestResponseBody<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException==>{}", e.getMessage(), e);
        List<String> errMsgList = new LinkedList<>();
        e.getBindingResult().getAllErrors().forEach(error -> errMsgList.add(
                ((FieldError) error).getField() + StrUtil.SPACE + error.getDefaultMessage())
        );
        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, errMsgList.toString());
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public RestResponseBody<Void> handleBindException(BindException e) {
        log.error("BindException==>{}", e.getMessage(), e);
        List<String> errMsgList = new LinkedList<>();
        e.getBindingResult().getAllErrors().forEach(error -> errMsgList.add(
                ((FieldError) error).getField() + StrUtil.SPACE + error.getDefaultMessage())
        );
        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, errMsgList.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public RestResponseBody<Void> handleViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException==>{}", e.getMessage(), e);
        List<String> errorList = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, errorList.toString());
    }


    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public RestResponseBody<Void> handleInvalidParameterException(InvalidParameterException e) {
        log.error("InvalidParameterException==>{}", e.getMessage(), e);

        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public RestResponseBody<Void> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("ResourceNotFoundException==>{}", e.getMessage(), e);

        return RestResponseBody.failure(ResultStatus.INVALID_PARAM, e.getMessage());
    }

}
