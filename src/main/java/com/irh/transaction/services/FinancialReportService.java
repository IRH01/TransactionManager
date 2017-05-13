package com.irh.transaction.services;

import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;

import java.util.Date;

/**
 * Defines contract for managing {@link FinancialReport}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface FinancialReportService{

    /**
     * Creates a financial reports of the headquarter and date.
     *
     * <p> If the reports of the same headquarter and date exists, they will be deleted and recreated. </p>
     *
     * @param hqId the headquarter id.
     * @param date the date.
     * @throws IllegalArgumentException if the date is null or greater than the current time.
     * @throws CoreServiceException     if any other error occurs.
     */
    void create(long hqId, Date date) throws CoreServiceException;

    /**
     * Finds the financial report by id.
     *
     * @param id the financial report id.
     * @return the retrieved financial report, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    FinancialReport findOne(long id) throws CoreServiceException;

    /**
     * Searches financial reports by filter.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<FinancialReport> search(ReportSearchFilter filter)
            throws CoreServiceException;

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
    FinancialReport getTotal(ReportSearchFilter filter) throws CoreServiceException;
}
