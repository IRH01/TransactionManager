package com.irh.transaction.services.impl;

import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.VipCardReportService;
import com.irh.transaction.dao.VipCardReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of the {@link VipCardReportService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class VipCardReportServiceImpl implements VipCardReportService {
    /**
     * The mapper to manage {@link VipCardReport} in persistence.
     */
    @Autowired
    private VipCardReportMapper mapper;

    /**
     * Searches the VIP card reports by filter.
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
    public SearchResult<VipCardReport> search(ReportSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<VipCardReport> items = mapper.search(filter);
            if (filter.isGroupByBranch()) {
                return ServiceHelper.createSearchResult(items, items.size(), filter);
            }
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
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
    public VipCardReport getTotal(ReportSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            return mapper.getTotal(filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
