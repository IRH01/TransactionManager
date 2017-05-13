package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import com.irh.transaction.services.SalesStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The API controller to manage statistical data of branch.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/statistic/branch")
public class ApiBranchSalesStatisticController extends BaseController{

    /**
     * The {@link SalesStatisticService} instance.
     */
    @Autowired
    private SalesStatisticService salesStatisticService;

    /**
     * Gets the statistical data.
     *
     * @return the response containing the result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse get(@RequestParam Map<String, String> params) throws ApiException{
        try{
            DateFilter filter = new DateFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(salesStatisticService.getBranchSalesSummaries(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiBranchSalesStatisticController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
