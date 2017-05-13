package com.irh.transaction.services;

/**
 * Thrown if duplicate entity exists.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class EntityExistsException extends CoreServiceException{
    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityExistsException} class.
     */
    public EntityExistsException(){
        super();
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityExistsException} class with the given error message.
     *
     * @param message the error message.
     */
    public EntityExistsException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityExistsException} class with the given error message and inner cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public EntityExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
