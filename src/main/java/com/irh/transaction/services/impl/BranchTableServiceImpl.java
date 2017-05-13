package com.irh.transaction.services.impl;

import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.*;
import com.irh.transaction.dao.BranchTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link BranchTableService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class BranchTableServiceImpl implements BranchTableService{

    /**
     * The mapper to manage {@link BranchTable} in persistence.
     */
    @Autowired
    private BranchTableMapper mapper;

    /**
     * Saves the branch table.
     *
     * @param table the table to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a table with the same code and branch id already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(BranchTable table) throws CoreServiceException{
        ServiceHelper.checkNotNull(table, "table");
        try {
            if (mapper.checkByCodeAndBranchId(table.getCode(), table.getBranchId()) != null) {
                throw new EntityExistsException(
                        "A table with the same code and branch id already exists.");
            }
            mapper.save(table);
        } catch (EntityExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the branch table.
     *
     * @param table the table to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityExistsException    if a table with the same code and branch id already exists.
     * @throws EntityNotFoundException  if the table cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional
    @Override
    public void update(BranchTable table) throws CoreServiceException{
        ServiceHelper.checkNotNull(table, "table");
        try {
            Long duplicateId = mapper.checkByCodeAndBranchId(table.getCode(), table.getBranchId());
            if (duplicateId != null && !duplicateId.equals(table.getId())) {
                throw new EntityExistsException(
                        "A table with the same code and branch id already exists.");
            }
            if (mapper.update(table) == 0) {
                throw new EntityNotFoundException("The branch table cannot be found.");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the branch table by id.
     *
     * @param id the table id.
     * @return the retrieved table, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    @Override
    public BranchTable findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches branch tables.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Override
    public SearchResult<BranchTable> search(SearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkNotNull(filter, "filter");
        ServiceHelper.checkNotNull(filter.getBranchId(), "filter.branchId");
        ServiceHelper.checkSearchFilter(filter, false);
        try {
            List<BranchTable> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
