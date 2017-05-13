package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.utils.exceptions.ApiException;
import com.irh.transaction.utils.code.ApiResponse;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.services.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link ProductDiscount}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/marketing/discount")
public class ApiProductDiscountController extends BaseController{

    /**
     * The {@link ProductDiscountService} instance.
     */
    @Autowired
    private ProductDiscountService productDiscountService;

    /**
     * Saves the product discount.
     *
     * @param productDiscount the product discount to save.
     * @return the response containing the id of the saved product discount.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody ProductDiscount productDiscount) throws ApiException{
        try{
            productDiscount.setHqId(getAccount().getHqId());
            productDiscountService.save(productDiscount);
            return new ApiResponse(productDiscount.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductDiscountController.class.getName()
                            + "#save(ProductDiscount)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the product discount.
     *
     * @param productDiscount the product discount to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody ProductDiscount productDiscount) throws ApiException{
        try{
            productDiscountService.update(productDiscount);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductDiscountController.class.getName()
                            + "#update(ProductDiscount)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the product discount by id.
     *
     * @param id the product discount id.
     * @return the response containing the retrieved product discount.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(productDiscountService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductDiscountController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches product discounts.
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(productDiscountService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductDiscountController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
