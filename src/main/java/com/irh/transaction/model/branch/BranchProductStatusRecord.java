package com.irh.transaction.model.branch;

import com.irh.transaction.model.common.IdentifiableEntity;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductStatus;

import java.util.Date;

/**
 * Represents a product status record in a branch.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchProductStatusRecord extends IdentifiableEntity{

    /**
     * The product.
     */
    private Product product;

    /**
     * The branch.
     */
    private Branch branch;

    /**
     * The datetime when the record was created.
     */
    private Date createdAt;

    /**
     * The status.
     */
    private ProductStatus status;

    /**
     * Gets the product.
     *
     * @return the product.
     */
    public Product getProduct(){
        return product;
    }

    /**
     * Sets the product.
     *
     * @param product the product to set.
     */
    public void setProduct(Product product){
        this.product = product;
    }

    /**
     * Gets the branch.
     *
     * @return the branch.
     */
    public Branch getBranch(){
        return branch;
    }

    /**
     * Sets the branch.
     *
     * @param branch the branch to set.
     */
    public void setBranch(Branch branch){
        this.branch = branch;
    }

    /**
     * Gets the createdAt.
     *
     * @return the createdAt.
     */
    public Date getCreatedAt(){
        return createdAt;
    }

    /**
     * Sets the createdAt.
     *
     * @param createdAt the createdAt to set.
     */
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

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
