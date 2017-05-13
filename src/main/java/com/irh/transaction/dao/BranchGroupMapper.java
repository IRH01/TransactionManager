package com.irh.transaction.dao;

import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link BranchGroup} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface BranchGroupMapper{

    /**
     * Saves the branchGroup.
     *
     * @param branchGroup the branchGroup to save.
     */
    @Transactional
    void save(BranchGroup branchGroup);

    /**
     * Updates the branchGroup.
     *
     * @param branchGroup the branchGroup to update.
     * @return the number of affected row.
     */
    @Transactional
    int update(BranchGroup branchGroup);

    /**
     * Finds the branch group by id.
     *
     * @param id the branch group id.
     * @return the retrieved branch group, null if not found.
     */
    BranchGroup findOne(long id);

    /**
     * Checks if the area with the given name and headquarter id already exists.
     *
     * @param name the name.
     * @param hqId the headquarter id.
     * @return the area id, null if not found.
     */
    Long checkByNameAndHqId(@Param("name") String name, @Param("hqId") long hqId);

    /**
     * Searches areas.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<BranchGroup> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of areas that match the filter.
     *
     * @param filter the filter.
     * @return the number of areas that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);
}
