package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.utils.exceptions.ApiException;
import com.irh.transaction.utils.code.ApiResponse;
import com.irh.transaction.model.statistic.CategorySalesDetails;
import com.irh.transaction.model.statistic.CategorySalesSummary;
import com.irh.transaction.model.statistic.ProductSalesDetails;
import com.irh.transaction.model.statistic.ProductSalesSummary;
import com.irh.transaction.services.SalesStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by iritchie on 2016/4/27 0027.
 */
@RestController
@RequestMapping(value = "/api/statistic/menu")
public class ApiMenuSalesStatisticController extends BaseController{
    @Autowired
    private SalesStatisticService salesStatisticService;

    @RequestMapping(value = {"", "product"}, method = RequestMethod.GET)
    public ApiResponse searchProduct(@RequestParam Map<String, String> params) throws ApiException{
        try{
            DateFilter filter = new DateFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            Long categoryId = params.get("categoryId") == null ? null : Long.parseLong(params.get("categoryId"));
            List<ProductSalesSummary> ProductSalesSummarys = salesStatisticService.getProductSalesSummaries(filter, categoryId);
            return new ApiResponse(ProductSalesSummarys);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMenuSalesStatisticController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ApiResponse searchCategory(@RequestParam Map<String, String> params) throws ApiException{
        try{
            DateFilter filter = new DateFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            List<CategorySalesSummary> CategorySalesSummaries = salesStatisticService.getCategorySalesSummaries(filter);
            return new ApiResponse(CategorySalesSummaries);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMenuSalesStatisticController.class.getName()
                            + "#searchByCategory(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    @RequestMapping(value = "/product/detail/{productId}", method = RequestMethod.GET)
    public ApiResponse getProductDetails(@RequestParam Map<String, String> params, @PathVariable long productId) throws ApiException{
        try{
            DateFilter filter = new DateFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            ProductSalesDetails productSalesDetails = salesStatisticService.getProductSalesDetails(filter, productId);
            return new ApiResponse(productSalesDetails);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMenuSalesStatisticController.class.getName()
                            + "#getProductDetails(Map<String, String>,long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    @RequestMapping(value = "/category/detail/{categoryId}", method = RequestMethod.GET)
    public ApiResponse getCategoryDetails(@RequestParam Map<String, String> params, @PathVariable long categoryId) throws ApiException{
        try{
            DateFilter filter = new DateFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            CategorySalesDetails categorySalesDetails = salesStatisticService.getCategorySalesDetails(filter, categoryId);
            return new ApiResponse(categorySalesDetails);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMenuSalesStatisticController.class.getName()
                            + "#getCategoryDetails(Map<String, String>,long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
