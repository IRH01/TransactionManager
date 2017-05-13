package com.irh.transaction.model.account;

import com.irh.transaction.model.common.IdentifiableEntity;

/**
 * Represents a permission.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class Permission extends IdentifiableEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The code.
     */
    private String code;

    /**
     * The category.
     */
    private String category;

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
     * Gets the code.
     *
     * @return the code.
     */
    public String getCode(){
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set.
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * Gets the category.
     *
     * @return the category.
     */
    public String getCategory(){
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category to set.
     */
    public void setCategory(String category){
        this.category = category;
    }
}
