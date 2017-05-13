package com.irh.transaction.dao;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.dto.search.BranchSearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Branch} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface BranchMapper{

    /**
     * Saves the branch.
     *
     * @param branch the branch to save.
     */
    @Transactional
    void save(Branch branch);

    /**
     * Saves the branch POS counter.
     *
     * @param branchId the branch id.
     */
    @Transactional
    void savePosCounter(@Param("branchId") long branchId);

    /**
     * Updates the branch.
     *
     * @param branch the branch to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(Branch branch);

    /**
     * Finds the branch by id.
     *
     * @param id the branch id.
     * @return the retrieved branch, null if not found.
     */
    Branch findOne(long id);

    /**
     * Checks if the branch with the given name and group id exists.
     *
     * @param name    the branch name.
     * @param groupId the branch group id.
     * @return the branch id, null if not found.
     */
    Long checkByNameAndAreaId(@Param("name") String name, @Param("groupId") long groupId);

    /**
     * Searches branches.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Branch> search(@Param("filter") BranchSearchFilter filter);

    /**
     * Counts the number of branches that match the filter.
     *
     * @param filter the filter.
     * @return the number of branches that match the filter.
     */
    long count(@Param("filter") BranchSearchFilter filter);

    /**
     * Finds all branch cities.
     *
     * @param hqId the headquarter id.
     * @return the retrieved list of cities.
     */
    List<String> getBranchCities(long hqId);

    /**
     * find max branch on in all branch
     *
     * @return the retrieved branch no of branch.
     */
    Integer getMaxBranchNo(@Param("hqId") long hqId);

    /**
     * Gets the device counter for the new POS of the given branch.
     *
     * @param branchId the branch id.
     * @return the device counter.
     */
    int getPosCounter(long branchId);
}
