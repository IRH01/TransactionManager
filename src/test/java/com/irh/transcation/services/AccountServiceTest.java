package com.irh.transcation.services;

import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link AccountService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class AccountServiceTest extends BaseTest{

    /**
     * The {@link AccountService} instance to test.
     */
    private AccountService service;

    /**
     * Asserts the account is correct.
     *
     * @param expectedId the expected account id.
     * @param account    the actual account.
     */
    private static void assertAccount(long expectedId, Account account) throws CoreServiceException{
        String expectedIdStr = String.valueOf(expectedId);
        assertEquals(TestHelper.HQ_ID, account.getHqId());
        assertEquals(getMD5(expectedIdStr), account.getPassword());
        assertEquals(expectedIdStr, account.getCredentialId());
        assertEquals("account" + expectedIdStr, account.getFullname());
        assertEquals(expectedIdStr, account.getMobile());

        long areaId = (long) Math.rint((expectedId + 1) / 2);
        assertEquals(expectedId, account.getBranch().getId());
        assertEquals("branch" + expectedIdStr, account.getBranch().getName());
        assertEquals(areaId, account.getBranchGroup().getId());
        assertEquals("area" + areaId, account.getBranchGroup().getName());
        Role role = TestHelper.Context.getBean(RoleService.class).findOne(
                account.getRole().getId());
        assertNotNull(role);
        assertEquals(role.getName(), account.getRole().getName());
        if(account.getRole().getPermissions().size() > 0){
            assertEquals(role.getPermissions().size(), account.getRole().getPermissions().size());
        }
        assertTrue(account.isEnabled());
    }

    /**
     * Creates an account for test.
     *
     * @param id the id.
     * @return the created account.
     */
    private static Account createAccount(long id){
        Integer areaId = (int) Math.rint((id + 1) / 2);
        String idStr = String.valueOf(id);
        Account account = new Account();
        account.setCredentialId(idStr);
        account.setFullname("account" + idStr);
        account.setPassword(getMD5(idStr));
        account.setMobile(idStr);
        account.setHqId(1);
        Role role = new Role();
        role.setId(id);
        account.setRole(role);
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setId(areaId);
        account.setBranchGroup(branchGroup);
        Branch branch = new Branch();
        branch.setId(id);
        account.setBranch(branch);
        account.setEnabled(true);
        account.setCreatedAt(new Date());
        return account;
    }

    /**
     * The private method is get md5 string.
     */
    private static String getMD5(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashText.length() < 32){
                hashText = "0" + hashText;
            }
            return hashText;
        }catch(NoSuchAlgorithmException ex){
            return null;
        }
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(AccountService.class);
    }

    /**
     * Tests the {@link AccountService#save(Account)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Account account = createAccount(6);
        service.save(account);

        Account actual = service.findOne("6", "1");
        assertAccount(6, actual);
        assertEquals(1, actual.getRole().getPermissions().size());
    }

    /**
     * Failure test for the {@link AccountService#save(Account)} method, with an account with the same credential id
     * already exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        service.save(createAccount(1));
        Assert.fail();
    }

    /**
     * Failure test for the {@link AccountService#save(Account)} method, with an account is null, {@link
     * IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveIsNull1() throws CoreServiceException{
        service.save(null);
        Assert.fail();
    }

    /**
     * Failure test for {@link AccountService#save(Account)} method, with an account.role is null, {@link
     * IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveIsNull2() throws CoreServiceException{
        Account account = createAccount(6);
        account.setRole(null);
        service.save(account);
        Assert.fail();
    }

    /**
     * Failure test for {@link AccountService#save(Account)} method, with an account.role.level is branch and
     * account.branch is null, {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveIsNull3() throws CoreServiceException{
        Account account = createAccount(6);
        account.getRole().setLevel(RoleLevel.BRANCH);
        account.setBranch(null);
        service.save(account);
        Assert.fail();
    }

    /**
     * Failure test for {@link AccountService#save(Account)} method, with an account.role.level is branch_group and
     * account.branchGroup is null, {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveIsNull4() throws CoreServiceException{
        Account account = createAccount(6);
        account.getRole().setLevel(RoleLevel.BRANCH_GROUP);
        account.setBranchGroup(null);
        service.save(account);
        Assert.fail();
    }

    /**
     * Tests the {@link AccountService#update(Account)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        Account account = service.findOne(1);
        account.setPassword(getMD5("6"));
        account.setFullname("account6");
        account.setMobile("6");
        account.getBranchGroup().setId(3);
        account.getBranch().setId(6);
        account.getRole().setId(3);
        account.setEnabled(false);
        service.update(account);

        Account actual = service.findOne(1);
        assertFalse(account.isEnabled());
        assertEquals("1", actual.getCredentialId());
        actual.setCredentialId("6");
        actual.setEnabled(true);
        assertAccount(6, actual);
    }

    /**
     * Failure test for {@link AccountService#update(Account)} method, with an account is not exist in data db, {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityNotFoundException.class)
    public void updateEntityNotFoundException() throws CoreServiceException{
        Account account = createAccount(6);
        service.update(account);
        fail();
    }

    /**
     * Failure test for {@link AccountService#update(Account)} method, with an acccount is null, {@link
     * IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void updateIsNull1() throws CoreServiceException{
        service.update(null);
        fail();
    }

    /**
     * Failure test for {@link AccountService#update(Account)} method, with an account.role is null, {@link
     * IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void updateIsNull2() throws CoreServiceException{
        Account account = createAccount(1);
        account.setRole(null);
        service.update(account);
        fail();
    }

    /**
     * Failure test for {@link AccountService#update(Account)} method, with an account.role.level is branch and
     * account.branch is null, {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void updateIsNull3() throws CoreServiceException{
        Account account = createAccount(1);
        account.getRole().setLevel(RoleLevel.BRANCH);
        account.setBranch(null);
        service.update(account);
        fail();
    }

    /**
     * Failure test for {@link AccountService#update(Account)} method, with an account.role.level is branch group and
     * account.branchGroup is null, {@link IllegalArgumentException} is expected;
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void updateIsNull4() throws CoreServiceException{
        Account account = createAccount(1);
        account.getRole().setLevel(RoleLevel.BRANCH_GROUP);
        account.setBranchGroup(null);
        service.update(account);
        fail();
    }

    /**
     * Tests the {@link AccountService#findOne(String, String)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOneByCredentialIdAndHqId() throws CoreServiceException{
        Account account = service.findOne("3", TestHelper.HQ_CODE);
        assertAccount(3, account);
        assertNotNull(account.getHq());
        assertEquals("1", account.getHq().getName());
        assertEquals("logo1", account.getHq().getLogo());
        assertEquals(TestHelper.HQ_CODE, account.getHq().getCode());
    }

    /**
     * Tests the {@link AccountService#findOne(String, String)} method, expecting no account will be found.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOneNotFound() throws CoreServiceException{
        assertNull(service.findOne("bad", TestHelper.HQ_CODE));
    }

    /**
     * Tests the {@link AccountService#search(AccountSearchFilter)} method, searching by fullname with pagination and
     * sorting.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchByFullname() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setName("account");
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<Account> result = service.search(filter);
        assertAccount(3, result.getItems().get(0));
        assertAccount(2, result.getItems().get(1));
        assertEquals(2, result.getItems().size());
        assertEquals(2, result.getPage().intValue());
        assertEquals(5, result.getCount());
        assertEquals(filter.getPage(), result.getPage());
        assertEquals(filter.getSize(), result.getSize());
    }

    /**
     * Tests the {@link AccountService#search(AccountSearchFilter)} method, searching by permission.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchByPermission() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setPermissionCode("data");
        SearchResult<Account> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertNull(result.getPage());
        assertAccount(1, result.getItems().get(0));
    }

    /**
     * Failure test for {@link AccountService#search(AccountSearchFilter)} method, with accountSearchFilter is null,
     * {@link IllegalArgumentException} is expected;
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchIsNull1() throws CoreServiceException{
        service.search(null);
        fail();
    }

    /**
     * Failure test for {@link AccountService#search(AccountSearchFilter)} method, with filter.hqId is null, {@link
     * IllegalArgumentException} is excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchIsNull2() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(null);
        filter.setName("account");
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        service.search(filter);
        fail();
    }

    /**
     * Failure test for {@link AccountService#search(AccountSearchFilter)} method, with an filter.page is non-positive.
     * {@link IllegalArgumentException} expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchIsNull3() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setPage(-1);
        filter.setName("account");
        filter.setSize(2);
        filter.setSortBy("id");
        service.search(filter);
        fail();
    }

    /**
     * Failure test for {@link AccountService#search(AccountSearchFilter)} method, with an filter.size is non-positive,
     * {@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchIsNull4() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setSize(-1);
        filter.setName("account");
        filter.setPage(2);
        filter.setSortBy("id");
        service.search(filter);
        fail();
    }

    /**
     * Failure test for {@link AccountService#search(AccountSearchFilter)} method, with an filter.size is null, {@link
     * IllegalArgumentException} is excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void searchIsNull5() throws CoreServiceException{
        AccountSearchFilter filter = new AccountSearchFilter();
        filter.setHqId(1L);
        filter.setPage(1);
        filter.setSize(null);
        filter.setName("account");
        filter.setSortBy("id");
        service.search(filter);
        fail();
    }
}
