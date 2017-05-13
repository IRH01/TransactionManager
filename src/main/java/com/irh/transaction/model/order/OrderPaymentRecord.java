package com.irh.transaction.model.order;

import com.irh.transaction.model.common.PayType;

import java.math.BigDecimal;

/**
 * Represents the order payment record.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>The memberId field has been removed and the associated member information has been
 * moved to {@link Order#vipCard}. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class OrderPaymentRecord{

    /**
     * The amount.
     */
    private BigDecimal amount;

    /**
     * The pay type.
     */
    private PayType payType;

    /**
     * Gets the amount.
     *
     * @return the amount.
     */
    public BigDecimal getAmount(){
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the amount to set.
     */
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    /**
     * Gets the payType.
     *
     * @return the payType.
     */
    public PayType getPayType(){
        return payType;
    }

    /**
     * Sets the payType.
     *
     * @param payType the payType to set.
     */
    public void setPayType(PayType payType){
        this.payType = payType;
    }
}
