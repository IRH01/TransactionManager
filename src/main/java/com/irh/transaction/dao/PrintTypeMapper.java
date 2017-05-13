package com.irh.transaction.dao;

import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link PrintType} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface PrintTypeMapper{

    /**
     * /** Saves the print type.
     *
     * @param printType the print type to save.
     */
    @Transactional
    void save(PrintType printType);

    /**
     * /** Updates the print type.
     *
     * @param printType the print type to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(PrintType printType);

    /**
     * Saves the association of the print type and product.
     *
     * @param id        the print type id.
     * @param productId the product id.
     */
    @Transactional
    void savePrintTypeProduct(@Param("id") long id, @Param("productId") long productId);

    /**
     * Deletes the associations of the print type and its products.
     *
     * @param id the print type id.
     */
    @Transactional
    void deletePrintTypeProducts(long id);

    /**
     * Finds the print type by id.
     *
     * @param id the print type id.
     * @return the retrieved print type, null if not found.
     */
    PrintType findOne(long id);

    /**
     * Searches print types.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<PrintType> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of print types that match the filter.
     *
     * @param filter the filter.
     * @return the number of print types that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);
}
