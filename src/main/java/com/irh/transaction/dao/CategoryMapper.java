package com.irh.transaction.dao;

import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.model.product.CategoryProduct;
import com.irh.transaction.dto.search.CategorySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Category} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface CategoryMapper{
    /**
     * Saves the category.
     *
     * @param category the category to save.
     */
    @Transactional
    void save(Category category);

    /**
     * Updates the category.
     *
     * @param category the category to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(Category category);

    /**
     * Updates the display order of the given category.
     *
     * @param id           the category id.
     * @param displayOrder the display order.
     * @return the number of affected rows.
     */
    @Transactional
    int updateDisplayOrder(@Param("id") long id, @Param("displayOrder") int displayOrder);

    /**
     * Deletes the associations of the category and its products.
     *
     * @param id the category id.
     */
    @Transactional
    void deleteCategoryProducts(long id);

    /**
     * Saves the association of the category and product.
     *
     * @param id              the category id.
     * @param categoryProduct the association to product.
     */
    @Transactional
    void saveCategoryProduct(@Param("id") long id,
                             @Param("categoryProduct") CategoryProduct categoryProduct);

    /**
     * Finds the category by id.
     *
     * @param id the category id.
     * @return the retrieved category, null if not found.
     */
    Category findOne(long id);

    /**
     * Searches categories.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Category> search(@Param("filter") CategorySearchFilter filter);

    /**
     * Counts the number of categories that match the filter.
     *
     * @param filter the filter.
     * @return the number of categories that match the filter.
     */
    long count(@Param("filter") CategorySearchFilter filter);

    /**
     * Finds the menu containing categories and their corresponding products of the given headquarter, branch and
     * platform.
     *
     * @param hqId     the headquarter id.
     * @param branchId the branch id, ignored if null.
     * @param platform the platform.
     * @return the list of retrieved categories.
     */
    List<Category> findMenu(@Param("hqId") long hqId, @Param("branchId") Long branchId,
                            @Param("platform") Platform platform);

    /**
     * @param hqId
     * @param branchId
     * @param hqCode
     * @return
     */
    List<Category> findMenuForBranch(@Param("hqId") Integer hqId, @Param("branchId") Integer branchId,
                                     @Param("hqCode") String hqCode);
}
