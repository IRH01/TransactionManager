package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.utils.exceptions.ApiException;
import com.irh.transaction.utils.code.ApiResponse;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.services.BranchTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link BranchTable}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/branch/branch-table")
public class ApiBranchTableController extends BaseController{

    /**
     * The {@link BranchTableService} instance.
     */
    @Autowired
    private BranchTableService branchTableService;

    /**
     * Saves the branch table.
     *
     * @param branchTable the branch table to save.
     * @return the response containing the id of the saved branch table.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody BranchTable branchTable) throws ApiException{
        try{
            Account account = getAccount();
            if(account.getRole().getLevel() != RoleLevel.HQ){
                branchTable.setBranchId(account.getBranch().getId());
            }
            branchTableService.save(branchTable);
            return new ApiResponse(branchTable.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchTableController.class.getName()
                            + "#save(BranchTable)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the branch table.
     *
     * @param branchTable the branch table to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody BranchTable branchTable) throws ApiException{
        try{
            branchTableService.update(branchTable);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchTableController.class.getName()
                            + "#update(BranchTable)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the branch table by id.
     *
     * @param id the branch table id.
     * @return the response containing the retrieved branch table.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(branchTableService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchTableController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches branch tables.
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            Account account = getAccount();
            SearchFilter filter = new SearchFilter();
            if(account.getRole().getLevel() != RoleLevel.HQ){
                filter.setBranchId(getAccount().getBranch().getId());
            }
            WebHelper.setSearchFilterParams(filter, params, getAccount());
            return new ApiResponse(branchTableService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchTableController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
