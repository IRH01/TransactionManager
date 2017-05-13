package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.services.PermissionService;
import com.irh.transaction.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The API controller to manage {@link Role}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping("/api/account/role")
public class ApiRoleController extends BaseController{

    /**
     * The {@link RoleService} instance.
     */
    @Autowired
    private RoleService roleService;

    /**
     * The {@link PermissionService} instance.
     */
    @Autowired
    private PermissionService permissionService;

    /**
     * Gets all permissions.
     *
     * @return the response containing the list of permissions.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ApiResponse getPermissions() throws ApiException{
        try{
            return new ApiResponse(permissionService.findAll());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiRoleController.class.getName()
                            + "#getPermissions()",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Saves the role.
     *
     * @param role the role to save.
     * @return the response containing the id of the saved role.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody Role role) throws ApiException{
        try{
            role.setHqId(getAccount().getHqId());
            roleService.save(role);
            return new ApiResponse(role.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiRoleController.class.getName()
                            + "#save(Role)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the role.
     *
     * @param role the role to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Role role) throws ApiException{
        try{
            roleService.update(role);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiRoleController.class.getName()
                            + "#update(Role)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the branch by id.
     *
     * @param id the role id.
     * @return the response containing the retrieved branch.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(roleService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiRoleController.class.getName()
                            + "#get(long)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets all roles of the current headquarter.
     *
     * @param params the request parameters.
     * @return the response containing the result.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(roleService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiRoleController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }
}
