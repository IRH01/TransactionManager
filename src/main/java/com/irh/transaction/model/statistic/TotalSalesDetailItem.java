package com.irh.transaction.model.statistic;

import java.math.BigDecimal;

/**
 * Represents a statistical data details for total sales.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class TotalSalesDetailItem extends StatisticDetailItem{

    /**
     * The table tun rate, in percentage.
     */
    private Float turnRate;

    /**
     * The average price per order.
     */
    private BigDecimal average;

    /**
     * Gets the turnRate.
     *
     * @return the turnRate.
     */
    public Float getTurnRate(){
        return turnRate;
    }

    /**
     * Sets the turnRate.
     *
     * @param turnRate the turnRate to set.
     */
    public void setTurnRate(Float turnRate){
        this.turnRate = turnRate;
    }

    /**
     * Gets the average.
     *
     * @return the average.
     */
    public BigDecimal getAverage(){
        return average;
    }

    /**
     * Sets the average.
     *
     * @param average the average to set.
     */
    public void setAverage(BigDecimal average){
        this.average = average;
    }
}
