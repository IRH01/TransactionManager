package com.irh.transaction.services;

import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link PrintType}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface PrintTypeService{

    /**
     * Saves the print type.
     *
     * @param printType the print type to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(PrintType printType) throws CoreServiceException;

    /**
     * Updates the print type.
     *
     * @param printType the print type to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityNotFoundException  if the print type cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(PrintType printType) throws CoreServiceException;

    /**
     * Finds the print type by id.
     *
     * @param id the print type id.
     * @return the retrieved print type, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    PrintType findOne(long id) throws CoreServiceException;

    /**
     * Searches print types.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<PrintType> search(NamedEntitySearchFilter filter) throws CoreServiceException;
}
