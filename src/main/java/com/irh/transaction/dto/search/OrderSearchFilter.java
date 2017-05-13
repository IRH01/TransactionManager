package com.irh.transaction.dto.search;

import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.order.OrderServiceType;
import com.irh.transaction.model.order.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The filter for order searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class OrderSearchFilter extends SearchFilter{

    /**
     * The minimum price to filter;
     */
    private BigDecimal priceFrom;

    /**
     * The maximum price to filter;
     */
    private BigDecimal priceTo;

    /**
     * The start of the created date to filter.
     */
    private Date createdAtFrom;

    /**
     * The end of the created date to filter.
     */
    private Date createdAtTo;

    /**
     * The platform to filter.
     */
    private Platform platform;

    /**
     * The pay type to filter.
     */
    private PayType payType;

    /**
     * The service type to filter.
     */
    private OrderServiceType serviceType;

    /**
     * The status to filter.
     */
    private OrderStatus status;

    /**
     * The bill number to filter.
     */
    private String bill;

    /**
     * Gets the priceFrom.
     *
     * @return the priceFrom.
     */
    public BigDecimal getPriceFrom(){
        return priceFrom;
    }

    /**
     * Sets the priceFrom.
     *
     * @param priceFrom the priceFrom to set.
     */
    public void setPriceFrom(BigDecimal priceFrom){
        this.priceFrom = priceFrom;
    }

    /**
     * Gets the priceTo.
     *
     * @return the priceTo.
     */
    public BigDecimal getPriceTo(){
        return priceTo;
    }

    /**
     * Sets the priceTo.
     *
     * @param priceTo the priceTo to set.
     */
    public void setPriceTo(BigDecimal priceTo){
        this.priceTo = priceTo;
    }

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
     * Gets the serviceType.
     *
     * @return the serviceType.
     */
    public OrderServiceType getServiceType(){
        return serviceType;
    }

    /**
     * Sets the serviceType.
     *
     * @param serviceType the serviceType to set.
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
}
