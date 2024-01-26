package com.teemor.core.exception;

/**
 * core cloud services list and error code
 *
 * @author lujing
 * @since 0.0.1
 */
public final class ResultCode {

    private ResultCode() {
    }


    public static final Integer SUCCESS = 0;

    public static final String SUCCESS_MSG = "success";

    public static final String INVALID_ERROR_MSG = "Invalid parameter: ";

    public static final String NOT_FOUND_ERROR_MSG = "Resource not found: ";

    public static final String BODY_ERROR_MSG = "unable to parse request or unacceptable body: ";

    public static final String PATH_ERROR_MSG = "Request path not found: ";

    public static final String UNAUTHORIZED_ERROR_MSG = "Unauthorized access error: ";

    /**
     * http 500 error code
     */
    public static final Integer SERVER_ERROR = 10000;
    /**
     * Database access error
     */
    public static final Integer DATABASE_ERROR = 10001;

    /**
     * Message broker access error
     */
    public static final Integer MIDDLEWARE_ERROR = 10002;

    /**
     * Error on API invocation
     */
    public static final Integer API_INVOCATION_ERROR = 10003;

    /**
     * No handler found
     */
    public static final Integer ERROR_URI = 10004;


    /**
     * http 422 error code
     * Invalid parameter, xxxx is required(Message will be different based on the parrameter)
     */
    public static final Integer INVALID_PARAMETER_ERROR = 10005;

    /**
     * http 404 error code
     * resource not found
     */
    public static final Integer NOT_FOUND_ERROR = 10006;

    /**
     * resource not found
     */
    public static final Integer BAD_REQUEST = 10007;

    /**
     * Unauthorized access error
     */
    public static final Integer UNAUTHORIZED_ERROR = 10008;


}
