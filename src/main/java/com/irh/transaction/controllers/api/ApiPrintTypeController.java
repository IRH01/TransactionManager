package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.services.PrintTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link PrintType}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/branch/print-type")
public class ApiPrintTypeController extends BaseController{

    /**
     * The {@link PrintTypeService} instance.
     */
    @Autowired
    private PrintTypeService printTypeService;

    /**
     * Saves the print type.
     *
     * @param printType the print type to save.
     * @return the response containing the id of the saved print type.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody PrintType printType) throws ApiException{
        try{
            printTypeService.save(printType);
            return new ApiResponse(printType.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiPrintTypeController.class.getName()
                            + "#save(PrintType)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the print type.
     *
     * @param printType the print type to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody PrintType printType) throws ApiException{
        try{
            printTypeService.update(printType);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiPrintTypeController.class.getName()
                            + "#update(PrintType)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the print type by id.
     *
     * @param id the print type id.
     * @return the response containing the retrieved print type.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(printTypeService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiPrintTypeController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches print types.
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
            return new ApiResponse(printTypeService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiPrintTypeController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
