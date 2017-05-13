package com.irh.transaction.services;


/**
 * Thrown if error occurs when accessing the persistence.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class CorePersistenceException extends CoreServiceException{
    /**
     * Creates an instance of the {@link CorePersistenceException} class.
     */
    public CorePersistenceException(){
        super();
    }

    /**
     * Creates an instance of the {@link CorePersistenceException} class with the given error message.
     *
     * @param message the error message.
     */
    public CorePersistenceException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link CorePersistenceException} class with the given error message and inner cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public CorePersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
