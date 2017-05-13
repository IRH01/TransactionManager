package com.irh.transaction.dao;

import com.irh.transaction.model.product.Product;
import com.irh.transaction.dto.search.ProductSearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Product} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>The methods to manage product and category association have been removed. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface ProductMapper{

    /**
     * Saves the product.
     *
     * @param product the product to save.
     */
    @Transactional
    void save(Product product);

    /**
     * Updates the product.
     *
     * @param product the product to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(Product product);

    /**
     * Saves the association of the product and option group.
     *
     * @param productId the product id.
     * @param groupId   the option group id.
     */
    @Transactional
    void saveProductOptionGroup(@Param("productId") long productId,
                                @Param("groupId") long groupId);

    /**
     * Deletes the associations of the product and its option groups.
     *
     * @param id the product id.
     */
    @Transactional
    void deleteProductOptionGroups(long id);

    /**
     * Finds the product by id.
     *
     * @param id the product id.
     * @return the retrieved product, null if not found.
     */
    Product findOne(long id);

    /**
     * Searches products.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Product> search(@Param("filter") ProductSearchFilter filter);

    /**
     * Counts the number of products that match the filter.
     *
     * @param filter the filter.
     * @return the number of products that match the filter.
     */
    long count(@Param("filter") ProductSearchFilter filter);
}
