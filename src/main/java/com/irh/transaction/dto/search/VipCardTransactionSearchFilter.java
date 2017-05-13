package com.irh.transaction.dto.search;

import com.irh.transaction.model.marketing.VipCardTransactionType;

import java.util.Date;

/**
 * The filter for member transaction searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class VipCardTransactionSearchFilter extends SearchFilter{

    /**
     * The start of the created date to filter.
     */
    private Date createdAtFrom;

    /**
     * The end of the created date to filter.
     */
    private Date createdAtTo;

    /**
     * The member id to filter.
     */
    private long vipCardId;

    /**
     * The transaction type to filter.
     */
    private VipCardTransactionType transactionType;

    /**
     * Gets the createdAtFrom.
     *
     * @return the createdAtFrom.
     */
    public Date getCreatedAtFrom(){
        return createdAtFrom;
    }

    /**
     * Sets the createdAtFrom.
     *
     * @param createdAtFrom the createdAtFrom to set.
     */
    public void setCreatedAtFrom(Date createdAtFrom){
        this.createdAtFrom = createdAtFrom;
    }

    /**
     * Gets the createdAtTo.
     *
     * @return the createdAtTo.
     */
    public Date getCreatedAtTo(){
        return createdAtTo;
    }

    /**
     * Sets the createdAtTo.
     *
     * @param createdAtTo the createdAtTo to set.
     */
    public void setCreatedAtTo(Date createdAtTo){
        this.createdAtTo = createdAtTo;
    }

    /**
     * Gets the vipCardId.
     *
     * @return the vipCardId.
     */
    public long getVipCardId(){
        return vipCardId;
    }

    /**
     * Sets the vipCardId.
     *
     * @param vipCardId the vipCardId to set.
     */
    public void setVipCardId(long vipCardId){
        this.vipCardId = vipCardId;
    }

    /**
     * Gets the transactionType.
     *
     * @return the transactionType.
     */
    public VipCardTransactionType getTransactionType(){
        return transactionType;
    }

    /**
     * Sets the transactionType.
     *
     * @param transactionType the transactionType to set.
     */
    public void setTransactionType(VipCardTransactionType transactionType){
        this.transactionType = transactionType;
    }
}
