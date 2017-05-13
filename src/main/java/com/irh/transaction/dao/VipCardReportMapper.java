package com.irh.transaction.dao;

import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link VipCardReport} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public interface VipCardReportMapper{

    /**
     * Searches the VIP card reports by filter.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<VipCardReport> search(@Param("filter") ReportSearchFilter filter);

    /**
     * Counts the number of VIP card reports that match the filter.
     *
     * @param filter the filter.
     * @return the number of VIP card reports that match the filter.
     */
    long count(@Param("filter") ReportSearchFilter filter);

    /**
     * Gets the total summary report.
     *
     * @param filter the filter.
     * @return the report containing total summary.
     */
    VipCardReport getTotal(@Param("filter") ReportSearchFilter filter);
}
