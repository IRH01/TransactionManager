package com.irh.transaction.dao;

import com.irh.transaction.dto.search.AccountSearchFilter;
import com.irh.transaction.model.account.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Account} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>Added the {@link AccountMapper#checkByCredentialIdAndHqId(String, long)} method to
 * check duplicate account. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface AccountMapper{

    /**
     * Saves the account.
     *
     * @param account the account to save.
     */
    @Transactional
    void save(Account account);

    /**
     * Updates the account.
     *
     * @param account the account to update.
     * @return the number of affected row.
     */
    @Transactional
    int update(Account account);

    /**
     * Finds the account by id.
     *
     * @param id the id.
     * @return the retrieved account.
     */
    Account findOne(long id);

    /**
     * Finds the account by credential id and headquarter code.
     *
     * @param credentialId the credential id.
     * @param hqCode       the headquarter code.
     * @return the retrieved account.
     */
    Account findOneByCredentialIdAndHqCode(@Param("credentialId") String credentialId,
                                           @Param("hqCode") String hqCode);

    /**
     * Checks if the account with the given credential id and headquarter id exists.
     *
     * @param credentialId the credential id.
     * @param hqId         the headquarter id.
     * @return the account id, null if not found.
     * @since 1.1
     */
    Long checkByCredentialIdAndHqId(@Param("credentialId") String credentialId,
                                    @Param("hqId") long hqId);

    /**
     * Searches accounts.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Account> search(@Param("filter") AccountSearchFilter filter);

    /**
     * Counts the number of accounts that match the filter.
     *
     * @param filter the filter.
     * @return the number of account that match the filter.
     */
    long count(@Param("filter") AccountSearchFilter filter);

    /**
     * @param hqId
     * @param branchId
     * @param hqCode
     * @return
     */
    List<Account> findAll(@Param("hqId") Integer hqId,
                          @Param("branchId") Integer branchId,
                          @Param("hqCode") String hqCode);
}
