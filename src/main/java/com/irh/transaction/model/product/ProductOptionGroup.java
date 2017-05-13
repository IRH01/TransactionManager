package com.irh.transaction.model.product;

import com.irh.transaction.model.common.HeadquarterEntity;

import java.util.List;

/**
 * Represents an option group for product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductOptionGroup extends HeadquarterEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The list of available options.
     */
    private List<ProductOption> options;

    /**
     * The value indicates if the product option group is enabled.
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
     * Gets the options.
     *
     * @return the options.
     */
    public List<ProductOption> getOptions(){
        return options;
    }

    /**
     * Sets the options.
     *
     * @param options the options to set.
     */
    public void setOptions(List<ProductOption> options){
        this.options = options;
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
