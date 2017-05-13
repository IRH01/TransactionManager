package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.CategorySearchFilter;
import com.irh.transaction.utils.exceptions.ApiException;
import com.irh.transaction.utils.code.ApiResponse;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link Category}.
 * <p>
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/product/category")
public class ApiCategoryController extends BaseController{

    /**
     * The {@link CategoryService} instance.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Saves the category.
     *
     * @param category the category to save.
     * @return the response containing the id of the saved category.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody Category category) throws ApiException{
        try{
            category.setHqId(getAccount().getHqId());
            categoryService.save(category);
            return new ApiResponse(category.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiCategoryController.class.getName()
                            + "#save(Category)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the category.
     *
     * @param category the category to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Category category) throws ApiException{
        try{
            categoryService.update(category);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiCategoryController.class.getName()
                            + "#update(Category)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the display orders of the categories.
     *
     * @param params the request parameters.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update-display-order", method = RequestMethod.POST)
    public ApiResponse updateDisplayOrder(@RequestBody Map<Long, Integer> params)
            throws ApiException{
        try{
            if(params == null || params.size() == 0){
                throw new ApiException("The params cannot be null or empty.", ApiResponse.INVALID_ENTITY_CODE);
            }
            categoryService.update(params);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiCategoryController.class.getName()
                            + "#updateDisplayOrder(Map<Long, Integer>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the category by id.
     *
     * @param id the category id.
     * @return the response containing the retrieved category.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(categoryService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiCategoryController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches categories.
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            CategorySearchFilter filter = new CategorySearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            if(params.containsKey("platform")){
                filter.setPlatform(Platform.valueOf(params.get("platform")));
            }
            return new ApiResponse(categoryService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiCategoryController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
