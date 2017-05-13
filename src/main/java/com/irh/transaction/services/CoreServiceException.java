package com.irh.transaction.services;


import com.irh.transaction.utils.exceptions.HCHCException;

/**
 * The top level exception thrown by service implementations.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class CoreServiceException extends HCHCException{

    /**
     * Creates an instance of the {@link CoreServiceException} class.
     */
    public CoreServiceException(){
        super();
    }

    /**
     * Creates an instance of the {@link CoreServiceException} class with the given error message.
     *
     * @param message the error message.
     */
    public CoreServiceException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link CoreServiceException} class with the given error message and inner cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public CoreServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
