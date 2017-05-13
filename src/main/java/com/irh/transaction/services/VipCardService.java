package com.irh.transaction.services;

import com.irh.transaction.services.EntityNotFoundException;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.dto.search.VipCardTransactionSearchFilter;

/**
 * Defines a contract for managing {@link VipCard}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> Added the {@link com.irh.transcation.services.VipCardService#saveTransaction(VipCardTransaction)} ,
 * {@link com.irh.transcation.services.VipCardService#searchTransactions(VipCardTransactionSearchFilter)} methods to manage {@link
 * VipCardTransaction}. </li> <li> Added the {@link com.irh.transcation.services.VipCardService#replace(VipCard, VipCard)}, {@link
 * com.irh.transcation.services.VipCardService#updateStatus(long, VipCardStatus)} methods to manage the VIP card status. </li> </ol>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface VipCardService{

    /**
     * Saves the VIP card.
     *
     * @param vipCard the VIP card to save.
     * @throws IllegalArgumentException <p> if the argument is null; </p> <p> if the <em>vipCard.number</em> is null or empty; </p> <p> if the
     *                                  <em>vipCard.name</em> is null or empty; </p> <p> if the <em>vipCard.gender</em> is null or empty; </p>
     *                                  <p> if the <em>vipCard.balance</em> is null or negative; </p> <p> if the <em>vipCard.invoiceQuota</em> is
     *                                  null or negative. </p>
     * @throws EntityExistsException    if a VIP card of the headquarter with the same card number already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    void save(VipCard vipCard) throws CoreServiceException;

    /**
     * Saves the VIP card transaction.
     *
     * @param transaction the VIP card transaction to save.
     * @throws IllegalArgumentException                          <p> if the argument is null; </p> <p> if the <em>transaction.number</em> is null or empty for INVOICE
     *                                                           transaction or if the <em>transaction.number</em> is not null for non-invoice transaction; </p> <p> if
     *                                                           the <em>transaction.handler</em> is null; </p> <p> if <em>transaction.branch</em> is null; </p> <p>if the
     *                                                           <em>transaction.amount</em> is not null and not positive or if the <em>transaction.amount</em> is null
     *                                                           and the <em>transaction.transactionType</em> is not <em>INVOICE</em>; </p> <p> if the
     *                                                           <em>transaction.bonus</em> is not null and negative for <em>RECHARGE</em> transaction; </p> <p> if the
     *                                                           <em>transaction.bonus</em> is not null for non-recharge transaction; </p> <p> if the
     *                                                           <em>transaction.invoiceAmount</em> is not null and negative or if <em>transaction.invoiceAmount</em> is
     *                                                           not null for refund transaction; </p> <p> if the <em>transaction .point</em> is not null for
     *                                                           <em>INVOICE</em> or <em>REFUND</em> transaction. </p>
     * @throws EntityExistsException                             if a transaction of the same number already exists.
     * @throws com.irh.transcation.services.EntityNotFoundException if the associated VIP card cannot be found.
     * @throws InsufficientBalanceException                      if the balance of the VIP card is insufficient.
     * @throws CoreServiceException                              if any other error occurs.
     */
    void saveTransaction(VipCardTransaction transaction) throws CoreServiceException;

    /**
     * Updates the VIP card.
     *
     * @param vipCard the VIP card to update.
     * @throws IllegalArgumentException                          if the argument is null.
     * @throws com.irh.transcation.services.EntityNotFoundException if the VIP card cannot be found.
     * @throws CoreServiceException                              if any other error occurs.
     */
    void update(VipCard vipCard) throws CoreServiceException;

    /**
     * Updates the status of the VIP card.
     *
     * @param id     the VIP card id.
     * @param status the new status.
     * @throws IllegalArgumentException   if the status is null.
     * @throws EntityNotFoundException    if the VIP card cannot be found.
     * @throws EntityUnavailableException if the VIP card is disabled.
     * @throws CoreServiceException       if any other error occurs.
     */
    void updateStatus(long id, VipCardStatus status) throws CoreServiceException;

    /**
     * Fins the VIP card by id.
     *
     * @param id the VIP card id.
     * @return the retrieved VIP card, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    VipCard findOne(long id) throws CoreServiceException;

    /**
     * Finds the VIP card by number.
     *
     * @param number the card number.
     * @return the retrieved VIP card, null if not found.
     * @throws IllegalArgumentException if the number is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    VipCard findOne(String number) throws CoreServiceException;

    /**
     * Searches VIP cards.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<VipCard> search(VipCardSearchFilter filter) throws CoreServiceException;

    /**
     * Searches transactions of a VIP card.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null; if the <em>filter.vipCardId</em> not positive; if the <em>filter.page</em> is not
     *                                  positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<VipCardTransaction> searchTransactions(VipCardTransactionSearchFilter filter)
            throws CoreServiceException;


    void replace(VipCard newCard, VipCard oldCard) throws CoreServiceException;

    /**
     * statistics vip card by status
     *
     * @param filter is the search filter.
     * @return the count of statistics result.
     * @throws CoreServiceException     if any other error occurs.
     * @throws IllegalArgumentException if the status is null.
     */
    Long statisticsByStatus(VipCardSearchFilter filter, VipCardStatus status) throws CoreServiceException;
}
