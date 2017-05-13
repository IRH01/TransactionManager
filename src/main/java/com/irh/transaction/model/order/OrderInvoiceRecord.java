package com.irh.transaction.model.order;

import com.irh.transaction.model.account.Account;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents an order invoice record.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class OrderInvoiceRecord{

    /**
     * The handler.
     */
    private Account handler;

    /**
     * The invoice amount.
     */
    private BigDecimal amount;

    /**
     * The datetime when the invoice record was created.
     */
    private Date createdAt;

    /**
     * Gets the handler.
     *
     * @return the handler.
     */
    public Account getHandler(){
        return handler;
    }

    /**
     * Sets the handler.
     *
     * @param handler the handler to set.
     */
    public void setHandler(Account handler){
        this.handler = handler;
    }

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
     * Gets the createdAt.
     *
     * @return the createdAt.
     */
    public Date getCreatedAt(){
        return createdAt;
    }

    /**
     * Sets the createdAt.
     *
     * @param createdAt the createdAt to set.
     */
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
}
