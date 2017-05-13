package com.irh.transaction.services;

import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link ProductOptionGroup}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface ProductOptionGroupService{

    /**
     * Saves the product option group.
     *
     * @param group the product option group to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(ProductOptionGroup group) throws CoreServiceException;

    /**
     * Updates the product option group.
     *
     * @param group the product option group to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityNotFoundException  if the product option group or any of its options cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(ProductOptionGroup group) throws CoreServiceException;

    /**
     * Finds the product option group by id.
     *
     * @param id the product option group id.
     * @return the retrieved product option group, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    ProductOptionGroup findOne(long id) throws CoreServiceException;

    /**
     * Searches product option groups.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<ProductOptionGroup> search(NamedEntitySearchFilter filter)
            throws CoreServiceException;
}
