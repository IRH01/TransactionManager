package com.irh.transaction.services.impl;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.*;
import com.irh.transaction.dao.BranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link BranchService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class BranchServiceImpl implements BranchService{

    /**
     * The mapper to manage {@link Branch} in persistence.
     */
    @Autowired
    private BranchMapper mapper;

    /**
     * Saves the branch.
     *
     * @param branch
     *         the branch to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityExistsException
     *         if a branch with the given name of the same group already exists.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Branch branch) throws CoreServiceException{
        ServiceHelper.checkNotNull(branch, "branch");
        try {
            if (mapper.checkByNameAndAreaId(branch.getName(), branch.getGroupId()) != null) {
                throw new EntityExistsException(
                        "The branch of the area with the same name already exists.");
            }
            Integer currentMaxBranchNo = mapper.getMaxBranchNo(branch.getHqId());
            branch.setNumber((currentMaxBranchNo == null ? 0 : currentMaxBranchNo) + 1);
            mapper.save(branch);
            mapper.savePosCounter(branch.getId());
        } catch (EntityExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the branch.
     *
     * @param branch
     *         the branch to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityExistsException
     *         if a branch with the given name of the same area already exists.
     * @throws EntityNotFoundException
     *         if the branch cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Branch branch) throws CoreServiceException{
        ServiceHelper.checkNotNull(branch, "branch");
        try {
            Long duplicateId = mapper.checkByNameAndAreaId(branch.getName(), branch.getGroupId());
            if (duplicateId != null && duplicateId != branch.getId()) {
                throw new EntityExistsException(
                        "The branch of the area with the same name already exists.");
            }
            if (mapper.update(branch) == 0) {
                throw new EntityNotFoundException("The branch cannot be found.");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the branch by id.
     *
     * @param id
     *         the branch id.
     *
     * @return the retrieved branch, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public Branch findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches branches.
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
    public SearchResult<Branch> search(BranchSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<Branch> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets all branch cities.
     *
     * @param hqId
     *         the headquarter id.
     *
     * @return the retrieved list of cities.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public List<String> getBranchCities(long hqId) throws CoreServiceException{
        try {
            return mapper.getBranchCities(hqId);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the device counter for the new POS of the given branch.
     *
     * @param branchId
     *         the branch id.
     *
     * @return the device counter.
     *
     * @throws EntityNotFoundException
     *         if the branch cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public int getPosCounter(long branchId) throws CoreServiceException{
        try {
            int result = mapper.getPosCounter(branchId);
            if (result == 0) {
                throw new EntityNotFoundException("The branch cannot be found.");
            }
            return result;
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
