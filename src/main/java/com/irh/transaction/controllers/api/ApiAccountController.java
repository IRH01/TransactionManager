package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.LoginDTO;
import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.util.code.PermissionAuthority;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.Permission;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.services.AccountService;
import com.irh.transaction.services.BranchGroupService;
import com.irh.transaction.services.BranchService;
import com.irh.transaction.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The API controller to manage {@link Account}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/account")
public class ApiAccountController extends BaseController{

    /**
     * The {@link BranchService} instance.
     */
    @Autowired
    private BranchService branchService;

    /**
     * The {@link BranchGroupService} instance.
     */
    @Autowired
    private BranchGroupService branchGroupService;

    /**
     * The {@link AccountService} instance.
     */
    @Autowired
    private AccountService accountService;

    /**
     * The {@link RoleService} instance.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Handles the login request.
     *
     * @param loginDTO      the submitted DTO containing the login information.
     * @param bindingResult the model binding result.
     * @return the response containing value indicating if login succeeds.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@RequestBody LoginDTO loginDTO, BindingResult bindingResult)
            throws ApiException{
        try{
            if(bindingResult.hasErrors()){
                return new ApiResponse(false);
            }
            Account account = getAccount(loginDTO);
            if(account == null || !account.isEnabled()){
                return new ApiResponse(false);
            }
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            for(Permission permission : account.getRole().getPermissions()){
                authorities.add(new PermissionAuthority(permission.getCode()));
            }
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    account, loginDTO.getPassword(), authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            return new ApiResponse(true);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#login(LoginDTO, BindingResult)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Logs the current account out.
     *
     * @return the response.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ApiResponse logout(){
        SecurityContextHolder.clearContext();
        return new ApiResponse();
    }

    /**
     * Gets the managed branches and groups of the current authenticated account.
     *
     * @return the response containing the managed branches, groups and the role level of the current account.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/managed-branches-and-groups", method = RequestMethod.GET)
    public ApiResponse getManagedBranchesAndGroups() throws ApiException{
        try{
            Account account = getAccount();
            RoleLevel roleLevel = account.getRole().getLevel();
            List<Branch> branches;
            List<BranchGroup> groups;
            if(roleLevel.equals(RoleLevel.BRANCH)){
                branches = new ArrayList<>();
                groups = new ArrayList<>();
                branches.add(account.getBranch());
                groups.add(account.getBranchGroup());
            }else{
                BranchSearchFilter branchSearchFilter = new BranchSearchFilter();
                branchSearchFilter.setHqId(account.getHqId());
                if(roleLevel.equals(RoleLevel.BRANCH_GROUP)){
                    groups = new ArrayList<>();
                    groups.add(account.getBranchGroup());
                    branchSearchFilter.setGroupId(account.getBranchGroup().getId());
                }else{
                    NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
                    filter.setHqId(account.getHqId());
                    groups = branchGroupService.search(filter).getItems();
                }
                branches = branchService.search(branchSearchFilter).getItems();
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("branches", branches);
            map.put("groups", groups);
            return new ApiResponse(map);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#getManagedBranchesAndGroups()",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the managed roles of the current authenticated account.
     *
     * @return the response containing the managed roles.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/managed-roles", method = RequestMethod.GET)
    public ApiResponse getManagedRoles() throws ApiException{
        try{
            NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
            filter.setHqId(getAccount().getHqId());
            List<Role> candidates = roleService.search(filter).getItems();
            Account account = getAccount();
            RoleLevel roleLevel = account.getRole().getLevel();
            if(roleLevel.equals(RoleLevel.HQ)){
                return new ApiResponse(candidates);
            }
            List<Role> roles = new ArrayList<>();
            for(Role role : candidates){
                if((roleLevel.equals(RoleLevel.BRANCH_GROUP) &&
                        !role.getLevel().equals(RoleLevel.HQ)) ||
                        (role.getLevel().equals(RoleLevel.BRANCH))){
                    roles.add(role);
                }
            }
            return new ApiResponse(roles);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#getManagedRoles()",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Saves the account.
     *
     * @param account the account to save.
     * @return the response containing the id of the saved account.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/account/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody Account account) throws ApiException{
        try{
            account.setHqId(getAccount().getHqId());
            account.setCreatedAt(new Date());
            accountService.save(account);
            return new ApiResponse(account.getId());
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#save(Account)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Updates the account.
     *
     * @param account the account to update.
     * @return the response.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/account/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody Account account) throws ApiException{
        try{
            accountService.update(account);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#update(Account)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the account by id.
     *
     * @param id the account id.
     * @return the response containing the retrieved account.
     * @throws ApiException if any error occurs.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ApiResponse get(@PathVariable long id) throws ApiException{
        try{
            return new ApiResponse(accountService.findOne(id));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
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
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            AccountSearchFilter filter = new AccountSearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            return new ApiResponse(accountService.search(filter));
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiAccountController.class.getName()
                            + "#search(Map<String, String>)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * Gets the account by the {@link LoginDTO}.
     *
     * @param loginDTO the submitted DTO containing the login information.
     * @return the retrieved account.
     * @throws Exception if any error occurs.
     */
    private Account getAccount(LoginDTO loginDTO) throws Exception{
        Account account = accountService.findOne(
                loginDTO.getCredentialId(), loginDTO.getHqCode());
        if(account == null){
            return null;
        }
        if(account.getPassword().equals(loginDTO.getPassword())){
            return account;
        }
        return null;
    }
}
