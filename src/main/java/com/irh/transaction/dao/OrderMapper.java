package com.irh.transaction.dao;

import com.irh.transaction.model.order.*;
import com.irh.transaction.dto.search.OrderSearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Order} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <<p> <b>v1.1 Change Notes:</b> </p> <ol> <li> Added the {@link OrderMapper#searchInvoiceRecords(OrderSearchFilter)}
 * and {@link OrderMapper#countInvoiceRecords(OrderSearchFilter)} method for searching orders with invoice record. </li>
 * <li> Added the {@link OrderMapper#updateStatus(long, OrderStatus)} method to updating order status. </li></ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface OrderMapper{

    /**
     * Saves the order.
     *
     * @param order the order to save.
     */
    @Transactional
    void save(Order order);

    /**
     * Saves the order item.
     *
     * @param item the order item to save.
     */
    @Transactional
    void saveItem(OrderItem item);

    /**
     * Saves the order payment record.
     *
     * @param orderId the order id.
     * @param record  the record to save.
     */
    @Transactional
    void savePaymentRecord(@Param("orderId") long orderId, @Param("record") OrderPaymentRecord record);

    /**
     * Saves the order item option.
     *
     * @param itemId   the item id.
     * @param optionId the option id.
     */
    @Transactional
    void saveItemOption(@Param("itemId") long itemId, @Param("optionId") long optionId);

    /**
     * Saves the order item discount.
     *
     * @param itemId     the item id.
     * @param discountId the discount id.
     */
    @Transactional
    void saveItemDiscount(@Param("itemId") long itemId, @Param("discountId") long discountId);

    /**
     * Creates the invoice for order of the give id.
     *
     * @param orderId the order id.
     * @param record  the invoice record.
     */
    @Transactional
    void saveInvoice(@Param("orderId") long orderId, @Param("record") OrderInvoiceRecord record);

    /**
     * Updates the order status.
     *
     * @param id     the order id.
     * @param status the new status value.
     * @return the number of affected row.
     * @since 1.1
     */
    @Transactional
    int updateStatus(@Param("id") long id, @Param("status") OrderStatus status);

    /**
     * Checks if the order of the given bill number exists.
     *
     * @param bill the bill number.
     * @return the number of existing orders.
     */
    int checkByBill(String bill);

    /**
     * Finds the order created after the given date by bill number.
     *
     * @param bill the partial string of the bill number.
     * @param date the date, optional.
     * @return the retrieved order, null if not found or more than one result is found.
     */
    Order findByBillAndDate(@Param("bill") String bill, @Param("date") Date date);

    /**
     * Finds the order by id.
     *
     * @param id the order id.
     * @return the retrieved order.
     */
    Order findOne(long id);

    /**
     * Gets the payment amount of the given bill number.
     *
     * @param bill the order bill number.
     * @return the payment amount.
     */
    BigDecimal findPaymentAmount(String bill);

    /**
     * Searches orders.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Order> search(@Param("filter") OrderSearchFilter filter);

    /**
     * Counts the orders that match the filter.
     *
     * @param filter the search filter.
     * @return the number of orders that match the filter.
     */
    long count(@Param("filter") OrderSearchFilter filter);

    /**
     * Searches order invoice records.
     *
     * @param filter the search filter.
     * @return the search result.
     * @since 1.1
     */
    List<Order> searchInvoiceRecords(@Param("filter") OrderSearchFilter filter);

    /**
     * Counts the orders with invoice record that match the filter.
     *
     * @param filter the search filter.
     * @return the number of orders with invoice record that match the filter.
     * @since 1.1
     */
    long countInvoiceRecords(@Param("filter") OrderSearchFilter filter);

    /**
     * Counts the total amount of all invoice records that match the filter.
     *
     * @param filter the search filter.
     * @return counting result.
     */
    BigDecimal countTotalInvoiceAmount(@Param("filter") OrderSearchFilter filter);

    /**
     * Gets the order counter for the branch.
     *
     * @param branchId the branch id.
     * @return the order counter.
     */
    int getCounter(int branchId);

    /**
     * Gets the total summary of orders.
     *
     * @param filter the filter.
     * @return the order entity containing the total summary data.
     */
    Order getTotal(@Param("filter") OrderSearchFilter filter);
}
