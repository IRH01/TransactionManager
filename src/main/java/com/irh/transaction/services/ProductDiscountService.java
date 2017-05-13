package com.irh.transaction.services;

import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;

import java.util.List;

/**
 * Defines a contract for managing {@link ProductDiscount}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface ProductDiscountService{

    /**
     * Saves the product discount.
     *
     * @param productDiscount the product discount to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(ProductDiscount productDiscount) throws CoreServiceException;

    /**
     * Updates the product discount.
     *
     * @param productDiscount the product discount to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityNotFoundException  if the product discount cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(ProductDiscount productDiscount) throws CoreServiceException;

    /**
     * Finds the product discount by id.
     *
     * @param id the product discount id.
     * @return the retrieved product discount, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    ProductDiscount findOne(long id) throws CoreServiceException;

    /**
     * Searches product discounts.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<ProductDiscount> search(NamedEntitySearchFilter filter) throws CoreServiceException;

    /**
     * Finds all available product discounts of the given headquarter.
     *
     * @param hqId the headquarter id.
     * @return the list of available product discounts of the headquarter.
     * @throws CoreServiceException if any error occurs.
     */
    List<ProductDiscount> findAllAvailable(long hqId) throws CoreServiceException;
}
