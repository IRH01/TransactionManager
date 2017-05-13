package com.irh.transaction.services.impl;

import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.services.ProductDiscountService;
import com.irh.transaction.dao.ProductDiscountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link ProductDiscount}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class ProductDiscountServiceImpl implements ProductDiscountService{

    /**
     * The mapper to manage {@link ProductDiscount} in persistence.
     */
    @Autowired
    private ProductDiscountMapper mapper;

    /**
     * Saves the product discount.
     *
     * @param productDiscount
     *         the product discount to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional
    @Override
    public void save(ProductDiscount productDiscount) throws CoreServiceException{
        ServiceHelper.checkNotNull(productDiscount, "productDiscount");
        try {
            mapper.save(productDiscount);
            if (productDiscount.getProducts() != null) {
                for (Product product : productDiscount.getProducts()) {
                    mapper.saveDiscountProduct(productDiscount.getId(), product.getId());
                }
            }
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the product discount.
     *
     * @param productDiscount
     *         the product discount to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the product discount cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional
    @Override
    public void update(ProductDiscount productDiscount) throws CoreServiceException{
        ServiceHelper.checkNotNull(productDiscount, "productDiscount");
        try {
            if (mapper.update(productDiscount) == 0) {
                throw new EntityNotFoundException("The product discount cannot be found.");
            }
            mapper.deleteDiscountProducts(productDiscount.getId());
            if (productDiscount.getProducts() != null) {
                for (Product product : productDiscount.getProducts()) {
                    mapper.saveDiscountProduct(productDiscount.getId(), product.getId());
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the product discount by id.
     *
     * @param id
     *         the product discount id.
     *
     * @return the retrieved product discount, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public ProductDiscount findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches product discounts.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *         or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<ProductDiscount> search(NamedEntitySearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<ProductDiscount> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds all available product discounts of the given headquarter.
     *
     * @param hqId
     *         the headquarter id.
     *
     * @return the list of available product discounts of the headquarter.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public List<ProductDiscount> findAllAvailable(long hqId) throws CoreServiceException{
        try {
            return mapper.findAllAvailable(hqId);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
