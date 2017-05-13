package com.irh.transaction.services;

import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines contract for managing {@link VipCardReport}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface VipCardReportService{

    /**
     * Searches the VIP card reports by filter.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<VipCardReport> search(ReportSearchFilter filter) throws CoreServiceException;

    /**
     * Gets the total summary report.
     *
     * @param filter the filter.
     * @return the report containing total summary.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    VipCardReport getTotal(ReportSearchFilter filter) throws CoreServiceException;
}
