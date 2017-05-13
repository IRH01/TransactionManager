package com.irh.transaction.dao;

import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.model.finance.FinancialReport;
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
public interface FinancialReportMapper{

    /**
     * Creates a financial reports of the headquarter and date.
     *
     * <p> If the reports of the same headquarter and date exists, they will be deleted and recreated. </p>
     *
     * @param hqId the headquarter id.
     * @param date the date.
     */
    @Transactional
    void create(@Param("hqId") long hqId, @Param("date") Date date);

    /**
     * Finds the financial report by id.
     *
     * @param id the financial report id.
     * @return the retrieved financial report, null if not found.
     */
    FinancialReport findOne(long id);

    /**
     * Searches financial reports.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<FinancialReport> search(@Param("filter") ReportSearchFilter filter);

    /**
     * Counts the number of financial reports that match the filter.
     *
     * @param filter the filter.
     * @return the number of financial reports that match the filter.
     */
    long count(@Param("filter") ReportSearchFilter filter);

    /**
     * Gets the total summary report.
     *
     * @param filter the filter.
     * @return the report containing total summary.
     */
    FinancialReport getTotal(@Param("filter") ReportSearchFilter filter);
}
