package com.irh.transaction.services;

import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link BranchGroup}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface BranchGroupService{

    /**
     * Saves the branchGroup.
     *
     * @param branchGroup the branchGroup to save.
     * @throws IllegalArgumentException if the argument is null or if the <em>branchGroup.manager</em> is null.
     * @throws EntityExistsException    if the branchGroup of the headquarter with the same name already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(BranchGroup branchGroup) throws CoreServiceException;

    /**
     * Updates the branchGroup.
     *
     * @param branchGroup the branchGroup to update.
     * @throws IllegalArgumentException if the argument is null or if the <em>branchGroup.manager</em> is null.
     * @throws EntityExistsException    if the branchGroup of the headquarter with the same name already exists.
     * @throws EntityNotFoundException  if the branchGroup cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(BranchGroup branchGroup) throws CoreServiceException;

    /**
     * Finds the branch group by id.
     *
     * @param id the branch group id.
     * @return the retrieved branch group, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    BranchGroup findOne(long id) throws CoreServiceException;

    /**
     * Searches areas.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<BranchGroup> search(NamedEntitySearchFilter filter) throws CoreServiceException;
}
