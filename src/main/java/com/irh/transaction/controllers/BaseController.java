package com.irh.transaction.controllers;

import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import com.irh.transaction.services.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The base class for controllers of this component.
 *
 * <p> The current logged account can be retrieved via the {@link BaseController#getAccount()}. Please also note that it
 * is recommended to invoke {@link BaseController#getAccount()} only once and assign the result to a variable. Because
 * it is not a simple getter of field. It will retrieve the {@link Authentication} from the current {@link
 * SecurityContext} and determines if the {@link Authentication} is anonymous.</p>
 *
 * <p> A {@link Logger} instance for logging can be retrieved via {@link BaseController#getLogger()}. </p>
 *
 * <p> All thrown {@link ApiException} by subclass is handled by {@link BaseController#handleApiException(ApiException)}.
 * </p>
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public abstract class BaseController {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Gets the logger.
     *
     * @return the logger.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Gets the current employee account.
     *
     * <p><b>Note:</b> It is not a simple getter of field. It will retrieve the {@link Authentication} from the current
     * {@link SecurityContext} and determines if the {@link Authentication} is anonymous.</p>
     *
     * @return the current employee account.
     *
     * @see SecurityContext
     * @see Authentication
     */
    protected Account getAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            return (Account) auth.getPrincipal();
        }
        return null;
    }

    /**
     * Handles the {@link ApiException}.
     *
     * @param ex
     *         the thrown {@link ApiException}.
     *
     * @return the HTTP response entity.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiException(ApiException ex) {
        Throwable cause = ex.getCause();
        if (cause != null && cause instanceof CoreServiceException) {
            if (cause instanceof EntityExistsException) {
                return new ResponseEntity<>(new ApiResponse(
                        ApiResponse.ENTITY_EXISTS_CODE, ApiResponse.ENTITY_EXISTS_MSG),
                        HttpStatus.OK);
            }
            if (cause instanceof EntityNotFoundException) {
                return new ResponseEntity<>(new ApiResponse(
                        ApiResponse.ENTITY_NOT_FOUND_CODE, ApiResponse.ENTITY_NOT_FOUND_MSG),
                        HttpStatus.OK);
            }
        } else if (ex.getCode() != null) {
            return new ResponseEntity<>(new ApiResponse(ex.getCode(), ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(ApiResponse.DEFAULT_ERROR_CODE,
                ApiResponse.DEFAULT_ERROR_MSG), HttpStatus.OK);
    }
}
