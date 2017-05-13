package com.irh.transaction.dao;

import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.dto.search.VipCardTransactionSearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link VipCard} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface VipCardMapper{

    /**
     * Saves the VIP card.
     *
     * @param vipCard the VIP card to save.
     */
    @Transactional
    void save(VipCard vipCard);

    /**
     * Saves the VIP card transaction.
     *
     * @param transaction the VIP card transaction to save.
     * @return the number of affected rows. It will be <em>-1</em> if the balance or invoice record of the VIP card is
     * insufficient; <em>-2</em> if a transaction with the same number already exists; <em>0</em> if the VIP card does
     * not exist; <em>1</em> if saved successfully.
     */
    @Transactional
    int saveTransaction(VipCardTransaction transaction);

    /**
     * Updates the VIP card.
     *
     * @param vipCard the VIP card to update.
     * @return the number of affected rows.
     */
    @Transactional
    int update(VipCard vipCard);

    /**
     * Updates the status of the VIP card.
     *
     * @param id     the VIP card id.
     * @param status the new status.
     * @return the number of affected rows, -1 if the VIP card is disabled.
     */
    @Transactional
    int updateStatus(@Param("id") long id, @Param("status") VipCardStatus status);

    /**
     * Finds the VIP card by id.
     *
     * @param id the VIP card id.
     * @return the retrieved VIP card, null if not found.
     */
    VipCard findOne(long id);

    /**
     * Finds the VIP card by number.
     *
     * @param number the card number.
     * @return the retrieved VIP card, null if not found.
     */
    VipCard findOneByNumber(String number);

    /**
     * Searches VIP cards.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<VipCard> search(@Param("filter") VipCardSearchFilter filter);

    /**
     * Counts the number of VIP cards that match the filter.
     *
     * @param filter the filter.
     * @return the number of VIP cards that match the filter.
     */
    long count(@Param("filter") VipCardSearchFilter filter);

    /**
     * Searches VIP card transactions.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<VipCardTransaction> searchTransactions(
            @Param("filter") VipCardTransactionSearchFilter filter);

    /**
     * Counts the number of VIP card transactions that match the filter.
     *
     * @param filter the filter.
     * @return the number of VIP card transactions that match the filter.
     */
    long countTransactions(@Param("filter") VipCardTransactionSearchFilter filter);

    /**
     * Checks if the VIP card of the headquarter and with the given number exists.
     *
     * @param hqId   the headquarter id.
     * @param number the card number.
     * @return the VIP card id, null if not found.
     */
    Long checkByHqIdAndNumber(@Param("hqId") long hqId, @Param("number") String number);

    /**
     * statistics vip card by status
     *
     * @param filter is the search filter.
     * @param status is the search filter.
     * @return the count of statistics result.
     */
    Long statisticsByStatus(@Param("filter") VipCardSearchFilter filter, @Param("status") VipCardStatus status);
}
