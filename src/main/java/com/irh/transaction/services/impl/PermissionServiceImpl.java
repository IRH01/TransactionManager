package com.irh.transaction.services.impl;

import com.irh.transaction.model.account.Permission;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.PermissionService;
import com.irh.transaction.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Defines a contract for managing {@link Permission}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    /**
     * The mapper to manage {@link Permission} in persistence.
     */
    @Autowired
    private PermissionMapper mapper;

    /**
     * Gets all permissions.
     *
     * @return the list of permissions.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public List<Permission> findAll() throws CoreServiceException{
        try {
            return mapper.findAll();
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
