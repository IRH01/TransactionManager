package com.irh.transaction.model.branch;

import com.irh.transaction.model.common.HeadquarterEntity;

/**
 * Represents a branch group.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> The supervisorName and supervisorPhone have been removed. </li> <li>
 * Added the {@link com.irh.transaction.model.branch.BranchGroup#enabled} field. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchGroup extends HeadquarterEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The value indicates if the area is enabled.
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
