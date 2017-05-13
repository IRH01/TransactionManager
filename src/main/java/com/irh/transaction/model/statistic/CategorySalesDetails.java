package com.irh.transaction.model.statistic;

import java.util.List;

/**
 * Represents the details of statistical data for a product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author 任欢，Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class CategorySalesDetails{

    /**
     * The category id.
     */
    private long categoryId;

    /**
     * The statistical details for service types.
     */
    private List<StatisticDetailItem> serviceTypeDetails;

    /**
     * The statistical details for products.
     */
    private List<StatisticDetailItem> optionDetails;

    /**
     * The list of sales summary grouped by date.
     */
    private List<CategorySalesSummary> dailySummaries;

    public long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(long categoryId){
        this.categoryId = categoryId;
    }

    public List<StatisticDetailItem> getServiceTypeDetails(){
        return serviceTypeDetails;
    }

    public void setServiceTypeDetails(List<StatisticDetailItem> serviceTypeDetails){
        this.serviceTypeDetails = serviceTypeDetails;
    }

    public List<StatisticDetailItem> getOptionDetails(){
        return optionDetails;
    }

    public void setOptionDetails(List<StatisticDetailItem> optionDetails){
        this.optionDetails = optionDetails;
    }

    public List<CategorySalesSummary> getDailySummaries(){
        return dailySummaries;
    }

    public void setDailySummaries(List<CategorySalesSummary> dailySummaries){
        this.dailySummaries = dailySummaries;
    }
}
