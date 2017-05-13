package com.irh.transaction.services;

import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.dto.search.CategorySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

import java.util.List;
import java.util.Map;

/**
 * Defines a contract for managing {@link Category}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface CategoryService{

    /**
     * Saves the category.
     *
     * @param category the category to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Category category) throws CoreServiceException;

    /**
     * Updates the category.
     *
     * @param category the category to update.
     * @throws IllegalArgumentException                          if the argument is null.
     * @throws com.irh.transcation.services.EntityNotFoundException if the category cannot be found.
     * @throws CoreServiceException                              if any other error occurs.
     */
    void update(Category category) throws CoreServiceException;

    /**
     * Updates the display orders of the categories.
     *
     * @param displayOrders the mapping between ids of the categories and their corresponding display orders.
     * @throws IllegalArgumentException if the argument is null or empty.
     * @throws EntityNotFoundException  if any category cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Map<Long, Integer> displayOrders) throws CoreServiceException;

    /**
     * Finds the category by id.
     *
     * @param id the category id.
     * @return the retrieved category, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    Category findOne(long id) throws CoreServiceException;

    /**
     * Searches categories.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Category> search(CategorySearchFilter filter) throws CoreServiceException;

    /**
     * Finds the menu containing categories and their corresponding products of the given headquarter, branch and
     * platform.
     *
     * @param hqId     the headquarter id.
     * @param branchId the branch id, ignored if null.
     * @param platform the platform.
     * @return the list of retrieved categories.
     * @throws IllegalArgumentException if the platform is null.
     * @throws CoreServiceException     if any error occurs.
     */
    List<Category> findMenu(long hqId, Long branchId, Platform platform) throws CoreServiceException;

    /**
     * @param hqId
     * @param branchId
     * @param hqCode
     * @return
     * @throws CoreServiceException
     */
    List<Category> findMenuForBranch(Integer hqId, Integer branchId, String hqCode) throws CoreServiceException;
}
