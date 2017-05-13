package com.irh.transaction.model.finance;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.marketing.VipCard;

import java.math.BigDecimal;

/**
 * Represents a report for VIP card.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class VipCardReport{

    /**
     * The branch.
     */
    private Branch branch;

    /**
     * The VIP card.
     */
    private VipCard vipCard;

    /**
     * The total recharge amount.
     */
    private BigDecimal totalRecharge;

    /**
     * The recharge amount via cash.
     */
    private BigDecimal cashRecharge;

    /**
     * The recharge amount via UniPay.
     */
    private BigDecimal unipayRecharge;

    /**
     * The total consume amount.
     */
    private BigDecimal consume;

    /**
     * The total invoice amount.
     */
    private BigDecimal invoice;

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
     * Gets the vipCard.
     *
     * @return the vipCard.
     */
    public VipCard getVipCard(){
        return vipCard;
    }

    /**
     * Sets the vipCard.
     *
     * @param vipCard the vipCard to set.
     */
    public void setVipCard(VipCard vipCard){
        this.vipCard = vipCard;
    }

    /**
     * Gets the totalRecharge.
     *
     * @return the totalRecharge.
     */
    public BigDecimal getTotalRecharge(){
        return totalRecharge;
    }

    /**
     * Sets the totalRecharge.
     *
     * @param totalRecharge the totalRecharge to set.
     */
    public void setTotalRecharge(BigDecimal totalRecharge){
        this.totalRecharge = totalRecharge;
    }

    /**
     * Gets the cashRecharge.
     *
     * @return the cashRecharge.
     */
    public BigDecimal getCashRecharge(){
        return cashRecharge;
    }

    /**
     * Sets the cashRecharge.
     *
     * @param cashRecharge the cashRecharge to set.
     */
    public void setCashRecharge(BigDecimal cashRecharge){
        this.cashRecharge = cashRecharge;
    }

    /**
     * Gets the unipayRecharge.
     *
     * @return the unipayRecharge.
     */
    public BigDecimal getUnipayRecharge(){
        return unipayRecharge;
    }

    /**
     * Sets the unipayRecharge.
     *
     * @param unipayRecharge the unipayRecharge to set.
     */
    public void setUnipayRecharge(BigDecimal unipayRecharge){
        this.unipayRecharge = unipayRecharge;
    }

    /**
     * Gets the consume.
     *
     * @return the consume.
     */
    public BigDecimal getConsume(){
        return consume;
    }

    /**
     * Sets the consume.
     *
     * @param consume the consume to set.
     */
    public void setConsume(BigDecimal consume){
        this.consume = consume;
    }

    /**
     * Gets the invoice.
     *
     * @return the invoice.
     */
    public BigDecimal getInvoice(){
        return invoice;
    }

    /**
     * Sets the invoice.
     *
     * @param invoice the invoice to set.
     */
    public void setInvoice(BigDecimal invoice){
        this.invoice = invoice;
    }
}
