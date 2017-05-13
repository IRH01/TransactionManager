package com.irh.transaction.utils.exceptions;


/**
 * Thrown by web API implementation. It provides an optional error code, which can be accessed via {@link
 * ApiException#getCode()}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ApiException extends HCHCException{

    /**
     * The error code.
     */
    private String code;

    /**
     * Creates an instance of the {@link ApiException} class with the given error code.
     *
     * @param code
     *         the error code.
     */
    public ApiException(String code) {
        super();
        this.code = code;
    }

    /**
     * Creates an instance of the {@link ApiException} class with the given error message and error code.
     *
     * @param message
     *         the error message.
     * @param code
     *         the error code.
     */
    public ApiException(String message, String code) {
        super(message);
        this.code = code;
    }

    /**
     * Creates an instance of the {@link ApiException} class with the given error message and inner cause.
     *
     * @param message
     *         the error message.
     * @param cause
     *         the inner cause.
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an instance of the {@link ApiException} class with the given error message, inner cause and error code.
     *
     * @param message
     *         the error message.
     * @param cause
     *         the inner cause.
     * @param code
     *         the error code.
     */
    public ApiException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Gets the error code.
     *
     * @return the error code.
     */
    public String getCode() {
        return code;
    }
}
