package com.irh.transaction.services;

/**
 * Thrown if the entity is unavailable.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class EntityUnavailableException extends CoreServiceException{

    /**
     * Creates an instance of the {@link EntityUnavailableException} class.
     */
    public EntityUnavailableException(){
        super();
    }

    /**
     * Creates an instance of the {@link EntityUnavailableException} class with the given error message.
     *
     * @param message the error message.
     */
    public EntityUnavailableException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link EntityUnavailableException} class with the given error message and inner cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public EntityUnavailableException(String message, Throwable cause){
        super(message, cause);
    }
}
