package com.irh.transaction.dto.search;

import com.irh.transaction.model.product.ProductStatus;

/**
 * The filter for product searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductSearchFilter extends NamedEntitySearchFilter{

    /**
     * The status to filter.
     */
    private ProductStatus status;

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public ProductStatus getStatus(){
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set.
     */
    public void setStatus(ProductStatus status){
        this.status = status;
    }
}
