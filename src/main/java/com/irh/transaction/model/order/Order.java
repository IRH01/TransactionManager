package com.irh.transaction.model.order;

import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.model.common.HeadquarterEntity;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.common.Platform;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents an order.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li>The memberCard field has been replaced with {@link com.irh.transaction.model.order.Order#vipCardId}.</li>
 * <li>The queueToken field has bee replaced with the {@link com.irh.transaction.model.order.Order#table} </li> <li> Added {@link com.irh.transaction.model.order.Order#invoiceRecords}
 * to replace the previous invoiceRecord field to support multiple invoice records. </li> <li> Added the {@link
 * com.irh.transaction.model.order.Order#totalInvoices}, {@link com.irh.transaction.model.order.Order#coupon} fields for statistic purpose. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class Order extends HeadquarterEntity{

    /**
     * The id of the branch.
     */
    private Long branchId;

    /**
     * The handler account.
     */
    private Account handler;

    /**
     * The date when the order was created.
     */
    private Date createdAt;

    /**
     * The associated bill number.
     */
    private String bill;

    /**
     * The price.
     */
    private BigDecimal price;

    /**
     * The original price;
     */
    private BigDecimal originalPrice;

    /**
     * The discount.
     */
    private Float discount;

    /**
     * The number of people.
     */
    private Integer numberOfPeople;

    /**
     * The pay type.
     */
    private PayType payType;

    /**
     * The platform from which the order was created.
     */
    private Platform platform;

    /**
     * The list of items.
     */
    private List<OrderItem> items;

    /**
     * The service type.
     */
    private com.irh.transaction.model.order.OrderServiceType serviceType;

    /**
     * The order status.
     */
    private OrderStatus status;

    /**
     * The bill signer.
     */
    private Account signer;

    /**
     * The VIP card id.
     *
     * @since 1.1
     */
    private Long vipCardId;

    /**
     * The remark;
     */
    private String remark;

    /**
     * The original order bill number, for sub order only.
     */
    private String originalBill;

    /**
     * The device where the order was made.
     */
    private Integer device;

    /**
     * The received amount.
     */
    private BigDecimal received;

    /**
     * The cash change amount.
     */
    private BigDecimal change;

    /**
     * The associated table.
     *
     * @since 1.1
     */
    private BranchTable table;

    /**
     * The list of order payment records.
     */
    private List<OrderPaymentRecord> paymentRecords;

    /**
     * The list of invoice records.
     *
     * @since 1.1
     */
    private List<OrderInvoiceRecord> invoiceRecords;

    /**
     * The total amount of invoices.
     *
     * @since 1.1
     */
    private BigDecimal totalInvoices;

    /**
     * The amount of coupon.
     *
     * @since 1.1
     */
    private BigDecimal coupon;

    /**
     * Gets the branchId.
     *
     * @return the branchId.
     */
    public Long getBranchId(){
        return branchId;
    }

    /**
     * Sets the branchId.
     *
     * @param branchId the branchId to set.
     */
    public void setBranchId(Long branchId){
        this.branchId = branchId;
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
     * Gets the date when the order was created.
     *
     * @return the date when the order was created.
     */
    public Date getCreatedAt(){
        return createdAt;
    }

    /**
     * Sets the date when the order was created.
     *
     * @param createdAt the date to set.
     */
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    /**
     * Gets the bill.
     *
     * @return the bill.
     */
    public String getBill(){
        return bill;
    }

    /**
     * Sets the bill.
     *
     * @param bill the bill to set.
     */
    public void setBill(String bill){
        this.bill = bill;
    }

    /**
     * Gets the price.
     *
     * @return the price.
     */
    public BigDecimal getPrice(){
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set.
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    /**
     * Gets the numberOfPeople.
     *
     * @return the numberOfPeople.
     */
    public Integer getNumberOfPeople(){
        return numberOfPeople;
    }

    /**
     * Sets the numberOfPeople.
     *
     * @param numberOfPeople the numberOfPeople to set.
     */
    public void setNumberOfPeople(Integer numberOfPeople){
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * Gets the pay type.
     *
     * @return the pay type.
     */
    public PayType getPayType(){
        return payType;
    }

    /**
     * Sets the pay type.
     *
     * @param payType the pay type to set.
     */
    public void setPayType(PayType payType){
        this.payType = payType;
    }

    /**
     * Gets the platform.
     *
     * @return the platform.
     */
    public Platform getPlatform(){
        return platform;
    }

    /**
     * Sets the platform.
     *
     * @param platform the platform to set.
     */
    public void setPlatform(Platform platform){
        this.platform = platform;
    }

    /**
     * Gets the items.
     *
     * @return the items.
     */
    public List<OrderItem> getItems(){
        return items;
    }

    /**
     * Sets the items.
     *
     * @param items the items to set.
     */
    public void setItems(List<OrderItem> items){
        this.items = items;
    }

    /**
     * Gets the service type.
     *
     * @return the service type.
     */
    public com.irh.transaction.model.order.OrderServiceType getServiceType(){
        return serviceType;
    }

    /**
     * Sets the service type.
     *
     * @param serviceType the service type to set.
     */
    public void setServiceType(OrderServiceType serviceType){
        this.serviceType = serviceType;
    }

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public OrderStatus getStatus(){
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set.
     */
    public void setStatus(OrderStatus status){
        this.status = status;
    }

    /**
     * Gets the signer.
     *
     * @return the signer.
     */
    public Account getSigner(){
        return signer;
    }

    /**
     * Sets the signer.
     *
     * @param signer the signer to set.
     */
    public void setSigner(Account signer){
        this.signer = signer;
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

    /**
     * Gets the originalPrice.
     *
     * @return the originalPrice.
     */
    public BigDecimal getOriginalPrice(){
        return originalPrice;
    }

    /**
     * Sets the originalPrice.
     *
     * @param originalPrice the originalPrice to set.
     */
    public void setOriginalPrice(BigDecimal originalPrice){
        this.originalPrice = originalPrice;
    }

    /**
     * Gets the discount.
     *
     * @return the discount.
     */
    public Float getDiscount(){
        return discount;
    }

    /**
     * Sets the discount.
     *
     * @param discount the discount to set.
     */
    public void setDiscount(Float discount){
        this.discount = discount;
    }

    /**
     * Gets the paymentRecords.
     *
     * @return the paymentRecords.
     */
    public List<OrderPaymentRecord> getPaymentRecords(){
        return paymentRecords;
    }

    /**
     * Sets the paymentRecords.
     *
     * @param paymentRecords the paymentRecords to set.
     */
    public void setPaymentRecords(List<OrderPaymentRecord> paymentRecords){
        this.paymentRecords = paymentRecords;
    }

    /**
     * Gets the originalBill.
     *
     * @return the originalBill.
     */
    public String getOriginalBill(){
        return originalBill;
    }

    /**
     * Sets the originalBill.
     *
     * @param originalBill the originalBill to set.
     */
    public void setOriginalBill(String originalBill){
        this.originalBill = originalBill;
    }

    /**
     * Gets the invoiceRecords.
     *
     * @return the invoiceRecords.
     * @since 1.1
     */
    public List<OrderInvoiceRecord> getInvoiceRecords(){
        return invoiceRecords;
    }

    /**
     * Sets the invoiceRecords.
     *
     * @param invoiceRecords the invoiceRecords to set.
     * @since 1.1
     */
    public void setInvoiceRecords(List<OrderInvoiceRecord> invoiceRecords){
        this.invoiceRecords = invoiceRecords;
    }

    /**
     * Gets the received.
     *
     * @return the received.
     */
    public BigDecimal getReceived(){
        return received;
    }

    /**
     * Sets the received.
     *
     * @param received the received to set.
     */
    public void setReceived(BigDecimal received){
        this.received = received;
    }

    /**
     * Gets the change.
     *
     * @return the change.
     */
    public BigDecimal getChange(){
        return change;
    }

    /**
     * Sets the change.
     *
     * @param change the change to set.
     */
    public void setChange(BigDecimal change){
        this.change = change;
    }

    /**
     * Gets the table.
     *
     * @return the table.
     * @since 1.1
     */
    public BranchTable getTable(){
        return table;
    }

    /**
     * Sets the table.
     *
     * @param table the table to set.
     * @since 1.1
     */
    public void setTable(BranchTable table){
        this.table = table;
    }

    /**
     * Gets the vipCardId.
     *
     * @return the vipCardId.
     * @since 1.1
     */
    public Long getVipCardId(){
        return vipCardId;
    }

    /**
     * Sets the vipCardId.
     *
     * @param vipCardId the vipCardId to set.
     * @since 1.1
     */
    public void setVipCardId(Long vipCardId){
        this.vipCardId = vipCardId;
    }

    /**
     * Gets the totalInvoices.
     *
     * @return the totalInvoices.
     * @since 1.1
     */
    public BigDecimal getTotalInvoices(){
        return totalInvoices;
    }

    /**
     * Sets the totalInvoices.
     *
     * @param totalInvoices the totalInvoices to set.
     * @since 1.1
     */
    public void setTotalInvoices(BigDecimal totalInvoices){
        this.totalInvoices = totalInvoices;
    }

    /**
     * Gets the coupon.
     *
     * @return the coupon.
     * @since 1.1
     */
    public BigDecimal getCoupon(){
        return coupon;
    }

    /**
     * Sets the coupon.
     *
     * @param coupon the coupon to set.
     * @since 1.1
     */
    public void setCoupon(BigDecimal coupon){
        this.coupon = coupon;
    }

    /**
     * Gets the device.
     *
     * @return the device.
     */
    public Integer getDevice(){
        return device;
    }

    /**
     * Sets the device.
     *
     * @param device the device to set.
     */
    public void setDevice(Integer device){
        this.device = device;
    }
}
