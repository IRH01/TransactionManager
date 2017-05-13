package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.services.ProductOptionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link ProductOptionGroup}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/product/product-option-group")
public class ApiProductOptionGroupController extends BaseController{

    /**
     * The {@link ProductOptionGroupService} instance.
     */
    @Autowired
    private ProductOptionGroupService productOptionGroupService;

    /**
     * Saves the product option group.
     *
     * @param optionGroup the product option group to save.
     * @return the response containing the id of the saved product option group.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody ProductOptionGroup optionGroup) throws ApiException{
        try{
            optionGroup.setHqId(getAccount().getHqId());
            productOptionGroupService.save(optionGroup);
            return new ApiResponse(optionGroup.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiProductOptionGroupController.class.getName() + "#save(ProductOptionGroup)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Update the product option group.
     *
     * @param optionGroup the product option group to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody ProductOptionGroup optionGroup) throws ApiException{
        try{
            productOptionGroupService.update(optionGroup);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiProductOptionGroupController.class.getName() + "#update(ProductOptionGroup)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the product option group by id.
     *
     * @param id the product option group id.
     * @return the response containing the retrieved product option group.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(productOptionGroupService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiProductOptionGroupController.class.getName() + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches product option groups.
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
            return new ApiResponse(productOptionGroupService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiProductOptionGroupController.class.getName() + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
