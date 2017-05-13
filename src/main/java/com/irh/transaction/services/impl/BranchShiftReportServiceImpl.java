package com.irh.transaction.services.impl;

import com.irh.transaction.dao.BranchShiftReportMapper;
import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.statistic.BranchShiftReport;
import com.irh.transaction.services.BranchShiftReportService;
import com.irh.transaction.services.CorePersistenceException;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link BranchShiftReportService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class BranchShiftReportServiceImpl implements BranchShiftReportService{

    /**
     * The mapper to manage {@link BranchShiftReport} in persistence.
     */
    @Autowired
    private BranchShiftReportMapper mapper;

    /**
     * Saves the branch shift report.
     *
     * @param report the report to save.
     * @throws IllegalArgumentException if the argument is null; if the <em>report.branch</em> or the <em>report.account</em> is null.
     * @throws EntityExistsException    if the report of the same branch, device and start time already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(BranchShiftReport report) throws CoreServiceException{
        ServiceHelper.checkNotNull(report, "report");
        ServiceHelper.checkNotNull(report.getBranch(), "branch");
        ServiceHelper.checkNotNull(report.getAccount(), "account");
        try{
            if(mapper.checkExists(
                    report.getBranch().getId(), report.getDevice(), report.getStartTime()) != null){
                throw new EntityExistsException(
                        "The report of the same branch, device and start time already exists.");
            }
            mapper.save(report);
        }catch(EntityExistsException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

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
    @Override
    public SearchResult<BranchShiftReport> search(DateFilter filter) throws CoreServiceException{
        ServiceHelper.checkNotNull(filter, "filter");
        if(filter.getBranchId() == null){
            throw new IllegalArgumentException("The filter.branchId cannot be null");
        }
        ServiceHelper.checkSearchFilter(filter, false);
        try{
            List<BranchShiftReport> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
