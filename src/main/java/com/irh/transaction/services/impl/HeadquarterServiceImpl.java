package com.irh.transaction.services.impl;

import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.*;
import com.irh.transaction.dao.HeadquarterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link HeadquarterService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class HeadquarterServiceImpl implements HeadquarterService{

    /**
     * The mapper to manage {@link Headquarter} in persistence.
     */
    @Autowired
    private HeadquarterMapper mapper;

    /**
     * Saves the headquarter.
     *
     * @param headquarter
     *         the headquarter to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityExistsException
     *         if the headquarter with the same code already exists.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Headquarter headquarter) throws CoreServiceException{
        ServiceHelper.checkNotNull(headquarter, "headquarter");
        try {
            if (mapper.checkByCode(headquarter.getCode()) != null) {
                throw new EntityExistsException("A headquarter with the same code already exists.");
            }
            mapper.save(headquarter);
        } catch (EntityExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the headquarter.
     *
     * @param headquarter
     *         the headquarter to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the headquarter cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Headquarter headquarter) throws CoreServiceException{
        ServiceHelper.checkNotNull(headquarter, "headquarter");
        try {
            if (mapper.update(headquarter) == 0) {
                throw new EntityNotFoundException("The headquarter cannot be found.");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches headquarters.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the <em>filter.page</em> is not positive or <em>filter.size</em> is not positive; if the
     *         <em>filter.page</em> is provided and the <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public SearchResult<Headquarter> search(NamedEntitySearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, false);
        try {
            List<Headquarter> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
