package com.irh.transaction.exceptions.web;

/**
 * Contains code, message and data for an web API response.
 *
 * <p> Same general response codes and messages are defined as public constants in this class. </p>
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ApiResponse {

    public static final String OK_CODE = "0";

    public static final String OK_MSG = "OK";

    public static final String DEFAULT_ERROR_CODE = "01";

    public static final String DEFAULT_ERROR_MSG = "Failed";

    public static final String ACCESS_DENIED_CODE = "02";

    public static final String ACCESS_DENIED_MSG = "Access Denied";

    public static final String ENTITY_EXISTS_CODE = "03";

    public static final String ENTITY_EXISTS_MSG = "Entity Already Exists";

    public static final String INVALID_ENTITY_CODE = "04";

    public static final String INVALID_ENTITY_MSG = "Invalid Entity";

    public static final String ENTITY_NOT_FOUND_CODE = "05";

    public static final String ENTITY_NOT_FOUND_MSG = "Entity Not Found";

    public static final String BALANCE_INSUFFICIENT_CODE = "06";

    public static final String BALANCE_INSUFFICIENT_MSG = "Balance Insufficient";

    public static final String ENTITY_UNAVAILABLE_CODE = "07";

    /**
     * The code.
     */
    private String code = OK_CODE;

    /**
     * The message.
     */
    private String message = OK_MSG;

    /**
     * The response data.
     */
    private Object data;

    /**
     * Creates a new instance of the {@link com.irh.transaction.exceptions.web.ApiResponse}.
     */
    public ApiResponse() {
    }

    /**
     * Creates a new instance of the {@link com.irh.transaction.exceptions.web.ApiResponse} with the given code and message.
     *
     * @param code
     *         the code.
     * @param message
     *         the message.
     */
    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Creates a new instance of the {@link com.irh.transaction.exceptions.web.ApiResponse} with the given data.
     *
     * @param data
     *         the data.
     */
    public ApiResponse(Object data) {
        this.data = data;
    }

    /**
     * Creates a new instance of the {@link com.irh.transaction.exceptions.web.ApiResponse} with the given code, message and data.
     *
     * @param code
     *         the code.
     * @param message
     *         the message.
     * @param data
     *         the data.
     */
    public ApiResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Gets the code.
     *
     * @return the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the message.
     *
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the data.
     *
     * @return the data.
     */
    public Object getData() {
        return data;
    }
}
