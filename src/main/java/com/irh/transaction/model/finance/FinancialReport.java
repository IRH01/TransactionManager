package com.irh.transaction.model.finance;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.HeadquarterEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a financial report.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>Added the {@link FinancialReport#invoices} field. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class FinancialReport extends HeadquarterEntity{

    /**
     * The group id.
     */
    private Long groupId;

    /**
     * The branch.
     */
    private Branch branch;

    /**
     * The date.
     */
    private Date date;

    /**
     * The total amount of sales.
     */
    private BigDecimal totalSales;

    /**
     * The amount of cash sales.
     */
    private BigDecimal cashSales;

    /**
     * The amount of UniPay sales.
     */
    private BigDecimal unipaySales;

    /**
     * The amount of AliPay sales.
     */
    private BigDecimal alipaySales;

    /**
     * The amount of WeChat sales.
     */
    private BigDecimal wechatSales;

    /**
     * The amount of VIP card sales.
     */
    private BigDecimal vipCardSales;

    /**
     * The amount of coupons used.
     */
    private BigDecimal coupons;

    /**
     * The amount of discounts.
     */
    private BigDecimal discounts;

    /**
     * The amount of signed bills.
     */
    private BigDecimal signedBills;

    /**
     * The total amount of refund.
     */
    private BigDecimal totalRefund;

    /**
     * The amount of refund via cash.
     */
    private BigDecimal cashRefund;

    /**
     * The amount of refund via UniPay.
     */
    private BigDecimal unipayRefund;

    /**
     * The amount of refund via AliPay.
     */
    private BigDecimal alipayRefund;

    /**
     * The amount of refund via WeChat.
     */
    private BigDecimal wechatRefund;

    /**
     * The amount of VIP card recharge.
     */
    private BigDecimal totalRecharge;

    /**
     * The amount of VIP card recharge via cash.
     */
    private BigDecimal cashRecharge;

    /**
     * The amount of VIP card recharge via UniPay.
     */
    private BigDecimal unipayRecharge;

    /**
     * The amount of gifts.
     */
    private BigDecimal gifts;

    /**
     * The amount of invoices.
     *
     * @since 1.1
     */
    private BigDecimal invoices;

    /**
     * The total number of orders.
     */
    private int orders;

    /**
     * Gets the groupId.
     *
     * @return the groupId.
     */
    public Long getGroupId(){
        return groupId;
    }

    /**
     * Sets the groupId.
     *
     * @param groupId the groupId to set.
     */
    public void setGroupId(Long groupId){
        this.groupId = groupId;
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
     * Gets the date.
     *
     * @return the date.
     */
    public Date getDate(){
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the date to set.
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Gets the totalSales.
     *
     * @return the totalSales.
     */
    public BigDecimal getTotalSales(){
        return totalSales;
    }

    /**
     * Sets the totalSales.
     *
     * @param totalSales the totalSales to set.
     */
    public void setTotalSales(BigDecimal totalSales){
        this.totalSales = totalSales;
    }

    /**
     * Gets the cashSales.
     *
     * @return the cashSales.
     */
    public BigDecimal getCashSales(){
        return cashSales;
    }

    /**
     * Sets the cashSales.
     *
     * @param cashSales the cashSales to set.
     */
    public void setCashSales(BigDecimal cashSales){
        this.cashSales = cashSales;
    }

    /**
     * Gets the unipaySales.
     *
     * @return the unipaySales.
     */
    public BigDecimal getUnipaySales(){
        return unipaySales;
    }

    /**
     * Sets the unipaySales.
     *
     * @param unipaySales the unipaySales to set.
     */
    public void setUnipaySales(BigDecimal unipaySales){
        this.unipaySales = unipaySales;
    }

    /**
     * Gets the alipaySales.
     *
     * @return the alipaySales.
     */
    public BigDecimal getAlipaySales(){
        return alipaySales;
    }

    /**
     * Sets the alipaySales.
     *
     * @param alipaySales the alipaySales to set.
     */
    public void setAlipaySales(BigDecimal alipaySales){
        this.alipaySales = alipaySales;
    }

    /**
     * Gets the wechatSales.
     *
     * @return the wechatSales.
     */
    public BigDecimal getWechatSales(){
        return wechatSales;
    }

    /**
     * Sets the wechatSales.
     *
     * @param wechatSales the wechatSales to set.
     */
    public void setWechatSales(BigDecimal wechatSales){
        this.wechatSales = wechatSales;
    }

    /**
     * Gets the coupons.
     *
     * @return the coupons.
     */
    public BigDecimal getCoupons(){
        return coupons;
    }

    /**
     * Sets the coupons.
     *
     * @param coupons the coupons to set.
     */
    public void setCoupons(BigDecimal coupons){
        this.coupons = coupons;
    }

    /**
     * Gets the discounts.
     *
     * @return the discounts.
     */
    public BigDecimal getDiscounts(){
        return discounts;
    }

    /**
     * Sets the discounts.
     *
     * @param discounts the discounts to set.
     */
    public void setDiscounts(BigDecimal discounts){
        this.discounts = discounts;
    }

    /**
     * Gets the signedBills.
     *
     * @return the signedBills.
     */
    public BigDecimal getSignedBills(){
        return signedBills;
    }

    /**
     * Sets the signedBills.
     *
     * @param signedBills the signedBills to set.
     */
    public void setSignedBills(BigDecimal signedBills){
        this.signedBills = signedBills;
    }

    /**
     * Gets the totalRefund.
     *
     * @return the totalRefund.
     */
    public BigDecimal getTotalRefund(){
        return totalRefund;
    }

    /**
     * Sets the totalRefund.
     *
     * @param totalRefund the totalRefund to set.
     */
    public void setTotalRefund(BigDecimal totalRefund){
        this.totalRefund = totalRefund;
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
     * Gets the orders.
     *
     * @return the orders.
     */
    public int getOrders(){
        return orders;
    }

    /**
     * Sets the orders.
     *
     * @param orders the orders to set.
     */
    public void setOrders(int orders){
        this.orders = orders;
    }

    /**
     * Gets the cashRefund.
     *
     * @return the cashRefund.
     */
    public BigDecimal getCashRefund(){
        return cashRefund;
    }

    /**
     * Sets the cashRefund.
     *
     * @param cashRefund the cashRefund to set.
     */
    public void setCashRefund(BigDecimal cashRefund){
        this.cashRefund = cashRefund;
    }

    /**
     * Gets the unipayRefund.
     *
     * @return the unipayRefund.
     */
    public BigDecimal getUnipayRefund(){
        return unipayRefund;
    }

    /**
     * Sets the unipayRefund.
     *
     * @param unipayRefund the unipayRefund to set.
     */
    public void setUnipayRefund(BigDecimal unipayRefund){
        this.unipayRefund = unipayRefund;
    }

    /**
     * Gets the alipayRefund.
     *
     * @return the alipayRefund.
     */
    public BigDecimal getAlipayRefund(){
        return alipayRefund;
    }

    /**
     * Sets the alipayRefund.
     *
     * @param alipayRefund the alipayRefund to set.
     */
    public void setAlipayRefund(BigDecimal alipayRefund){
        this.alipayRefund = alipayRefund;
    }

    /**
     * Gets the wechatRefund.
     *
     * @return the wechatRefund.
     */
    public BigDecimal getWechatRefund(){
        return wechatRefund;
    }

    /**
     * Sets the wechatRefund.
     *
     * @param wechatRefund the wechatRefund to set.
     */
    public void setWechatRefund(BigDecimal wechatRefund){
        this.wechatRefund = wechatRefund;
    }

    /**
     * Gets the gifts.
     *
     * @return the gifts.
     */
    public BigDecimal getGifts(){
        return gifts;
    }

    /**
     * Sets the gifts.
     *
     * @param gifts the gifts to set.
     */
    public void setGifts(BigDecimal gifts){
        this.gifts = gifts;
    }

    /**
     * Gets the invoices.
     *
     * @return the invoices.
     * @since 1.1
     */
    public BigDecimal getInvoices(){
        return invoices;
    }

    /**
     * Sets the invoices.
     *
     * @param invoices the invoices to set.
     * @since 1.1
     */
    public void setInvoices(BigDecimal invoices){
        this.invoices = invoices;
    }

    /**
     * Gets the vipCardSales.
     *
     * @return the vipCardSales.
     */
    public BigDecimal getVipCardSales(){
        return vipCardSales;
    }

    /**
     * Sets the vipCardSales.
     *
     * @param vipCardSales the vipCardSales to set.
     */
    public void setVipCardSales(BigDecimal vipCardSales){
        this.vipCardSales = vipCardSales;
    }
}
