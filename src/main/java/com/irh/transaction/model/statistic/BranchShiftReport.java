package com.irh.transaction.model.statistic;

import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.IdentifiableEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a report for a shift of a branch.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchShiftReport extends IdentifiableEntity{

    /**
     * The start date time.
     */
    private Date startTime;

    /**
     * The end date time.
     */
    private Date endTime;

    /**
     * The branch.
     */
    private Branch branch;

    /**
     * The employee account of the shift.
     */
    private Account account;

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
     * The amount of VIP card recharge via cash.
     */
    private BigDecimal cashRecharge;

    /**
     * The amount of VIP card recharge via UniPay.
     */
    private BigDecimal unipayRecharge;

    /**
     * The device number.
     */
    private int device;

    /**
     * The remark.
     */
    private String remark;

    /**
     * Gets the startTime.
     *
     * @return the startTime.
     */
    public Date getStartTime(){
        return startTime;
    }

    /**
     * Sets the startTime.
     *
     * @param startTime the startTime to set.
     */
    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    /**
     * Gets the endTime.
     *
     * @return the endTime.
     */
    public Date getEndTime(){
        return endTime;
    }

    /**
     * Sets the endTime.
     *
     * @param endTime the endTime to set.
     */
    public void setEndTime(Date endTime){
        this.endTime = endTime;
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
     * Gets the account.
     *
     * @return the account.
     */
    public Account getAccount(){
        return account;
    }

    /**
     * Sets the account.
     *
     * @param account the account to set.
     */
    public void setAccount(Account account){
        this.account = account;
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
     * Gets the device.
     *
     * @return the device.
     */
    public int getDevice(){
        return device;
    }

    /**
     * Sets the device.
     *
     * @param device the device to set.
     */
    public void setDevice(int device){
        this.device = device;
    }

    /**
     * Gets the remark.
     *
     * @return the remark.
     */
    public String getRemark(){
        return remark;
    }

    /**
     * Sets the remark.
     *
     * @param remark the remark to set.
     */
    public void setRemark(String remark){
        this.remark = remark;
    }
}
