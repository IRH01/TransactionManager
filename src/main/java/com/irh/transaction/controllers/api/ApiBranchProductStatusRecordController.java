package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.services.BranchProductStatusRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * The API controller to manage {@link BranchProductStatusRecord}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/branch/product-status-record")
public class ApiBranchProductStatusRecordController extends BaseController{

    /**
     * The {@link BranchProductStatusRecordService} instance.
     */
    @Autowired
    private BranchProductStatusRecordService branchProductStatusRecordService;

    /**
     * Saves the branch product status record.
     *
     * @param record the branch product status record to save.
     * @return the response containing the id of the saved branch product status record.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody BranchProductStatusRecord record) throws ApiException{
        try{
            record.setCreatedAt(new Date());
            branchProductStatusRecordService.save(record);
            return new ApiResponse(record.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiBranchProductStatusRecordController.class.getName()
                            + "#save(BranchProductStatusRecord)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Deletes the branch product status record by id.
     *
     * @param id the branch product status record id.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ApiResponse delete(@PathVariable long id) throws ApiException{
        try{
            branchProductStatusRecordService.delete(id);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(),
                    ApiBranchProductStatusRecordController.class.getName()
                            + "#delete(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches branch product status records.
     *
     * @param params the request parameters.
     * @return the response containing result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            SearchFilter filter = new SearchFilter();
            WebHelper.setSearchFilterParams(filter, params, getAccount());
            return new ApiResponse(branchProductStatusRecordService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiProductController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
