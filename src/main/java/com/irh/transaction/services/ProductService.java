package com.irh.transaction.services;

import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.dto.search.ProductSearchFilter;
import com.irh.transaction.dto.search.SearchResult;

/**
 * Defines a contract for managing {@link Product}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p><br>The methods to manage {@link PrintType} and {@link Category} are move the
 * corresponding services respectively.
 *
 * @author  Iritchie.ren
 * @version 1.1
 * @see CategoryService
 * @see PrintTypeService
 */
public interface ProductService{

    /**
     * Saves the product.
     *
     * @param product the product to save.
     * @throws IllegalArgumentException if the argument is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Product product) throws CoreServiceException;

    /**
     * Updates the product.
     *
     * @param product the product to update.
     * @throws IllegalArgumentException if the argument is null.
     * @throws EntityNotFoundException  if the product cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Product product) throws CoreServiceException;

    /**
     * Finds the product by id.
     *
     * @param id the product id.
     * @return the retrieved product, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    Product findOne(long id) throws CoreServiceException;

    /**
     * Searches products.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Product> search(ProductSearchFilter filter) throws CoreServiceException;
}
