package com.irh.transaction.model.marketing;

import com.irh.transaction.model.common.HeadquarterEntity;
import com.irh.transaction.model.product.Product;

import java.util.Date;
import java.util.List;

/**
 * Represents a product discount.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductDiscount extends HeadquarterEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The discount type.
     */
    private DiscountType discountType;

    /**
     * The discount value.
     */
    private Integer discountValue;

    /**
     * The start date.
     */
    private Date startDate;

    /**
     * The end date.
     */
    private Date endDate;

    /**
     * The start time.
     */
    private Integer startTime;

    /**
     * The end time.
     */
    private Integer endTime;

    /**
     * The list of products the discount applies to.
     */
    private List<Product> products;

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the discountType.
     *
     * @return the discountType.
     */
    public DiscountType getDiscountType(){
        return discountType;
    }

    /**
     * Sets the discountType.
     *
     * @param discountType the discountType to set.
     */
    public void setDiscountType(DiscountType discountType){
        this.discountType = discountType;
    }

    /**
     * Gets the discountValue.
     *
     * @return the discountValue.
     */
    public Integer getDiscountValue(){
        return discountValue;
    }

    /**
     * Sets the discountValue.
     *
     * @param discountValue the discountValue to set.
     */
    public void setDiscountValue(Integer discountValue){
        this.discountValue = discountValue;
    }

    /**
     * Gets the products.
     *
     * @return the products.
     */
    public List<Product> getProducts(){
        return products;
    }

    /**
     * Sets the products.
     *
     * @param products the products to set.
     */
    public void setProducts(List<Product> products){
        this.products = products;
    }

    /**
     * Gets the startDate.
     *
     * @return the startDate.
     */
    public Date getStartDate(){
        return startDate;
    }

    /**
     * Sets the startDate.
     *
     * @param startDate the startDate to set.
     */
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    /**
     * Gets the endDate.
     *
     * @return the endDate.
     */
    public Date getEndDate(){
        return endDate;
    }

    /**
     * Sets the endDate.
     *
     * @param endDate the endDate to set.
     */
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    /**
     * Gets the startTime.
     *
     * @return the startTime.
     */
    public Integer getStartTime(){
        return startTime;
    }

    /**
     * Sets the startTime.
     *
     * @param startTime the startTime to set.
     */
    public void setStartTime(Integer startTime){
        this.startTime = startTime;
    }

    /**
     * Gets the endTime.
     *
     * @return the endTime.
     */
    public Integer getEndTime(){
        return endTime;
    }

    /**
     * Sets the endTime.
     *
     * @param endTime the endTime to set.
     */
    public void setEndTime(Integer endTime){
        this.endTime = endTime;
    }
}
