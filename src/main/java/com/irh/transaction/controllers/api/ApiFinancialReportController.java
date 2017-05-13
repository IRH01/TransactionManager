package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.utils.exceptions.ApiException;
import com.irh.transaction.utils.code.ApiResponse;
import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.services.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The API controller to manage {@link FinancialReport}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/finance/report")
public class ApiFinancialReportController extends BaseController{

    /**
     * The {@link FinancialReportService} instance.
     */
    @Autowired
    private FinancialReportService financialReportService;

    /**
     * Searches financial reports
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            ReportSearchFilter filter = new ReportSearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            if(params.containsKey("groupByBranch")){
                filter.setGroupByBranch(Boolean.valueOf(params.get("groupByBranch")));
            }
            return new ApiResponse(financialReportService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiFinancialReportController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
