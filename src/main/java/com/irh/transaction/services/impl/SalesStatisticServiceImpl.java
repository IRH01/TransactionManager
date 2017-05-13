package com.irh.transaction.services.impl;

import com.irh.transaction.dao.SalesStatisticMapper;
import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.model.statistic.*;
import com.irh.transaction.services.CorePersistenceException;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.SalesStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of the {@link SalesStatisticService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class SalesStatisticServiceImpl implements SalesStatisticService{

    /**
     * The mapper to run computation for statistical sales data in persistence.
     */
    @Autowired
    private SalesStatisticMapper mapper;

    /**
     * Gets the computation result of the statistical data for sales of branch.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public List<BranchSalesSummary> getBranchSalesSummaries(DateFilter filter)
            throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getBranchSalesSummaries(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical data for sales of product.
     *
     * @param filter     the computation filter.
     * @param categoryId the category id to filter. If null, all products will be counted.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public List<ProductSalesSummary> getProductSalesSummaries(DateFilter filter, Long categoryId)
            throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getProductSalesSummaries(filter, categoryId);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical details for sales data of a given product.
     *
     * @param filter    the computation filter.
     * @param productId the product id.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public ProductSalesDetails getProductSalesDetails(DateFilter filter, long productId)
            throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getProductSalesDetails(filter, productId);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical details for total sales data.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public TotalSalesDetails getTotalSalesDetails(DateFilter filter)
            throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getTotalSalesDetails(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical data for sales of category.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public List<CategorySalesSummary> getCategorySalesSummaries(DateFilter filter) throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getCategorySalesSummaries(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical details for sales data of a given category.
     *
     * @param filter     the computation filter.
     * @param categoryId the category id.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public CategorySalesDetails getCategorySalesDetails(DateFilter filter, long categoryId) throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getCategorySalesDetails(filter, categoryId);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the computation result of the statistical data for sales of product discount.
     *
     * @param filter the computation filter.
     * @return the computation result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null;
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public List<ProductDiscountSalesSummary> getDiscountSalesSummaries(DateFilter filter)
            throws CoreServiceException{
        checkFilter(filter);
        try{
            return mapper.getDiscountSalesSummaries(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    private static void checkFilter(DateFilter filter){
        ServiceHelper.checkNotNull(filter, "filter");
        ServiceHelper.checkNotNull(filter.getHqId(), "filter.hqId");
    }
}
