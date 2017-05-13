package com.irh.transaction.model.statistic;

import java.util.List;

/**
 * Represents the details of statistical data for total sales.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class TotalSalesDetails{

    private long hqId;

    /**
     * The list of sales statistical details grouped by hour.
     */
    private List<HourlyTotalSalesDetailItem> hourlyDetails;

    /**
     * The list of sales statistical details grouped by service types.
     */
    private List<TotalSalesDetailItem> serviceTypeDetails;

    /**
     * The list of sales statistical details grouped by platforms.
     */
    private List<TotalSalesDetailItem> platformDetails;

    /**
     * The list of sales statistical details grouped by pay types.
     */
    private List<TotalSalesDetailItem> payTypeDetails;

    /**
     * Gets the hqId.
     *
     * @return the hqId.
     */
    public long getHqId(){
        return hqId;
    }

    /**
     * Sets the hqId.
     *
     * @param hqId the hqId to set.
     */
    public void setHqId(long hqId){
        this.hqId = hqId;
    }

    /**
     * Gets the hourlyDetails.
     *
     * @return the hourlyDetails.
     */
    public List<HourlyTotalSalesDetailItem> getHourlyDetails(){
        return hourlyDetails;
    }

    /**
     * Sets the hourlyDetails.
     *
     * @param hourlyDetails the hourlyDetails to set.
     */
    public void setHourlyDetails(List<HourlyTotalSalesDetailItem> hourlyDetails){
        this.hourlyDetails = hourlyDetails;
    }

    /**
     * Gets the serviceTypeDetails.
     *
     * @return the serviceTypeDetails.
     */
    public List<TotalSalesDetailItem> getServiceTypeDetails(){
        return serviceTypeDetails;
    }

    /**
     * Sets the serviceTypeDetails.
     *
     * @param serviceTypeDetails the serviceTypeDetails to set.
     */
    public void setServiceTypeDetails(List<TotalSalesDetailItem> serviceTypeDetails){
        this.serviceTypeDetails = serviceTypeDetails;
    }

    /**
     * Gets the platformDetails.
     *
     * @return the platformDetails.
     */
    public List<TotalSalesDetailItem> getPlatformDetails(){
        return platformDetails;
    }

    /**
     * Sets the platformDetails.
     *
     * @param platformDetails the platformDetails to set.
     */
    public void setPlatformDetails(List<TotalSalesDetailItem> platformDetails){
        this.platformDetails = platformDetails;
    }

    /**
     * Gets the payTypeDetails.
     *
     * @return the payTypeDetails.
     */
    public List<TotalSalesDetailItem> getPayTypeDetails(){
        return payTypeDetails;
    }

    /**
     * Sets the payTypeDetails.
     *
     * @param payTypeDetails the payTypeDetails to set.
     */
    public void setPayTypeDetails(List<TotalSalesDetailItem> payTypeDetails){
        this.payTypeDetails = payTypeDetails;
    }

}
