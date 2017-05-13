package com.irh.transaction.services;

import com.irh.transaction.model.account.Permission;

import java.util.List;

/**
 * Defines a contract for managing {@link Permission}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface PermissionService{

    /**
     * Gets all permissions.
     *
     * @return the list of permissions.
     * @throws CoreServiceException if any error occurs.
     */
    List<Permission> findAll() throws CoreServiceException;
}
