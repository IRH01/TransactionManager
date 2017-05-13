package com.irh.transaction.services.impl;

import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.model.product.CategoryProduct;
import com.irh.transaction.dto.search.CategorySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CategoryService;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link CategoryService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    /**
     * The mapper to manage {@link Category} in persistence.
     */
    @Autowired
    private CategoryMapper mapper;

    /**
     * Saves the category.
     *
     * @param category
     *         the category to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Category category) throws CoreServiceException{
        ServiceHelper.checkNotNull(category, "category");
        try {
            mapper.save(category);
            if (category.getProducts() != null) {
                for (CategoryProduct categoryProduct : category.getProducts()) {
                    mapper.saveCategoryProduct(category.getId(), categoryProduct);
                }
            }
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the category.
     *
     * @param category
     *         the category to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the category cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Category category) throws CoreServiceException{
        ServiceHelper.checkNotNull(category, "category");
        try {
            if (mapper.update(category) == 0) {
                throw new EntityNotFoundException("The category cannot be found.");
            }
            mapper.deleteCategoryProducts(category.getId());
            if (category.getProducts() != null) {
                for (CategoryProduct categoryProduct : category.getProducts()) {
                    mapper.saveCategoryProduct(category.getId(), categoryProduct);
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the display orders of the categories.
     *
     * @param displayOrders
     *         the mapping between ids of the categories and their corresponding display orders.
     *
     * @throws IllegalArgumentException
     *         if the argument is null or empty.
     * @throws EntityNotFoundException
     *         if any category cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Map<Long, Integer> displayOrders) throws CoreServiceException{
        ServiceHelper.checkNotNull(displayOrders, "displayOrders");
        if (displayOrders.size() == 0) {
            throw new IllegalArgumentException("The displayOrders cannot be empty.");
        }
        try {
            for (Long categoryId : displayOrders.keySet()) {
                if (mapper.updateDisplayOrder(categoryId, displayOrders.get(categoryId)) == 0) {
                    throw new EntityNotFoundException(
                            "The category of id " + categoryId + " cannot be found.");
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the category by id.
     *
     * @param id
     *         the category id.
     *
     * @return the retrieved category, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public Category findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches categories.
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
    public SearchResult<Category> search(CategorySearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<Category> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the menu containing categories and their corresponding products of the given headquarter, branch and
     * platform.
     *
     * @param hqId
     *         the headquarter id.
     * @param branchId
     *         the branch id, ignored if null.
     * @param platform
     *         the platform.
     *
     * @return the list of retrieved categories.
     *
     * @throws IllegalArgumentException
     *         if the platform is null.
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public List<Category> findMenu(long hqId, Long branchId, Platform platform) throws CoreServiceException{
        ServiceHelper.checkNotNull(platform, "platform");
        try {
            return mapper.findMenu(hqId, branchId, platform);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     *
     * @param hqId
     * @param branchId
     * @param hqCode
     * @return
     * @throws CoreServiceException
     */
    @Override
    public List<Category> findMenuForBranch(Integer hqId, Integer branchId, String hqCode) throws CoreServiceException{
        try {
            return mapper.findMenuForBranch(hqId, branchId, hqCode);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
