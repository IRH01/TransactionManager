package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.Permission;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.services.AccountService;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AccountServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@Transactional
public class AccountServiceTest extends BaseCase{

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void after(){
    }

    /**
     * Method: save(Account account)
     */
    @Test
    public void testSave() throws CoreServiceException{
        Account account = getAccount();
        accountService.save(account);
        Account result = accountService.findOne(account.getId());
        Assert.assertNotNull(result);
        assertAccount(account, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithAccountIsNull() throws CoreServiceException{
        accountService.save(null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithAccountRoleIsNull() throws CoreServiceException{
        Account account = getAccount();
        account.setRole(null);
        accountService.save(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithAccountRoleLevelIsBranchAndAccountBranchIsNUll() throws CoreServiceException{
        Account account = getAccount();
        account.getRole().setLevel(RoleLevel.BRANCH);
        account.setBranch(null);
        accountService.save(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithAccountRoleLevelIsBranchGroupAndAccountBranchGroupIsNull() throws CoreServiceException{
        Account account = getAccount();
        account.getRole().setLevel(RoleLevel.BRANCH_GROUP);
        account.setBranchGroup(null);
        accountService.save(account);
        Assert.fail();
    }

    @Test(expected = EntityExistsException.class)
    public void testSaveCredentialIdHasAlreadyBeenUsed() throws CoreServiceException{
        Account account = getAccount();
        account.setCredentialId("10");
        accountService.save(account);
        Assert.fail();
    }

    /**
     * Method: update(Account account)
     */
    @Test
    public void testUpdate() throws CoreServiceException{
        Account account = getAccount();
        account.setId(1);
        account.setFullname("new account");
        accountService.update(account);
        Account result = accountService.findOne(account.getId());
        Assert.assertEquals(account.getId(), result.getId());
        Assert.assertEquals(account.getFullname(), result.getFullname());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithAccountIsNull() throws CoreServiceException{
        accountService.update(null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithAccountRoleIsNull() throws CoreServiceException{
        Account account = getAccount();
        account.setId(1);
        account.setRole(null);
        accountService.update(account);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithAccountRoleLevelIsBranchAndAccountBranchIsNull() throws CoreServiceException{
        Account account = getAccount();
        account.setId(1);
        account.getRole().setLevel(RoleLevel.BRANCH);
        account.setBranch(null);
        accountService.update(account);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithAccountRoleLevelIsBranchGroupAndAccountBranchGroupIsNull() throws CoreServiceException{
        Account account = getAccount();
        account.setId(1);
        account.getRole().setLevel(RoleLevel.BRANCH_GROUP);
        account.setBranchGroup(null);
        accountService.update(account);
        Assert.fail();
    }

    /**
     * Method: findOne(String credentialId, String hqCode)
     */
    @Test
    public void testFindOneForCredentialIdHqCode() throws CoreServiceException{
        Account result = accountService.findOne("10", "1");
        assertAccount(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindOneForCaredentialIdHqCodeWithCredentialIdIsNull() throws CoreServiceException{
        accountService.findOne(null, "1");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindOneForCaredentialIdHqCodeWithHqCodeIsNull() throws CoreServiceException{
        accountService.findOne("10", null);
        Assert.fail();
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOneId() throws Exception{
        Account result = accountService.findOne(1);
        assertAccount(result);
    }

    /**
     * Method: search(AccountSearchFilter filter)
     */
    @Test
    public void testSearch() throws CoreServiceException{
        AccountSearchFilter filter = getAccountSearchFilter();
        SearchResult<Account> result = accountService.search(filter);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals(null, result.getPage());
        Assert.assertEquals(null, result.getSize());
        List<Account> list = result.getItems();
        for(Account item : list){
            if(item.getId() == 1){
                assertAccount(item);
            }
        }
    }

    @Test
    public void testSearch1() throws CoreServiceException{
        AccountSearchFilter filter = getAccountSearchFilter();
        filter.setPage(1);
        filter.setSize(1);
        SearchResult<Account> result = accountService.search(filter);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals(new Integer(1), result.getPage());
        Assert.assertEquals(new Integer(1), result.getSize());
        List<Account> list = result.getItems();
        for(Account item : list){
            if(item.getId() == 1){
                assertAccount(item);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWithFilterIsNull() throws CoreServiceException{
        accountService.search(null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWithFilterHqIdIsNull() throws CoreServiceException{
        AccountSearchFilter filter = getAccountSearchFilter();
        filter.setHqId(null);
        accountService.search(filter);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWithFilterPageIsNotPositive() throws CoreServiceException{
        AccountSearchFilter filter = getAccountSearchFilter();
        filter.setPage(-1);
        accountService.search(filter);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWithFilterSizeIsNotPositive() throws CoreServiceException{
        AccountSearchFilter filter = getAccountSearchFilter();
        filter.setPage(1);
        filter.setSize(-1);
        accountService.search(filter);
        Assert.fail();
    }

    private void assertAccount(Account account, Account result){
        Assert.assertEquals(account.getId(), result.getId());
        Assert.assertEquals(account.getFullname(), result.getFullname());
        Assert.assertEquals(account.getHqId(), result.getHqId());
        Assert.assertEquals(account.getPassword(), result.getPassword());
        Assert.assertEquals(account.getCredentialId(), result.getCredentialId());
        Assert.assertEquals(account.getMobile(), result.getMobile());
        Assert.assertEquals(account.getBranch().getId(), result.getBranch().getId());
        Assert.assertEquals(account.getBranch().getName(), result.getBranch().getName());
        Assert.assertEquals(account.getBranchGroup().getId(), result.getBranchGroup().getId());
        Assert.assertEquals(account.getRole().getId(), result.getRole().getId());
        Assert.assertEquals(account.getRole().getLevel(), result.getRole().getLevel());
    }

    private void assertAccount(Account result){
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals(1, result.getHqId());
        Assert.assertEquals("10", result.getCredentialId());
        Assert.assertEquals("123", result.getPassword());
        Assert.assertEquals("account1", result.getFullname());
        Assert.assertEquals("1386569552", result.getMobile());
        Assert.assertEquals(true, result.isEnabled());
        Assert.assertEquals(1, result.getRole().getId());
        Assert.assertEquals(1, result.getBranchGroup().getId());
        Assert.assertEquals(1, result.getBranch().getId());
    }

    private Account getAccount(){
        Branch branch = getBranch();
        BranchGroup branchGroup = getBranchGroup();
        Headquarter headquarter = getHeadquarter();
        Role role = getRole();
        Account account = getAccount(branch, branchGroup, headquarter, role);
        return account;
    }

    private Account getAccount(Branch branch, BranchGroup branchGroup, Headquarter headquarter, Role role){
        Account account = new Account();
        account.setBranch(branch);
        account.setEnabled(true);
        account.setBranchGroup(branchGroup);
        account.setHqId(1);
        account.setHq(headquarter);
        account.setId(1);
        account.setCreatedAt(new Date());
        account.setCredentialId("1");
        account.setFullname("account1");
        account.setMobile("18676853525");
        account.setPassword("1");
        account.setRole(role);
        return account;
    }

    private AccountSearchFilter getAccountSearchFilter(){
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setBranchId(1L);
        return filter;
    }

    private Role getRole(){
        Role role = new Role();
        role.setLevel(RoleLevel.HQ);
        role.setName("总部");
        role.setId(1);
        role.setHqId(1);
        List<Permission> permissionList = new ArrayList<>();
        Permission permission = getPermission();
        permissionList.add(permission);
        role.setPermissions(permissionList);
        return role;
    }

    private Permission getPermission(){
        Permission permission = new Permission();
        permission.setId(1);
        permission.setName("permission1");
        permission.setCategory("会计");
        permission.setCode("kuaiji");
        return permission;
    }

    private Headquarter getHeadquarter(){
        Headquarter headquarter = new Headquarter();
        headquarter.setId(1);
        headquarter.setCode("1");
        headquarter.setName("总部");
        headquarter.setEnabled(true);
        headquarter.setLogo("logo");
        return headquarter;
    }

    private BranchGroup getBranchGroup(){
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setId(1);
        branchGroup.setHqId(1);
        branchGroup.setName("branch_group1");
        branchGroup.setEnabled(true);
        return branchGroup;
    }

    private Branch getBranch(){
        Branch branch = new Branch();
        branch.setId(1);
        branch.setHqId(1);
        branch.setName("岳麓山店");
        branch.setGroupId(1);
        branch.setProvince("湖南");
        branch.setCity("长沙");
        branch.setDistrict("岳麓区");
        branch.setAddress("枫林路133号");
        branch.setLatitude(11D);
        branch.setPhone("18676823505");
        branch.setEnabled(true);
        return branch;
    }
}
