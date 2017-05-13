package com.irh.transaction.services;

import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.statistic.BranchShiftReport;

/**
 * Defines a contract for managing {@link BranchShiftReportService}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface BranchShiftReportService{

    /**
     * Saves the branch shift report.
     *
     * @param report the report to save.
     * @throws IllegalArgumentException if the argument is null; if the <em>report.branch</em> or the <em>report.account</em> is null.
     * @throws EntityExistsException    if the report of the same branch, device and start time already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(BranchShiftReport report) throws CoreServiceException;

    /**
     * Searches branch shift reports.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<BranchShiftReport> search(DateFilter filter) throws CoreServiceException;
}
