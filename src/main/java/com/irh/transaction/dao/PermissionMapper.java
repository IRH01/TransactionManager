package com.irh.transaction.dao;

import com.irh.transaction.model.account.Permission;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Permission} in persistence.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface PermissionMapper{

    /**
     * Gets all permissions.
     *
     * @return the list of permissions.
     */
    List<Permission> findAll();
}
