package com.irh.transaction.services;

import com.irh.transaction.services.InsufficientBalanceException;
import com.irh.transaction.model.order.Order;
import com.irh.transaction.model.order.OrderInvoiceRecord;
import com.irh.transaction.model.order.OrderStatus;
import com.irh.transaction.dto.search.OrderSearchFilter;
import com.irh.transaction.dto.search.SearchResult;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Defines a contract for managing {@link Order}.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> Added the {@link com.irh.transcation.services.OrderService#searchInvoiceRecords(OrderSearchFilter)}
 * method for searching orders with invoice record. </li> <li> Added the {@link com.irh.transcation.services.OrderService#updateStatus(long,
 * OrderStatus)} method for updating order status. </li> <li> The implementation of the {@link com.irh.transcation.services.OrderService#save(Order)}
 * method has been updated. See method doc for detail. </li> <li> Added the {@link
 * com.irh.transcation.services.OrderService#getTotal(OrderSearchFilter)} method for statistic purpose. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface OrderService{

    /**
     * Saves the order.
     *
     * @param order the order to save.
     * @throws IllegalArgumentException                               <p>if the order is null;</p> <p> if the <em>order.bill</em> is null or empty; </p> </p> <p>if the
     *                                                                <em>order.payType</em> is <em>OrderPayType .COMBINED</em> and <em>order.paymentRecords</em> is null or
     *                                                                contains only one or zero item;</p> <p> if the <em>order.items</em> is null or empty or if the product or
     *                                                                price of any item is null; </p> <p> if the item with discount(s) has no original price or if the original
     *                                                                price is less than the item price; </p> <p>if the <em>order.payType</em> is <em>PayType.VIPCARD</em> and
     *                                                                the <em>order.vipCardId</em> is null;</p> <p>if the <em>order.price</em> is null or if the
     *                                                                <em>order.originalPrice</em> is less than the <em>order.price</em>; </p> <p> if the <em>order.price</em>
     *                                                                is 0 and the <em>order.paymentRecords</em> is not null nor empty; </p> <p> if the <em>order.price</em> is
     *                                                                not 0 and there is not any payment record with its pay type equals the order pay type; </p><p>if the
     *                                                                order contains more than one invoice records or if the amount of the invoice record is null or not
     *                                                                positive.</p>
     * @throws com.irh.transcation.services.InsufficientBalanceException if the <em>order.vipCard</em> is not null and the balance of the VIP card is insufficient.
     * @throws CoreServiceException                                   if any other error occurs.
     */
    void save(Order order) throws CoreServiceException;

    /**
     * Saves the invoice record of the order of the given id.
     *
     * @param orderId       the order id.
     * @param invoiceRecord the invoice record to save.
     * @throws IllegalArgumentException     if the invoice record is null.
     * @throws InsufficientBalanceException if the order is paid via VIP card and the invoice quota of the VIP card is insufficient
     * @throws CoreServiceException         if any other error occurs.
     */
    void saveInvoice(long orderId, OrderInvoiceRecord invoiceRecord) throws CoreServiceException;

    /**
     * Updates the status of the order.
     *
     * @param id     the order id.
     * @param status the new status value.
     * @throws EntityNotFoundException if the order cannot be found.
     * @throws CoreServiceException    if any error occurs.
     * @since 1.1
     */
    void updateStatus(long id, OrderStatus status) throws CoreServiceException;

    /**
     * Finds the order created after the given date by bill number.
     *
     * @param bill the partial string of the bill number.
     * @param date the date, optional.
     * @return the retrieved order, null if not found or more than one result is found.
     * @throws IllegalArgumentException if the bill is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    Order findOne(String bill, Date date) throws CoreServiceException;

    /**
     * Finds the order by id.
     *
     * @param id the order id.
     * @return the retrieved order.
     * @throws CoreServiceException if any error occurs.
     */
    Order findOne(long id) throws CoreServiceException;

    /**
     * Gets the payment amount of the given bill number.
     *
     * @param bill the order bill number.
     * @return the payment amount, null if not found.
     * @throws IllegalArgumentException if the argument is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    BigDecimal findPaymentAmount(String bill) throws CoreServiceException;

    /**
     * Checks if the order of the given bill number exists.
     *
     * @param bill the bill number.
     * @return the value indicates if the order exists.
     * @throws IllegalArgumentException if the bill is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    boolean checkByBill(String bill) throws CoreServiceException;

    /**
     * Searches orders.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    SearchResult<Order> search(OrderSearchFilter filter) throws CoreServiceException;

    /**
     * Searches orders with invoice record.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     * @since 1.1
     */
    SearchResult<Order> searchInvoiceRecords(OrderSearchFilter filter) throws CoreServiceException;

    /**
     * Counts the total amount of all invoice records that match the filter.
     *
     * @param filter the search filter.
     * @return counting result.
     * @throws CoreServiceException if any error occurs.
     */
    BigDecimal countInvoiceAmount(OrderSearchFilter filter) throws CoreServiceException;

    /**
     * Gets the total summary of orders.
     *
     * @param filter the filter.
     * @return the order entity containing the total summary data.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     * @since 1.1
     */
    Order getTotal(OrderSearchFilter filter) throws CoreServiceException;
}
