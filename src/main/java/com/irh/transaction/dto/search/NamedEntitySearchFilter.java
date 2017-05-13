package com.irh.transaction.dto.search;


/**
 * The filter for searching entities with name.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class NamedEntitySearchFilter extends SearchFilter{

    /**
     * The name to filter.
     */
    private String name;

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
}
