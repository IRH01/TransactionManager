package com.irh.transaction.services.impl;

import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.dto.search.ProductSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.services.ProductService;
import com.irh.transaction.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link ProductService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class ProductServiceImpl implements ProductService{

    /**
     * The mapper to manage {@link Product} in persistence.
     */
    @Autowired
    private ProductMapper mapper;

    /**
     * Saves the product.
     *
     * @param product
     *         the product to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Product product) throws CoreServiceException{
        ServiceHelper.checkNotNull(product, "product");
        try {
            mapper.save(product);
            if (product.getOptionGroups() != null) {
                for (ProductOptionGroup optionGroup : product.getOptionGroups()) {
                    mapper.saveProductOptionGroup(product.getId(), optionGroup.getId());
                }
            }
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the product.
     *
     * @param product
     *         the product to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the product cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Product product) throws CoreServiceException{
        ServiceHelper.checkNotNull(product, "product");
        try {
            if (mapper.update(product) == 0) {
                throw new EntityNotFoundException("The product cannot be found.");
            }
            mapper.deleteProductOptionGroups(product.getId());
            if (product.getOptionGroups() != null) {
                for (ProductOptionGroup optionGroup : product.getOptionGroups()) {
                    mapper.saveProductOptionGroup(product.getId(), optionGroup.getId());
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the product by id.
     *
     * @param id
     *         the product id.
     *
     * @return the retrieved product, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public Product findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches products.
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
    public SearchResult<Product> search(ProductSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<Product> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
