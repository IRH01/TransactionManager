package com.irh.transaction.dto.search;

import java.util.Date;

/**
 * Contains filter parameters to search by date range.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class DateFilter extends SearchFilter{

    /**
     * The start of the date to filter.
     */
    private Date dateFrom;

    /**
     * The end of the date to filter.
     */
    private Date dateTo;

    /**
     * Gets the dateFrom.
     *
     * @return the dateFrom.
     */
    public Date getDateFrom(){
        return dateFrom;
    }

    /**
     * Sets the dateFrom.
     *
     * @param dateFrom the dateFrom to set.
     */
    public void setDateFrom(Date dateFrom){
        this.dateFrom = dateFrom;
    }

    /**
     * Gets the dateTo.
     *
     * @return the dateTo.
     */
    public Date getDateTo(){
        return dateTo;
    }

    /**
     * Sets the dateTo.
     *
     * @param dateTo the dateTo to set.
     */
    public void setDateTo(Date dateTo){
        this.dateTo = dateTo;
    }
}
