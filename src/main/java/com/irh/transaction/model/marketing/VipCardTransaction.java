package com.irh.transaction.model.marketing;

import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.IdentifiableEntity;
import com.irh.transaction.model.common.PayType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a transaction record for a VIP card.
 *
 * <p> This transaction is the combination of recharge, consume, refund and invoice record for a VIP card. </p>
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class VipCardTransaction extends IdentifiableEntity{

    /**
     * The VIP card id.
     */
    private long vipCardId;

    /**
     * The transaction amount.
     */
    private BigDecimal amount;

    /**
     * The bonus amount for recharge.
     */
    private BigDecimal bonus;

    /**
     * The balance of the VIP card before the transaction.
     */
    private BigDecimal balanceBefore;

    /**
     * The balance of the VIP card after the transaction.
     */
    private BigDecimal balanceAfter;

    /**
     * The pay type.
     */
    private PayType payType;

    /**
     * The datetime when the recharge took place.
     */
    private Date createdAt;

    /**
     * The handler account.
     */
    private Account handler;

    /**
     * The branch that handles the recharge.
     */
    private Branch branch;

    /**
     * The transaction type.
     */
    private com.irh.transaction.model.marketing.VipCardTransactionType transactionType;

    /**
     * The amount of invoice.
     */
    private BigDecimal invoiceAmount;

    /**
     * The point.
     */
    private Integer point;

    /**
     * The transaction number.
     */
    private String number;

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
     * Gets the branch.
     *
     * @return the branch.
     */
    public Branch getBranch(){
        return branch;
    }

    /**
     * Sets the branch.
     *
     * @param branch the branch to set.
     */
    public void setBranch(Branch branch){
        this.branch = branch;
    }

    /**
     * Gets the balanceBefore.
     *
     * @return the balanceBefore.
     */
    public BigDecimal getBalanceBefore(){
        return balanceBefore;
    }

    /**
     * Sets the balanceBefore.
     *
     * @param balanceBefore the balanceBefore to set.
     */
    public void setBalanceBefore(BigDecimal balanceBefore){
        this.balanceBefore = balanceBefore;
    }

    /**
     * Gets the balanceAfter.
     *
     * @return the balanceAfter.
     */
    public BigDecimal getBalanceAfter(){
        return balanceAfter;
    }

    /**
     * Sets the balanceAfter.
     *
     * @param balanceAfter the balanceAfter to set.
     */
    public void setBalanceAfter(BigDecimal balanceAfter){
        this.balanceAfter = balanceAfter;
    }

    /**
     * Gets the transactionType.
     *
     * @return the transactionType.
     */
    public com.irh.transaction.model.marketing.VipCardTransactionType getTransactionType(){
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
     * Gets the point.
     *
     * @return the point.
     */
    public Integer getPoint(){
        return point;
    }

    /**
     * Sets the point.
     *
     * @param point the point to set.
     */
    public void setPoint(Integer point){
        this.point = point;
    }

    /**
     * Gets the bonus.
     *
     * @return the bonus.
     */
    public BigDecimal getBonus(){
        return bonus;
    }

    /**
     * Sets the bonus.
     *
     * @param bonus the bonus to set.
     */
    public void setBonus(BigDecimal bonus){
        this.bonus = bonus;
    }

    /**
     * Gets the invoiceAmount.
     *
     * @return the invoiceAmount.
     */
    public BigDecimal getInvoiceAmount(){
        return invoiceAmount;
    }

    /**
     * Sets the invoiceAmount.
     *
     * @param invoiceAmount the invoiceAmount to set.
     */
    public void setInvoiceAmount(BigDecimal invoiceAmount){
        this.invoiceAmount = invoiceAmount;
    }

    /**
     * Gets the number.
     *
     * @return the number.
     */
    public String getNumber(){
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the number to set.
     */
    public void setNumber(String number){
        this.number = number;
    }
}
