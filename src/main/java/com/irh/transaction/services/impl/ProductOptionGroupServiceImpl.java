package com.irh.transaction.services.impl;

import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.services.ProductOptionGroupService;
import com.irh.transaction.dao.ProductOptionGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link ProductOptionGroupService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class ProductOptionGroupServiceImpl implements ProductOptionGroupService{

    /**
     * The mapper to manage {@link ProductOptionGroup} in persistence.
     */
    @Autowired
    private ProductOptionGroupMapper mapper;

    /**
     * Saves the product option group.
     *
     * @param group
     *         the product option group to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(ProductOptionGroup group) throws CoreServiceException{
        ServiceHelper.checkNotNull(group, "group");
        try {
            mapper.save(group);
            if (group.getOptions() != null) {
                for (ProductOption option : group.getOptions()) {
                    mapper.saveOption(group.getId(), option);
                }
            }
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the product option group.
     *
     * @param group
     *         the product option group to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the product option group or any of its options cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(ProductOptionGroup group) throws CoreServiceException{
        ServiceHelper.checkNotNull(group, "group");
        try {
            if (mapper.update(group) == 0) {
                throw new EntityNotFoundException("The product option group cannot be found.");
            }
            if (group.getOptions() != null) {
                for (ProductOption option : group.getOptions()) {
                    if (option.getId() == 0) {
                        mapper.saveOption(group.getId(), option);
                    } else if (mapper.updateOption(option) == 0) {
                        throw new EntityNotFoundException("The product option cannot be found.");
                    }
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the product option group by id.
     *
     * @param id
     *         the product option group id.
     *
     * @return the retrieved product option group, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public ProductOptionGroup findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches product option groups.
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
    public SearchResult<ProductOptionGroup> search(NamedEntitySearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<ProductOptionGroup> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
