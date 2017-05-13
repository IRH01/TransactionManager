package com.irh.transaction.model.common;

/**
 * The base class for entity belongs to a headquarter.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public abstract class HeadquarterEntity extends IdentifiableEntity{

    /**
     * The headquarter id.
     */
    private long hqId;

    /**
     * Gets the hqId.
     *
     * @return the hqId.
     */
    public long getHqId(){
        return hqId;
    }

    /**
     * Sets the hqId.
     *
     * @param hqId the hqId to set.
     */
    public void setHqId(long hqId){
        this.hqId = hqId;
    }
}
