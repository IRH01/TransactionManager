package com.irh.transaction.services;

import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link BranchTable}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface BranchTableService{

    /**
     * Saves the branch table.
     *
     * @param table the table to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a table with the same code and branch id already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(BranchTable table) throws CoreServiceException;

    /**
     * Updates the branch table.
     *
     * @param table the table to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a table with the same code and branch id already exists.
     * @throws EntityNotFoundException  if the table cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(BranchTable table) throws CoreServiceException;

    /**
     * Finds the branch table by id.
     *
     * @param id the table id.
     * @return the retrieved table, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    BranchTable findOne(long id) throws CoreServiceException;

    /**
     * Searches branch tables.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<BranchTable> search(SearchFilter filter) throws CoreServiceException;

}
