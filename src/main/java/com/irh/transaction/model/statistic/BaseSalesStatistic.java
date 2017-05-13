package com.irh.transaction.model.statistic;

import java.math.BigDecimal;

/**
 * The base class for statistical data for sales.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public abstract class BaseSalesStatistic{

    /**
     * The total sales.
     */
    private BigDecimal sales;

    /**
     * The number of count.
     */
    private int count;

    /**
     * Gets the sales.
     *
     * @return the sales.
     */
    public BigDecimal getSales(){
        return sales;
    }

    /**
     * Sets the sales.
     *
     * @param sales the sales to set.
     */
    public void setSales(BigDecimal sales){
        this.sales = sales;
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
