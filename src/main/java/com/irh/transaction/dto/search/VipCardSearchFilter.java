package com.irh.transaction.dto.search;

import com.irh.transaction.model.marketing.VipCardStatus;

/**
 * The filter for VIP card searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class VipCardSearchFilter extends SearchFilter{

    /**
     * The information represented by card number/mobile/name to filter.
     */
    private String info;

    /**
     * The status to filter.
     */
    private VipCardStatus status;

    /**
     * Gets the info.
     *
     * @return the info.
     */
    public String getInfo(){
        return info;
    }

    /**
     * Sets the info.
     *
     * @param info the info to set.
     */
    public void setInfo(String info){
        this.info = info;
    }

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public VipCardStatus getStatus(){
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set.
     */
    public void setStatus(VipCardStatus status){
        this.status = status;
    }
}
