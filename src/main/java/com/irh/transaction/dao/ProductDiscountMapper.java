package com.irh.transaction.dao;

import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link ProductDiscount} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface ProductDiscountMapper{

    /**
     * Saves the product discount.
     *
     * @param productDiscount the product discount to save.
     */
    @Transactional
    void save(ProductDiscount productDiscount);

    /**
     * Updates the product discount.
     *
     * @param productDiscount the product discount to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(ProductDiscount productDiscount);

    /**
     * Saves the association of the product discount and the product.
     *
     * @param id        the product discount id.
     * @param productId the product id.
     */
    @Transactional
    void saveDiscountProduct(@Param("id") long id, @Param("productId") long productId);

    /**
     * Deletes the associations of the product discount and its products.
     *
     * @param id the product discount id.
     */
    @Transactional
    void deleteDiscountProducts(long id);

    /**
     * Finds the product discount by id.
     *
     * @param id the product discount id.
     * @return the retrieved product discount, null if not found.
     */
    ProductDiscount findOne(long id);

    /**
     * Searches product discounts.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<ProductDiscount> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of product discounts that match the filter.
     *
     * @param filter the filter.
     * @return the number of product discounts that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Finds all available product discounts of the given headquarter.
     *
     * @param hqId the headquarter id.
     * @return the list of available product discounts of the headquarter.
     */
    List<ProductDiscount> findAllAvailable(long hqId);
}
