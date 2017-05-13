package com.irh.transaction.services.impl;

import com.irh.transaction.dao.AccountMapper;
import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link AccountService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class AccountServiceImpl implements AccountService{

    /**
     * The mapper to manage {@link Account} in persistence.
     */
    @Autowired
    private AccountMapper mapper;

    /**
     * Saves the account.
     *
     * @param account
     *         the account to save.
     *
     * @throws IllegalArgumentException
     *         if the account is null; if the <em>account.role</em> is null; if the <em>account.role.level</em> is
     *         <em>RoleLevel.BRANCH</em> and either the <em>account.branch</em> or the <em>account.branchGroup</em> is
     *         null; if the <em>account.role.level</em> is <em>RoleLevel.BRANCH_GROUP</em> and the
     *         <em>account.branchGroup</em> is null.
     * @throws EntityExistsException
     *         if an account of the headquarter with the same credential id already exists.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Account account) throws CoreServiceException{
        checkAccount(account);
        try {
            if (mapper.checkByCredentialIdAndHqId(
                    account.getCredentialId(), account.getHqId()) != null) {
                throw new EntityExistsException("The credential Id has already been used.");
            }
            mapper.save(account);
        } catch (EntityExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * The account to update.
     *
     * @param account
     *         the account.
     *
     * @throws IllegalArgumentException
     *         if the account is null; if the <em>account.role</em> is null; if the <em>account.role.level</em> is
     *         <em>RoleLevel.BRANCH</em> and either the <em>account.branch</em> or the <em>account.branchGroup</em> is
     *         null; if the <em>account.role.level</em> is <em>RoleLevel.BRANCH_GROUP</em> and the
     *         <em>account.branchGroup</em> is null.
     * @throws EntityNotFoundException
     *         if the account cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Account account) throws CoreServiceException{
        checkAccount(account);
        try {
            if (mapper.update(account) == 0) {
                throw new EntityNotFoundException("The entity cannot be found.");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the employee account by the given credential id and headquarter code.
     *
     * @param credentialId
     *         the credential id.
     * @param hqCode
     *         the headquarter code.
     *
     * @return the retrieved account.
     *
     * @throws IllegalArgumentException
     *         if any argument is null or empty.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public Account findOne(String credentialId, String hqCode) throws CoreServiceException{
        ServiceHelper.checkString(credentialId, "credentialId");
        ServiceHelper.checkString(hqCode, "hqCode");
        try {
            return mapper.findOneByCredentialIdAndHqCode(credentialId, hqCode);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the employee account by id.
     *
     * @param id
     *         the account id.
     *
     * @return the retrieved account.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public Account findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches accounts.
     *
     * @param filter
     *         the search filter.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *         or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<Account> search(AccountSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<Account> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    private static void checkAccount(Account account) {
        ServiceHelper.checkNotNull(account, "account");
        ServiceHelper.checkNotNull(account.getRole(), "account.role");
        RoleLevel roleLevel = account.getRole().getLevel();
        if (RoleLevel.BRANCH.equals(roleLevel) &&
                (account.getBranch() == null || account.getBranchGroup() == null)) {
            throw new IllegalArgumentException(
                    "Neither the branch nor branch group can be null for account of BRANCH level.");
        }
        if (RoleLevel.BRANCH_GROUP.equals(roleLevel) && account.getBranchGroup() == null) {
            throw new IllegalArgumentException(
                    "The branch group cannot be null for account of BRANCH_GROUP level.");
        }
    }

    /**
     *
     * @param hqId  the headquarter id
     * @param branchId  the branch id
     * @param hqCode the headquarter code.
     * @return
     */
    @Override
    public List<Account> findAll(Integer hqId, Integer branchId, String hqCode) throws CoreServiceException{
        try {
            return mapper.findAll(hqId,branchId,hqCode);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
