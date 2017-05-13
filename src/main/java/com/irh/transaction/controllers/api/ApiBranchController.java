package com.irh.transaction.controllers.api;


import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link Branch}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/branch/branch")
public class ApiBranchController extends BaseController{

    /**
     * The {@link BranchService} instance.
     */
    @Autowired
    private BranchService branchService;

    /**
     * Saves the branch.
     *
     * @param branch the branch to save.
     * @return the response containing the id of the saved branch.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody Branch branch) throws ApiException{
        try{
            Account account = getAccount();
            if(!account.getRole().getLevel().equals(RoleLevel.HQ) && branch.getGroupId() != account.getBranchGroup().getId()){
                throw new ApiException(ApiResponse.INVALID_ENTITY_CODE,
                        "The current account has no permission to create branch of another branch group");
            }
            branch.setHqId(account.getHqId());
            branchService.save(branch);
            return new ApiResponse(branch.getId());
        }catch(ApiException ex){
            throw WebHelper.logException(getLogger(), ApiBranchController.class.getName()
                    + "#save(Branch)", ex);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchController.class.getName()
                            + "#save(Branch)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the branch.
     *
     * @param branch the branch to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Branch branch) throws ApiException{
        try{
            branchService.update(branch);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchController.class.getName()
                            + "#update(Branch)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the branch by id.
     *
     * @param id the branch id.
     * @return the response containing the retrieved branch.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(branchService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches branches.
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            Account account = getAccount();
            BranchSearchFilter filter = new BranchSearchFilter();
            if(account.getRole().getLevel() != RoleLevel.HQ){
                filter.setBranchId(getAccount().getBranch().getId());
            }
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(branchService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
