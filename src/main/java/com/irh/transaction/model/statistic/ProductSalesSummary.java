package com.irh.transaction.model.statistic;

import com.irh.transaction.model.product.Product;

import java.util.Date;

/**
 * Represents a statistical data for the summary of the sales of a product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductSalesSummary extends BaseSalesStatistic{

    /**
     * The product.
     */
    private Product product;

    /**
     * The order rate, in percentage.
     */
    private float orderRate;

    /**
     * The date.
     */
    private Date date;

    /**
     * Gets the product.
     *
     * @return the product.
     */
    public Product getProduct(){
        return product;
    }

    /**
     * Sets the product.
     *
     * @param product the product to set.
     */
    public void setProduct(Product product){
        this.product = product;
    }

    /**
     * Gets the orderRate.
     *
     * @return the orderRate.
     */
    public float getOrderRate(){
        return orderRate;
    }

    /**
     * Sets the orderRate.
     *
     * @param orderRate the orderRate to set.
     */
    public void setOrderRate(float orderRate){
        this.orderRate = orderRate;
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
}
