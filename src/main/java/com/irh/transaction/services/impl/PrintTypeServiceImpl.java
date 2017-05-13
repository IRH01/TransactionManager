package com.irh.transaction.services.impl;

import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.services.CorePersistenceException;

import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.services.PrintTypeService;
import com.irh.transaction.dao.PrintTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link PrintTypeService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
@Service
public class PrintTypeServiceImpl implements PrintTypeService{
    /**
     * The mapper to manage {@link PrintType} in persistence.
     */
    @Autowired
    private PrintTypeMapper mapper;

    /**
     * Saves the print type.
     *
     * @param printType
     *         the print type to save.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(PrintType printType) throws CoreServiceException{
        ServiceHelper.checkNotNull(printType, "printType");
        try {
            mapper.save(printType);
            if (printType.getProducts() != null) {
                for (Product product : printType.getProducts()) {
                    mapper.savePrintTypeProduct(printType.getId(), product.getId());
                }
            }
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the print type.
     *
     * @param printType
     *         the print type to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the print type cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(PrintType printType) throws CoreServiceException{
        ServiceHelper.checkNotNull(printType, "printType");
        try {
            if (mapper.update(printType) == 0) {
                throw new EntityNotFoundException("The print type cannot be found.");
            }
            mapper.deletePrintTypeProducts(printType.getId());
            if (printType.getProducts() != null) {
                for (Product product : printType.getProducts()) {
                    mapper.savePrintTypeProduct(printType.getId(), product.getId());
                }
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the print type by id.
     *
     * @param id
     *         the print type id.
     *
     * @return the retrieved print type, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public PrintType findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches print types.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.branchId</em> is null; if the <em>filter.page</em> is not
     *         positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<PrintType> search(NamedEntitySearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkNotNull(filter, "filter");
        ServiceHelper.checkNotNull(filter.getBranchId(), "filter.branchId");
        ServiceHelper.checkSearchFilter(filter, false);
        try {
            List<PrintType> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
