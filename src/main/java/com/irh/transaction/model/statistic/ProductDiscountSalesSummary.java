package com.irh.transaction.model.statistic;

/**
 * Represents a statistical data for the summary of the sales of a product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductDiscountSalesSummary{

    /**
     * The discount id.
     */
    private long discountId;

    /**
     * The discount name.
     */
    private String discountName;

    /**
     * The number of applied orders.
     */
    private int count;

    /**
     * Gets the discountId.
     *
     * @return the discountId.
     */
    public long getDiscountId(){
        return discountId;
    }

    /**
     * Sets the discountId.
     *
     * @param discountId the discountId to set.
     */
    public void setDiscountId(long discountId){
        this.discountId = discountId;
    }

    /**
     * Gets the discountName.
     *
     * @return the discountName.
     */
    public String getDiscountName(){
        return discountName;
    }

    /**
     * Sets the discountName.
     *
     * @param discountName the discountName to set.
     */
    public void setDiscountName(String discountName){
        this.discountName = discountName;
    }

    /**
     * Gets the count.
     *
     * @return the count.
     */
    public int getCount(){
        return count;
    }

    /**
     * Sets the count.
     *
     * @param count the count to set.
     */
    public void setCount(int count){
        this.count = count;
    }
}
