package com.irh.transaction.services;

/**
 * Thrown if the entity cannot be found.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class EntityNotFoundException extends CoreServiceException{
    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityNotFoundException} class.
     */
    public EntityNotFoundException(){
        super();
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityNotFoundException} class with the given error message.
     *
     * @param message the error message.
     */
    public EntityNotFoundException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.EntityNotFoundException} class with the given error message and inner cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
