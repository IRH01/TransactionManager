package com.irh.transaction.services.impl;

import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.model.marketing.VipCardTransactionType;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.dto.search.VipCardTransactionSearchFilter;
import com.irh.transaction.services.*;
import com.irh.transaction.dao.VipCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Default implementation of the {@link VipCardService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> <br>Added the {@link VipCardService#saveTransaction(VipCardTransaction)} and {@link
 * VipCardService#searchTransactions(VipCardTransactionSearchFilter)} methods to manage {@link VipCardTransaction}.
 * </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
@Service
public class VipCardServiceImpl implements VipCardService{

    /**
     * The mapper to manage {@link VipCard} in persistence.
     */
    @Autowired
    private VipCardMapper mapper;

    /**
     * Saves the VIP card.
     *
     * @param vipCard
     *         the VIP card to save.
     *
     * @throws IllegalArgumentException
     *         <p> if the argument is null; </p> <p> if the <em>vipCard.number</em> is null or empty; </p> <p> if the
     *         <em>vipCard.name</em> is null or empty; </p> <p> if the <em>vipCard.gender</em> is null or empty; </p>
     *         <p> if the <em>vipCard.balance</em> is null or negative; </p> <p> if the <em>vipCard.invoiceQuota</em> is
     *         null or negative. </p>
     * @throws EntityExistsException
     *         if a VIP card of the headquarter with the same card number already exists.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(VipCard vipCard) throws CoreServiceException{
        ServiceHelper.checkNotNull(vipCard, "vipCard");
        ServiceHelper.checkString(vipCard.getNumber(), "number");
        ServiceHelper.checkString(vipCard.getName(), "name");
        ServiceHelper.checkString(vipCard.getGender(), "gender");
        ServiceHelper.checkNotNull(vipCard.getBalance(), "balance");
        if (vipCard.getBalance().compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("The balance cannot be negative.");
        }
        ServiceHelper.checkNotNull(vipCard.getInvoiceQuota(), "invoiceQuota");
        if (vipCard.getInvoiceQuota().compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("The invoiceQuota cannot be negative.");
        }
        try {
            if (mapper.checkByHqIdAndNumber(vipCard.getHqId(), vipCard.getNumber()) != null) {
                throw new EntityExistsException(
                        "The VIP card of the headquarter and with same number already exists.");
            }
            mapper.save(vipCard);
        } catch (EntityExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Saves the VIP card transaction.
     *
     * @param transaction
     *         the VIP card transaction to save.
     *
     * @throws IllegalArgumentException
     *         <p> if the argument is null; </p> <p> if the <em>transaction.number</em> is null or empty for INVOICE
     *         transaction or if the <em>transaction.number</em> is not null for non-invoice transaction; </p> <p> if
     *         the <em>transaction.handler</em> is null; </p> <p> if <em>transaction.branch</em> is null; </p> <p>if the
     *         <em>transaction.amount</em> is not null and not positive or if the <em>transaction.amount</em> is null
     *         and the <em>transaction.transactionType</em> is not <em>INVOICE</em>; </p> <p> if the
     *         <em>transaction.bonus</em> is not null and negative for <em>RECHARGE</em> transaction; </p> <p> if the
     *         <em>transaction.bonus</em> is not null for non-recharge transaction; </p> <p> if the
     *         <em>transaction.invoiceAmount</em> is not null and negative or if <em>transaction.invoiceAmount</em> is
     *         not null for refund transaction; </p> <p> if the <em>transaction .point</em> is not null for
     *         <em>INVOICE</em> or <em>REFUND</em> transaction. </p>
     * @throws EntityExistsException
     *         if a transaction of the same number already exists.
     * @throws EntityNotFoundException
     *         if the associated VIP card cannot be found.
     * @throws InsufficientBalanceException
     *         if the balance of the VIP card is insufficient.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void saveTransaction(VipCardTransaction transaction) throws CoreServiceException{
        ServiceHelper.checkNotNull(transaction, "transaction");
        ServiceHelper.checkNotNull(transaction.getHandler(), "handler");
        ServiceHelper.checkNotNull(transaction.getBranch(), "branch");
        if (transaction.getTransactionType() == VipCardTransactionType.INVOICE) {
            if (transaction.getNumber() != null) {
                throw new IllegalArgumentException("The number must be null for INVOICE transaction.");
            }
            if (transaction.getAmount() != null) {
                throw new IllegalArgumentException("The amount must be null for INVOICE transaction.");
            }
            if (transaction.getInvoiceAmount() == null) {
                throw new IllegalArgumentException("The invoice amount cannot be null for INVOICE transaction.");
            }
        } else {
            ServiceHelper.checkString(transaction.getNumber(), "number");
            if (transaction.getAmount() == null) {
                throw new IllegalArgumentException("The amount cannot be null for non-invoice transaction");
            }
            if (transaction.getAmount().compareTo(new BigDecimal("0")) <= 0) {
                throw new IllegalArgumentException("The amount must be positive.");
            }
        }
        if (transaction.getTransactionType() == VipCardTransactionType.RECHARGE) {
            if (transaction.getBonus() != null && transaction.getBonus().compareTo(new BigDecimal("0")) < 0) {
                throw new IllegalArgumentException("The bonus cannot be negative.");
            }
        } else if (transaction.getBonus() != null) {
            throw new IllegalArgumentException("The bonus is not allowed for non-recharge transaction.");
        }

        if (transaction.getTransactionType() != VipCardTransactionType.RECHARGE &&
                transaction.getTransactionType() != VipCardTransactionType.CONSUME &&
                transaction.getPoint() != null) {
            throw new IllegalArgumentException("The point is not allowed for non-recharge and non-consume transaction");
        }
        if (transaction.getTransactionType() == VipCardTransactionType.REFUND && transaction.getInvoiceAmount() != null) {
            throw new IllegalArgumentException("The invoice amount must be null for refund transaction.");
        }
        if (transaction.getInvoiceAmount() != null &&
                transaction.getInvoiceAmount().compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("The invoice amount cannot be negative.");
        }
        try {
            int result = mapper.saveTransaction(transaction);
            if (result == -2) {
                throw new EntityExistsException("A transaction with the same number already exists.");
            }
            if (result == -1) {
                throw new InsufficientBalanceException(
                        "The balance or invoice quota of the VIP card is insufficient.");
            }
            if (result == 0) {
                throw new EntityNotFoundException("The VIP card cannot be found.");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the VIP card.
     *
     * @param vipCard
     *         the VIP card to update.
     *
     * @throws IllegalArgumentException
     *         if the argument is null.
     * @throws EntityNotFoundException
     *         if the VIP card cannot be found.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(VipCard vipCard) throws CoreServiceException{
        ServiceHelper.checkNotNull(vipCard, "vipCard");
        try {
            if (mapper.update(vipCard) == 0) {
                throw new EntityNotFoundException("The VIP card cannot be found.");
            }
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the status of the VIP card.
     *
     * @param id
     *         the VIP card id.
     * @param status
     *         the new status.
     *
     * @throws IllegalArgumentException
     *         if the status is null.
     * @throws EntityNotFoundException
     *         if the VIP card cannot be found.
     * @throws EntityUnavailableException
     *         if the VIP card is disabled.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void updateStatus(long id, VipCardStatus status) throws CoreServiceException{
        ServiceHelper.checkNotNull(status, "status");
        try {
            int result = mapper.updateStatus(id, status);
            if (result == 0) {
                throw new EntityNotFoundException("The VIP card cannot be found.");
            }
            if (result == -1) {
                throw new EntityUnavailableException("The VIP card is disabled");
            }
        } catch (CoreServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Fins the VIP card by id.
     *
     * @param id
     *         the VIP card id.
     *
     * @return the retrieved VIP card, null if not found.
     *
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public VipCard findOne(long id) throws CoreServiceException{
        try {
            return mapper.findOne(id);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the VIP card by number.
     *
     * @param number
     *         the card number.
     *
     * @return the retrieved VIP card, null if not found.
     *
     * @throws IllegalArgumentException
     *         if the number is null or empty.
     * @throws CoreServiceException
     *         if any error occurs.
     */
    @Override
    public VipCard findOne(String number) throws CoreServiceException{
        ServiceHelper.checkString(number, "number");
        try {
            return mapper.findOneByNumber(number);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches VIP cards.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *         or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<VipCard> search(VipCardSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try {
            List<VipCard> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches transactions of a VIP card.
     *
     * @param filter
     *         the search filter.
     *
     * @return the search result.
     *
     * @throws IllegalArgumentException
     *         if the filter is null; if the <em>filter.vipCardId</em> not positive; if the <em>filter.page</em> is not
     *         positive or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *         <em>filter.size</em> is null.
     * @throws CoreServiceException
     *         if any other error occurs.
     */
    @Override
    public SearchResult<VipCardTransaction> searchTransactions(VipCardTransactionSearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkNotNull(filter, "filter");
        if (filter.getVipCardId() <= 0) {
            throw new IllegalArgumentException("The vipCardId must be positive.");
        }
        try {
            List<VipCardTransaction> items = mapper.searchTransactions(filter);
            long count = filter.getPage() != null ? mapper.countTransactions(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        } catch (Exception ex) {
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void replace(VipCard newCard, VipCard oldCard) throws CoreServiceException{
        try {
            save(newCard);
            updateStatus(oldCard.getId(), VipCardStatus.DISABLED);
        } catch (Exception ex) {
            throw ex;
        }

    }

    /**
     * statistics vip card by status
     *
     * @param filter
     *         is the search filter.
     *
     * @return the count of statistics result.
     *
     * @throws CoreServiceException
     *         if any other error occurs.
     * @throws IllegalArgumentException
     *         if the status is null.
     */
    @Override
    public Long statisticsByStatus(VipCardSearchFilter filter, VipCardStatus status) throws CoreServiceException{
        try {
            if (status == null) {
                throw new IllegalArgumentException("The vipCardId must be positive.");
            }
            return mapper.statisticsByStatus(filter, status);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
