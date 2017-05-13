package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.exceptions.web.ApiException;
import com.irh.transaction.exceptions.web.ApiResponse;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.services.BranchGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link BranchGroup}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/branch/branch-group")
public class ApiBranchGroupController extends BaseController{

    /**
     * The {@link BranchGroupService} instance.
     */
    @Autowired
    private BranchGroupService branchGroupService;

    /**
     * Saves the branch group.
     *
     * @param branchGroup the branch group to save.
     * @return the response containing the id of the saved branch group.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody BranchGroup branchGroup) throws ApiException{
        try{
            branchGroup.setHqId(getAccount().getHqId());
            Account account = getAccount();
            if(account.getRole().getLevel().equals(RoleLevel.BRANCH)){
                throw new ApiException("The branch level not access denied.", ApiResponse.ACCESS_DENIED_MSG);
            }
            branchGroupService.save(branchGroup);
            return new ApiResponse(branchGroup.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchGroupController.class.getName()
                            + "#save(BranchGroup)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the branch group.
     *
     * @param branchGroup the branch group to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody BranchGroup branchGroup) throws ApiException{
        try{
            Account account = getAccount();
            if(account.getRole().getLevel().equals(RoleLevel.BRANCH)){
                throw new ApiException("The branch level not access denied.", ApiResponse.ACCESS_DENIED_MSG);
            }
            branchGroupService.update(branchGroup);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchGroupController.class.getName()
                            + "#update(BranchGroup)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the branch group by id.
     *
     * @param id the branch group id.
     * @return the response containing the retrieved branch group.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(branchGroupService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchGroupController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Searches branch groups.
     *
     * @param params the request parameters.
     * @return the response containing the search result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            Account account = getAccount();
            NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
            if(account.getRole().getLevel().equals(RoleLevel.BRANCH)){
                new ApiResponse(ApiResponse.ACCESS_DENIED_CODE, "The branch no access denied!");
            }
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(branchGroupService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiBranchGroupController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
