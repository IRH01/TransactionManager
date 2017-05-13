package com.irh.transaction.util.exceptions;

/**
 * The top level exception.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class HCHCException extends Exception {

    /**
     * Creates an instance of the {@linkHCHCException} class.
     */
    public HCHCException() {
        super();
    }

    /**
     * Creates an instance of the {@linkHCHCException} class with the given error message.
     *
     * @param message
     *         the error message.
     */
    public HCHCException(String message) {
        super(message);
    }

    /**
     * Creates an instance of the {@linkHCHCException} class with the given error message and inner cause.
     *
     * @param message
     *         the error message.
     * @param cause
     *         the inner cause.
     */
    public HCHCException(String message, Throwable cause) {
        super(message, cause);
    }
}
