package com.irh.transaction.services;

import com.irh.transaction.model.account.Role;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link Role}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface RoleService{

    /**
     * Saves the role.
     *
     * @param role the role to save.
     * @throws IllegalArgumentException if the account is null.
     * @throws EntityExistsException    if a role of the headquarter with the same name already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Role role) throws CoreServiceException;

    /**
     * Updates the role.
     *
     * @param role the role to update.
     * @throws IllegalArgumentException if the account is null.
     * @throws EntityExistsException    if a role of the headquarter with the same name already exists.
     * @throws EntityNotFoundException  if the account cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Role role) throws CoreServiceException;

    /**
     * Finds the role by id.
     *
     * @param id the role id.
     * @return the retrieved role, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    Role findOne(long id) throws CoreServiceException;

    /**
     * Searches roles.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Role> search(NamedEntitySearchFilter filter) throws CoreServiceException;
}
