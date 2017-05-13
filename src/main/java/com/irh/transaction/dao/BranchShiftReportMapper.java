package com.irh.transaction.dao;

import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.model.statistic.BranchShiftReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Represents the mapper that provides operations to {@link FinancialReport} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface BranchShiftReportMapper{

    /**
     * Saves the branch shift report.
     *
     * @param report the report to save.
     */
    @Transactional
    void save(BranchShiftReport report);

    /**
     * Searches branch shift reports.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<BranchShiftReport> search(@Param("filter") DateFilter filter);

    /**
     * Counts the number of branch shift reports that match the filter.
     *
     * @param filter the filter.
     * @return the number of branch shift reports that match the filter.
     */
    long count(@Param("filter") DateFilter filter);

    /**
     * Checks if the report with the same branch, device and start time exists.
     *
     * @param branchId  the branch id.
     * @param device    the device number.
     * @param startTime the start time.
     * @return the existing report id, null if not found.
     */
    Long checkExists(@Param("branchId") long branchId, @Param("device") int device, @Param("startTime") Date startTime);
}
