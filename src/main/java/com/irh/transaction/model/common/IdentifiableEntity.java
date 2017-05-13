package com.irh.transaction.model.common;

import java.io.Serializable;

/**
 * The base class for entity with an identifiable number (Id).
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> The class has been renamed to make it more official. </li> <li> The type
 * of the {@link com.irh.transaction.model.common.IdentifiableEntity#id} has been changed to long to increase its available maximum value and to avoid
 * boxing. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public abstract class IdentifiableEntity implements Serializable{

    /**
     * The entity id.
     */
    private long id;

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public long getId(){
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set.
     */
    public void setId(long id){
        this.id = id;
    }
}
