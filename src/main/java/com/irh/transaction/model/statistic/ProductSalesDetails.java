package com.irh.transaction.model.statistic;

import java.util.List;

/**
 * Represents the details of statistical data for a product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ProductSalesDetails{

    /**
     * The product id.
     */
    private long productId;

    /**
     * The statistical details for service types.
     */
    private List<StatisticDetailItem> serviceTypeDetails;

    /**
     * The statistical details for product options.
     */
    private List<StatisticDetailItem> optionDetails;

    /**
     * The list of sales summary grouped by date.
     */
    private List<ProductSalesSummary> dailySummaries;

    /**
     * Gets the productId.
     *
     * @return the productId.
     */
    public long getProductId(){
        return productId;
    }

    /**
     * Sets the productId.
     *
     * @param productId the productId to set.
     */
    public void setProductId(long productId){
        this.productId = productId;
    }

    /**
     * Gets the serviceTypeDetails.
     *
     * @return the serviceTypeDetails.
     */
    public List<StatisticDetailItem> getServiceTypeDetails(){
        return serviceTypeDetails;
    }

    /**
     * Sets the serviceTypeDetails.
     *
     * @param serviceTypeDetails the serviceTypeDetails to set.
     */
    public void setServiceTypeDetails(List<StatisticDetailItem> serviceTypeDetails){
        this.serviceTypeDetails = serviceTypeDetails;
    }

    /**
     * Gets the optionDetails.
     *
     * @return the optionDetails.
     */
    public List<StatisticDetailItem> getOptionDetails(){
        return optionDetails;
    }

    /**
     * Sets the optionDetails.
     *
     * @param optionDetails the optionDetails to set.
     */
    public void setOptionDetails(List<StatisticDetailItem> optionDetails){
        this.optionDetails = optionDetails;
    }

    /**
     * Gets the dailySummaries.
     *
     * @return the dailySummaries.
     */
    public List<ProductSalesSummary> getDailySummaries(){
        return dailySummaries;
    }

    /**
     * Sets the dailySummaries.
     *
     * @param dailySummaries the dailySummaries to set.
     */
    public void setDailySummaries(List<ProductSalesSummary> dailySummaries){
        this.dailySummaries = dailySummaries;
    }
}
