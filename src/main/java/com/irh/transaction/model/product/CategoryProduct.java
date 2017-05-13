package com.irh.transaction.model.product;

/**
 * Represents an association between category and product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class CategoryProduct{

    /**
     * The product.
     */
    private Product product;

    /**
     * The display order.
     */
    private int displayOrder;

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
     * Gets the displayOrder.
     *
     * @return the displayOrder.
     */
    public int getDisplayOrder(){
        return displayOrder;
    }

    /**
     * Sets the displayOrder.
     *
     * @param displayOrder the displayOrder to set.
     */
    public void setDisplayOrder(int displayOrder){
        this.displayOrder = displayOrder;
    }
}
