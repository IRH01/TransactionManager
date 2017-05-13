package com.irh.transaction.model.branch;

import com.irh.transaction.model.common.IdentifiableEntity;
import com.irh.transaction.model.product.Product;

import java.util.List;

/**
 * Represents a product print type.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public class PrintType extends IdentifiableEntity{

    /**
     * The branch id.
     */
    private long branchId;

    /**
     * The name.
     */
    private String name;

    /**
     * The list of products.
     */
    private List<Product> products;

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the branchId.
     *
     * @return the branchId.
     */
    public long getBranchId(){
        return branchId;
    }

    /**
     * Sets the branchId.
     *
     * @param branchId the branchId to set.
     */
    public void setBranchId(long branchId){
        this.branchId = branchId;
    }

    /**
     * Gets the products.
     *
     * @return the products.
     */
    public List<Product> getProducts(){
        return products;
    }

    /**
     * Sets the products.
     *
     * @param products the products to set.
     */
    public void setProducts(List<Product> products){
        this.products = products;
    }
}
