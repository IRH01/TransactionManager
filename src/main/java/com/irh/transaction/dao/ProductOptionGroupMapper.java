package com.irh.transaction.dao;

import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link ProductOptionGroup} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface ProductOptionGroupMapper{

    /**
     * Saves the product option group.
     *
     * @param group the product option group to save.
     */
    @Transactional
    void save(ProductOptionGroup group);

    /**
     * Updates the product option group.
     *
     * @param group the product option group to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(ProductOptionGroup group);

    /**
     * Saves the product option of the option group.
     *
     * @param groupId the option group id.
     * @param option  the product option to save.
     */
    @Transactional
    void saveOption(@Param("groupId") long groupId, @Param("option") ProductOption option);

    /**
     * Updates the product option.
     *
     * @param option the product option to update.
     * @return the number of affected rows.
     */
    @Transactional
    int updateOption(ProductOption option);

    /**
     * Finds the product option group by id.
     *
     * @param id the product option group id.
     * @return the retrieved product option group, null if not found.
     */
    ProductOptionGroup findOne(long id);

    /**
     * Searches product option groups.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<ProductOptionGroup> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of product option groups that match the filter.
     *
     * @param filter the filter.
     * @return the number of product option groups that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);
}
