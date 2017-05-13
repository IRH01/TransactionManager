package com.irh.transaction.dao;

import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.dto.search.SearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link BranchProductStatusRecord} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface BranchProductStatusRecordMapper{

    /**
     * Saves the branch product status record.
     *
     * @param record the branch product status record to save.
     */
    @Transactional
    void save(BranchProductStatusRecord record);

    /**
     * Deletes the branch product status record by id.
     *
     * @param id the branch product status record id.
     * @return the number of affected rows.
     */
    @Transactional
    int delete(long id);

    /**
     * Checks if the status record of the given branch and product exists.
     *
     * @param branchId  the branch id.
     * @param productId the product id.
     * @return the id of the existing status record, null if not found.
     */
    Long checkByBranchAndProduct(@Param("branchId") long branchId, @Param("productId") long productId);

    /**
     * Searches branch product status records.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<BranchProductStatusRecord> search(@Param("filter") SearchFilter filter);

    /**
     * Counts the number of branch product status records that match the filter.
     *
     * @param filter the filter.
     * @return the number of branch product status records that match the filter.
     */
    long count(@Param("filter") SearchFilter filter);

    @Transactional
    void update(BranchProductStatusRecord record);
}
