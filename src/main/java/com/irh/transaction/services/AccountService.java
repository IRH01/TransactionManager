package com.irh.transaction.services;

import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Account;

import java.util.List;

/**
 * Defines a contract for managing {@link Account}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface AccountService{

    /**
     * Saves the account.
     *
     * @param account the account to save.
     * @throws IllegalArgumentException if the account is null; if the <em>account.role</em> is null; if the <em>account.role.level</em> is
     *                                  <em>RoleLevel.BRANCH</em> and either the <em>account.branch</em> or the <em>account.branchGroup</em> is
     *                                  null; if the <em>account.role.level</em> is <em>RoleLevel.BRANCH_GROUP</em> and the
     *                                  <em>account.branchGroup</em> is null.
     * @throws EntityExistsException    if an account of the headquarter with the same credential id already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(Account account) throws CoreServiceException;

    /**
     * The account to update.
     *
     * @param account the account.
     * @throws IllegalArgumentException if the account is null; if the <em>account.role</em> is null; if the <em>account.role.level</em> is
     *                                  <em>RoleLevel.BRANCH</em> and either the <em>account.branch</em> or the <em>account.branchGroup</em> is
     *                                  null; if the <em>account.role.level</em> is <em>RoleLevel.BRANCH_GROUP</em> and the
     *                                  <em>account.branchGroup</em> is null.
     * @throws EntityNotFoundException  if the account cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    void update(Account account) throws CoreServiceException;

    /**
     * Finds the employee account by id.
     *
     * @param id the account id.
     * @return the retrieved account.
     * @throws CoreServiceException if any error occurs.
     */
    Account findOne(long id) throws CoreServiceException;

    /**
     * Finds the employee account by the given credential id and headquarter code.
     *
     * @param credentialId the credential id.
     * @param hqCode       the headquarter code.
     * @return the retrieved account.
     * @throws IllegalArgumentException if any argument is null or empty.
     * @throws CoreServiceException     if any other error occurs.
     */
    Account findOne(String credentialId, String hqCode) throws CoreServiceException;

    /**
     * Searches accounts.
     *
     * @param filter the search filter.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Account> search(AccountSearchFilter filter) throws CoreServiceException;

    /**
     * Get all accounts for the branch by the given info.
     *
     * @param hqId     the headquarter id
     * @param branchId the branch id
     * @param hqCode   the headquarter code.
     * @return
     */
    List<Account> findAll(Integer hqId, Integer branchId, String hqCode) throws CoreServiceException;
}
