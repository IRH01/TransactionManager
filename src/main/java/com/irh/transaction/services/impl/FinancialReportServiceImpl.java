package com.irh.transaction.services.impl;

import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.FinancialReportService;
import com.irh.transaction.dao.FinancialReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Default implementation of the {@link FinancialReportService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class FinancialReportServiceImpl implements FinancialReportService {

    /**
     * The mapper to manage {@link FinancialReport} in persistence.
     */
    @Autowired
    private FinancialReportMapper mapper;

    /**
     * Creates a financial reports of the headquarter and date.
     *
     * <p> If the reports of the same headquarter and date exists, they will be deleted and recreated. </p>
     *
     * @param hqId
     *         the headquarter id.
     * @param date
     *         the date.
     *
     * @throws IllegalArgumentException
     *         if the date is null or greater than the current time.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void create(long hqId, Date date) throws CoreServiceException{
        ServiceHelper.checkNotNull(date, "date");
        if (date.getTime() > new Date().getTime()) {
            throw new IllegalArgumentException("The date is greater than the current time.");
        }
        try {
            mapper.create(hqId, date);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the financial report by id.
     *
     * @param id
     *         the financial report id.
     *
     * @return the retrieved financial report, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public FinancialReport findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches financial reports by filter.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *         or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<FinancialReport> search(ReportSearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<FinancialReport> items = mapper.search(filter);
            long count = !filter.isGroupByBranch() && filter.getPage() != null ?
                    mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the total summary report.
     *
     * @param filter
     *         the filter.
     *
     * @return the report containing total summary.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *         or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public FinancialReport getTotal(ReportSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            return mapper.getTotal(filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
