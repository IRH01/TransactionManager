package com.irh.transaction.dao;

import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.dto.search.SearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link BranchTable} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface BranchTableMapper{

    /**
     * Saves the branch table.
     *
     * @param table the table to save.
     */
    @Transactional
    void save(BranchTable table);

    /**
     * Updates the branch table.
     *
     * @param table the table to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(BranchTable table);

    /**
     * Finds the branch table by id.
     *
     * @param id the table id.
     * @return the retrieved table, null if not found.
     */
    BranchTable findOne(long id);

    /**
     * Checks if the table with the given code and branch id exists.
     *
     * @param code     the code.
     * @param branchId the branch id.
     * @return the id of the table with the given code and branch id, null if not found.
     */
    Long checkByCodeAndBranchId(@Param("code") String code, @Param("branchId") long branchId);

    /**
     * Searches branch tables.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<BranchTable> search(@Param("filter") SearchFilter filter);

    /**
     * Counts the number of branch tables that match the filter.
     *
     * @param filter the filter.
     * @return the number of branch tables that match the filter.
     */
    long count(@Param("filter") SearchFilter filter);

}
