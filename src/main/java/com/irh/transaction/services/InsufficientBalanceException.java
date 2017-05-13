package com.irh.transaction.services;

/**
 * Thrown if the balance of a VIP card is insufficient.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class InsufficientBalanceException extends CoreServiceException{

    /**
     * Creates an instance of the {@link com.irh.transcation.services.InsufficientBalanceException} class.
     */
    public InsufficientBalanceException(){
        super();
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.InsufficientBalanceException} class with the given error message.
     *
     * @param message the error message.
     */
    public InsufficientBalanceException(String message){
        super(message);
    }

    /**
     * Creates an instance of the {@link com.irh.transcation.services.InsufficientBalanceException} class with the given error message and inner
     * cause.
     *
     * @param message the error message.
     * @param cause   the inner cause.
     */
    public InsufficientBalanceException(String message, Throwable cause){
        super(message, cause);
    }
}
