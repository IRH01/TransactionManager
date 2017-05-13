package com.irh.transaction.model.product;

import com.irh.transaction.model.common.IdentifiableEntity;

import java.math.BigDecimal;

/**
 * Represents a product option.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductOption extends IdentifiableEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The internationalized name.
     */
    private String interName;

    /**
     * The price.
     */
    private BigDecimal price;

    /**
     * The value indicates if the option is enabled.
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
     * Gets the interName.
     *
     * @return the interName.
     */
    public String getInterName(){
        return interName;
    }

    /**
     * Sets the interName.
     *
     * @param interName the interName to set.
     */
    public void setInterName(String interName){
        this.interName = interName;
    }

    /**
     * Gets the price.
     *
     * @return the price.
     */
    public BigDecimal getPrice(){
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set.
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    /**
     * Gets the enabled.
     *
     * @return the enabled.
     */
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
