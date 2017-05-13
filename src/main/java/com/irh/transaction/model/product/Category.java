package com.irh.transaction.model.product;

import com.irh.transaction.model.common.HeadquarterEntity;
import com.irh.transaction.model.common.Platform;

import java.util.List;

/**
 * Represents a product category.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> The clientId field has been replaced with the {@link com.irh.transaction.model.product.Category#platform}
 * </li> <li> Added the {@link com.irh.transaction.model.product.Category#enabled} field. </li> <li> The type of the {@link com.irh.transaction.model.product.Category#products} field has
 * been changed to {@link CategoryProduct} to support display order. </li> </ol>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public class Category extends HeadquarterEntity{
    /**
     * The platform.
     *
     * @since 1.1
     */
    private Platform platform;

    /**
     * The name.
     */
    private String name;

    /**
     * The internationalized name.
     */
    private String interName;

    /**
     * The list of associations of products belong the to category.
     */
    private List<CategoryProduct> products;

    /**
     * The display order.
     */
    private int displayOrder;

    /**
     * The value indicates if the category is enabled.
     *
     * @since 1.1
     */
    private boolean enabled;

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
     * Gets the internationalized name.
     *
     * @return the internationalized name.
     */
    public String getInterName(){
        return interName;
    }

    /**
     * Sets the internationalized name.
     *
     * @param interName the internationalized name to set.
     */
    public void setInterName(String interName){
        this.interName = interName;
    }

    /**
     * Gets the platform.
     *
     * @return the platform.
     * @since 1.1
     */
    public Platform getPlatform(){
        return platform;
    }

    /**
     * Sets the platform.
     *
     * @param platform the platform to set.
     * @since 1.1
     */
    public void setPlatform(Platform platform){
        this.platform = platform;
    }

    /**
     * Gets the products.
     *
     * @return the products.
     */
    public List<CategoryProduct> getProducts(){
        return products;
    }

    /**
     * Sets the products.
     *
     * @param products the products to set.
     */
    public void setProducts(List<CategoryProduct> products){
        this.products = products;
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

    /**
     * Gets the enabled.
     *
     * @return the enabled.
     * @since 1.1
     */
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     * @since 1.1
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
