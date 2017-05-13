package com.irh.transaction.services;

import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link BranchProductStatusRecord}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @since 1.1
 */
public interface BranchProductStatusRecordService{

    /**
     * Saves the branch product status record.
     *
     * @param record the branch product status record to save.
     * @throws IllegalArgumentException if the argument is null or if the <em>record.product</em> is null or if the <em>record.branch</em> is
     *                                  null or if the <em>record.status</em> is <em>ONSALE</em>.
     * @throws EntityExistsException    if a status record for the same branch and product already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(BranchProductStatusRecord record) throws CoreServiceException;

    /**
     * Deletes the branch product status record by id.
     *
     * @param id the branch product status record id.
     * @throws EntityNotFoundException if the branch product status record cannot be found.
     * @throws CoreServiceException    if any other error occurs.
     */
    void delete(long id) throws CoreServiceException;

    /**
     * Searches branch product status records.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<BranchProductStatusRecord> search(SearchFilter filter) throws CoreServiceException;

    void update(BranchProductStatusRecord productStatusRecord) throws CorePersistenceException;
}
