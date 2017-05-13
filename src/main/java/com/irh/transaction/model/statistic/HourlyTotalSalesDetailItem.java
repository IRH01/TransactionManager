package com.irh.transaction.model.statistic;

/**
 * Represents a statistical data details for total sales grouped by hour.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class HourlyTotalSalesDetailItem extends TotalSalesDetailItem{

    /**
     * The hour by which to group the data.
     */
    private int hour;

    /**
     * Gets the hour.
     *
     * @return the hour.
     */
    public int getHour(){
        return hour;
    }

    /**
     * Sets the hour.
     *
     * @param hour the hour to set.
     */
    public void setHour(int hour){
        this.hour = hour;
    }
}
