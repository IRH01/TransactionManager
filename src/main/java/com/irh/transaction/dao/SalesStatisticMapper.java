package com.irh.transaction.dao;

import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.model.statistic.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Represents the mapper that computes statistical sales data in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface SalesStatisticMapper{

    /**
     * Gets the computation result of the statistical data for sales of branch.
     *
     * @param filter the computation filter.
     * @return the computation result.
     */
    List<BranchSalesSummary> getBranchSalesSummaries(@Param("filter") DateFilter filter);

    /**
     * /** Gets the computation result of the statistical details for total sales data.
     *
     * @param filter the computation filter.
     * @return the computation result.
     */
    TotalSalesDetails getTotalSalesDetails(@Param("filter") DateFilter filter);

    /**
     * Gets the computation result of the statistical data summary for product sales.
     *
     * @param filter     the computation filter.
     * @param categoryId the optional category id to filter the product.
     * @return the computation result.
     */
    List<ProductSalesSummary> getProductSalesSummaries(@Param("filter") DateFilter filter,
                                                       @Param("categoryId") Long categoryId);

    /**
     * /** Gets the computation result of the statistical details for sales data of the product.
     *
     * @param filter    the computation filter.
     * @param productId the product id.
     * @return the computation result.
     */
    ProductSalesDetails getProductSalesDetails(@Param("filter") DateFilter filter,
                                               @Param("productId") long productId);

    /**
     * Gets the computation result of the statistical data for sales of category.
     *
     * @param filter the computation filter.
     * @return the computation result.
     */
    List<CategorySalesSummary> getCategorySalesSummaries(@Param("filter") DateFilter filter);

    /**
     * Gets the computation result of the statistical details for sales data of a given category.
     *
     * @param filter     the computation filter.
     * @param categoryId the category id.
     * @return the computation result.
     */
    CategorySalesDetails getCategorySalesDetails(@Param("filter") DateFilter filter,
                                                 @Param("categoryId") long categoryId);

    /**
     * Gets the computation result of the statistical data for sales of product discount.
     *
     * @param filter the computation filter.
     * @return the computation result.
     */
    List<ProductDiscountSalesSummary> getDiscountSalesSummaries(
            @Param("filter") DateFilter filter);
}
