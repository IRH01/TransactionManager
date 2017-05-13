package com.irh.transaction.services.impl;

import com.irh.transaction.dao.BranchGroupMapper;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link BranchGroupService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class BranchGroupServiceImpl implements BranchGroupService{

    /**
     * The mapper to manage {@link BranchGroup} in persistence.
     */
    @Autowired
    private BranchGroupMapper mapper;

    /**
     * Saves the branchGroup.
     *
     * @param branchGroup the branchGroup to save.
     * @throws IllegalArgumentException if the argument is null or if the <em>branchGroup.manager</em> is null.
     * @throws EntityExistsException    if the branchGroup of the headquarter with the same name already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(BranchGroup branchGroup) throws CoreServiceException{
        ServiceHelper.checkNotNull(branchGroup, "branchGroup");
        try{
            if(mapper.checkByNameAndHqId(branchGroup.getName(), branchGroup.getHqId()) != null){
                throw new EntityExistsException("The branchGroup with the same name already exists.");
            }
            mapper.save(branchGroup);
        }catch(EntityExistsException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the branchGroup.
     *
     * @param branchGroup the branchGroup to update.
     * @throws IllegalArgumentException if the argument is null or if the <em>branchGroup.manager</em> is null.
     * @throws EntityExistsException    if the branchGroup of the headquarter with the same name already exists.
     * @throws EntityNotFoundException  if the branchGroup cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(BranchGroup branchGroup) throws CoreServiceException{
        ServiceHelper.checkNotNull(branchGroup, "branchGroup");
        try{
            Long duplicatedId = mapper.checkByNameAndHqId(branchGroup.getName(), branchGroup.getHqId());
            if(duplicatedId != null && duplicatedId != branchGroup.getId()){
                throw new EntityExistsException("The branchGroup with the same name already exists.");
            }
            if(mapper.update(branchGroup) == 0){
                throw new EntityNotFoundException("The branchGroup cannot be found.");
            }
        }catch(CoreServiceException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the branch group by id.
     *
     * @param id the branch group id.
     * @return the retrieved branch group, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    @Override
    public BranchGroup findOne(long id) throws CoreServiceException{
        try{
            return mapper.findOne(id);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches areas.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Override
    public SearchResult<BranchGroup> search(NamedEntitySearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try{
            List<BranchGroup> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
