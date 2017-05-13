package com.irh.transaction.services;

import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.model.common.Headquarter;

import java.util.List;

/**
 * Defines a contract for managing {@link Branch}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> The methods to manage {@link Headquarter} and {@link BranchGroup} are
 * moved to the corresponding services respectively. </li> <li> Added the {@link BranchService#getPosCounter(long)}
 * method. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 * @see BranchGroupService
 * @see HeadquarterService
 */
public interface BranchService{

    /**
     * Saves the branch.
     *
     * @param branch the branch to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a branch with the given name of the same group already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Branch branch) throws CoreServiceException;

    /**
     * Updates the branch.
     *
     * @param branch the branch to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a branch with the given name of the same group already exists.
     * @throws EntityNotFoundException  if the branch cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Branch branch) throws CoreServiceException;

    /**
     * Finds the branch by id.
     *
     * @param id the branch id.
     * @return the retrieved branch, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    Branch findOne(long id) throws CoreServiceException;

    /**
     * Searches branches.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Branch> search(BranchSearchFilter filter) throws CoreServiceException;

    /**
     * Gets all branch cities.
     *
     * @param hqId the headquarter id.
     * @return the retrieved list of cities.
     * @throws CoreServiceException if any error occurs.
     */
    List<String> getBranchCities(long hqId) throws CoreServiceException;

    /**
     * Gets the device counter for the new POS of the given branch.
     *
     * @param branchId the branch id.
     * @return the device counter.
     * @throws EntityNotFoundException if the branch cannot be found.
     * @throws CoreServiceException    if any other error occurs.
     */
    int getPosCounter(long branchId) throws CoreServiceException;
}
