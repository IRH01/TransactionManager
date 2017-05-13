package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.ProductSearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link Product}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/product/product")
public class ApiProductController extends BaseController{

    /**
     * The {@link ProductService} instance.
     */
    @Autowired
    private ProductService productService;

    /**
     * Saves the product.
     *
     * @param product the product to save.
     * @return the response containing the id of the saved product.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody Product product) throws ApiException{
        try{
            product.setHqId(getAccount().getHqId());
            productService.save(product);
            return new ApiResponse(product.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductController.class.getName()
                            + "#save(Product)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the product.
     *
     * @param product the product to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Product product) throws ApiException{
        try{
            productService.update(product);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductController.class.getName()
                            + "#update(Product)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the product by id.
     *
     * @param id the product id.
     * @return the response containing the retrieved product.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(productService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches products.
     *
     * @param params the request parameters.
     * @return the response containing result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            ProductSearchFilter filter = new ProductSearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            if(params.containsKey("status")){
                filter.setStatus(ProductStatus.valueOf(params.get("status")));
            }
            return new ApiResponse(productService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
