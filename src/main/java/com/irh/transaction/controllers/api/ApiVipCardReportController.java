package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.services.VipCardReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The API controller to manage {@link VipCardReport}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/finance/vip-card")
public class ApiVipCardReportController extends BaseController{

    /**
     * The {@link VipCardReportService} instance.
     */
    @Autowired
    private VipCardReportService vipCardReportService;

    /**
     * Searches VIP card reports
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
            return new ApiResponse(vipCardReportService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiVipCardReportController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
