package com.irh.transaction.model.statistic;

import com.irh.transaction.model.product.Category;

import java.util.Date;

/**
 * Represents a statistical data for the summary of the sales of a category.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author 任欢，Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class CategorySalesSummary extends BaseSalesStatistic{

    /**
     * The category.
     */
    private Category category;

    /**
     * The order rate, in percentage.
     */
    private float orderRate;

    /**
     * The date.
     */
    private Date date;

    /**
     * Gets the category.
     *
     * @return the category.
     */
    public Category getCategory(){
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category to set.
     */
    public void setCategory(Category category){
        this.category = category;
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
