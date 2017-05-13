package com.irh.transaction.services;

import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.model.statistic.*;

import java.util.List;

/**
 * Defines a contract for managing statistical data.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface SalesStatisticService{

    /**
     * Gets the computation result of the statistical data for sales of branch.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    List<BranchSalesSummary> getBranchSalesSummaries(DateFilter filter)
            throws CoreServiceException;

    /**
     * Gets the computation result of the statistical data for sales of product.
     *
     * @param filter     the computation filter.
     * @param categoryId the category id to filter. If null, all products will be counted.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    List<ProductSalesSummary> getProductSalesSummaries(DateFilter filter, Long categoryId)
            throws CoreServiceException;

    /**
     * Gets the computation result of the statistical details for sales data of a given product.
     *
     * @param filter    the computation filter.
     * @param productId the product id.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    ProductSalesDetails getProductSalesDetails(DateFilter filter, long productId)
            throws CoreServiceException;

    /**
     * Gets the computation result of the statistical details for total sales data.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    TotalSalesDetails getTotalSalesDetails(DateFilter filter) throws CoreServiceException;

    /**
     * Gets the computation result of the statistical data for sales of category.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    List<CategorySalesSummary> getCategorySalesSummaries(DateFilter filter)
            throws CoreServiceException;

    /**
     * Gets the computation result of the statistical details for sales data of a given category.
     *
     * @param filter     the computation filter.
     * @param categoryId the category id.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    CategorySalesDetails getCategorySalesDetails(DateFilter filter, long categoryId)
            throws CoreServiceException;

    /**
     * Gets the computation result of the statistical data for sales of product discount.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any error occurs.
     */
    List<ProductDiscountSalesSummary> getDiscountSalesSummaries(DateFilter filter)
            throws CoreServiceException;
}
