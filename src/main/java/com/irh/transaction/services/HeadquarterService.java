package com.irh.transaction.services;

import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link Headquarter}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface HeadquarterService{

    /**
     * Saves the headquarter.
     *
     * @param headquarter the headquarter to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if the headquarter with the same code already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Headquarter headquarter) throws CoreServiceException;

    /**
     * Updates the headquarter.
     *
     * @param headquarter the headquarter to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityNotFoundException  if the headquarter cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Headquarter headquarter) throws CoreServiceException;

    /**
     * Searches headquarters.
     *
     * @return the search result.
     * @throws IllegalArgumentException if the <em>filter.page</em> is not positive or <em>filter.size</em> is not positive; if the
     *                                  <em>filter.page</em> is provided and the <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    SearchResult<Headquarter> search(NamedEntitySearchFilter filter) throws CoreServiceException;
}
