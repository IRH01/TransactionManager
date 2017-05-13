package com.irh.transaction.model.order;

import com.irh.transaction.model.common.HeadquarterEntity;

/**
 * Represents a queue token for order.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class OrderQueueToken extends HeadquarterEntity{

    /**
     * The branch id.
     */
    private long branchId;

    /**
     * The code.
     */
    private String code;

    /**
     * The value indicates if the token is available.
     */
    private boolean available;

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
     * Gets the value indicates if the token is available.
     *
     * @return the value indicates if the token is available.
     */
    public boolean isAvailable(){
        return available;
    }

    /**
     * Sets the value indicates if the token is available.
     *
     * @param available the value to set.
     */
    public void setAvailable(boolean available){
        this.available = available;
    }
}
