package com.irh.transaction.services.impl;

import com.irh.transaction.dao.BranchProductStatusRecordMapper;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link BranchProductStatusRecordService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class BranchProductStatusRecordServiceImpl implements BranchProductStatusRecordService{

    /**
     * The mapper to manage {@link BranchProductStatusRecordMapper} in persistence.
     */
    @Autowired
    private BranchProductStatusRecordMapper mapper;

    /**
     * Saves the branch product status record.
     *
     * @param record the branch product status record to save.
     * @throws IllegalArgumentException if the argument is null or if the <em>record.product</em> is null or if the <em>record.branch</em> is
     *                                  null or if the <em>record.status</em> is <em>ONSALE</em>.
     * @throws EntityExistsException    if a status record for the same branch and product already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(BranchProductStatusRecord record) throws CoreServiceException{
        ServiceHelper.checkNotNull(record, "record");
        ServiceHelper.checkNotNull(record.getProduct(), "record.product");
        ServiceHelper.checkNotNull(record.getBranch(), "record.branch");
        if(ProductStatus.ONSALE.equals(record.getStatus())){
            throw new IllegalArgumentException("The status record status cannot be ONSAEL");
        }
        try{
            if(mapper.checkByBranchAndProduct(record.getBranch().getId(),
                    record.getProduct().getId()) != null){
                throw new EntityExistsException(
                        "A status record for the same branch and product already exist.");
            }
            mapper.save(record);
        }catch(EntityExistsException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Deletes the branch product status record by id.
     *
     * @param id the branch product status record id.
     * @throws EntityNotFoundException if the branch product status record cannot be found.
     * @throws CoreServiceException    if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void delete(long id) throws CoreServiceException{
        try{
            if(mapper.delete(id) == 0){
                throw new EntityNotFoundException("The branch product status record cannot be found.");
            }
        }catch(EntityNotFoundException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches branch product status records.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Override
    public SearchResult<BranchProductStatusRecord> search(SearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkNotNull(filter, "filter");
        ServiceHelper.checkNotNull(filter.getBranchId(), "filter.branchId");
        try{
            List<BranchProductStatusRecord> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    @Override
    public void update(BranchProductStatusRecord productStatusRecord) throws CorePersistenceException{
        ServiceHelper.checkNotNull(productStatusRecord.getId(), "id");
        ServiceHelper.checkNotNull(productStatusRecord.getStatus(), "product status record status");
        try{
            mapper.update(productStatusRecord);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence", ex);
        }
    }
}
