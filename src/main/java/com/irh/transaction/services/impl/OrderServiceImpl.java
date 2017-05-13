package com.irh.transaction.services.impl;

import com.irh.transaction.dao.OrderMapper;
import com.irh.transaction.dao.VipCardMapper;
import com.irh.transaction.dto.search.OrderSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.model.marketing.VipCardTransactionType;
import com.irh.transaction.model.order.*;
import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Default implementation of the {@link OrderService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class OrderServiceImpl implements OrderService{

    /**
     * The mapper to manage {@link Order} in persistence.
     */
    @Autowired
    private OrderMapper mapper;

    /**
     * The mapper to manage {@link VipCard} in persistence.
     */
    @Autowired
    private VipCardMapper vipCardMapper;

    /**
     * Saves the order.
     *
     * @param order the order to save.
     * @throws IllegalArgumentException     <p>if the order is null;</p> <p> if the <em>order.bill</em> is null or empty; </p> </p> <p>if the
     *                                      <em>order.payType</em> is <em>OrderPayType .COMBINED</em> and <em>order.paymentRecords</em> is null or
     *                                      contains only one or zero item;</p> <p> if the <em>order.items</em> is null or empty or if the product or
     *                                      price of any item is null; </p> <p> if the item with discount(s) has no original price or if the original
     *                                      price is less than the item price; </p> <p>if the <em>order.payType</em> is <em>PayType.VIPCARD</em> and
     *                                      the <em>order.vipCardId</em> is null;</p> <p>if the <em>order.price</em> is null or if the
     *                                      <em>order.originalPrice</em> is less than the <em>order.price</em>; </p> <p> if the <em>order.price</em>
     *                                      is 0 and the <em>order.paymentRecords</em> is not null nor empty; </p> <p> if the <em>order.price</em> is
     *                                      not 0 and there is not any payment record with its pay type equals the order pay type; </p><p>if the
     *                                      order contains more than one invoice records or if the amount of the invoice record is null or not
     *                                      positive.</p>
     * @throws InsufficientBalanceException if the <em>order.vipCard</em> is not null and the balance of the VIP card is insufficient.
     * @throws CoreServiceException         if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Order order) throws CoreServiceException{
        checkOrder(order);
        try{
            mapper.save(order);
            for(OrderItem item : order.getItems()){
                item.setOrderId(order.getId());
                mapper.saveItem(item);
                if(item.getOptions() != null){
                    for(ProductOption option : item.getOptions()){
                        mapper.saveItemOption(item.getId(), option.getId());
                    }
                }
                if(item.getDiscounts() != null){
                    for(ProductDiscount discount : item.getDiscounts()){
                        mapper.saveItemDiscount(item.getId(), discount.getId());
                    }
                }
            }
            if(order.getInvoiceRecords() != null){
                for(OrderInvoiceRecord invoiceRecord : order.getInvoiceRecords()){
                    invoiceRecord.setCreatedAt(order.getCreatedAt());
                    invoiceRecord.setHandler(order.getHandler());
                    mapper.saveInvoice(order.getId(), invoiceRecord);
                }
            }
            for(OrderPaymentRecord record : order.getPaymentRecords()){
                if(order.getPayType() == PayType.VIPCARD && record.getPayType().equals(PayType.VIPCARD)){
                    VipCardTransaction transaction = new VipCardTransaction();
                    transaction.setAmount(record.getAmount());
                    transaction.setPayType(PayType.VIPCARD);
                    Branch branch = new Branch();
                    branch.setId(order.getBranchId());
                    transaction.setBranch(branch);
                    transaction.setHandler(order.getHandler());
                    transaction.setNumber(order.getBill());
                    if(order.getInvoiceRecords() != null && order.getInvoiceRecords().size() > 0){
                        transaction.setInvoiceAmount(order.getInvoiceRecords().get(0).getAmount());
                    }
                    transaction.setVipCardId(order.getVipCardId());
                    transaction.setCreatedAt(order.getCreatedAt());
                    transaction.setTransactionType(VipCardTransactionType.CONSUME);
                    int cardTransactionResult = vipCardMapper.saveTransaction(transaction);
                    if(cardTransactionResult == -1){
                        throw new InsufficientBalanceException("The balance or the invoice quota of the VIP card is " +
                                "insufficient.");
                    }
                }
                mapper.savePaymentRecord(order.getId(), record);
            }
        }catch(CoreServiceException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Saves the invoice record of the order of the given id.
     *
     * @param orderId       the order id.
     * @param invoiceRecord the invoice record to save.
     * @throws IllegalArgumentException if the invoice record is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void saveInvoice(long orderId, OrderInvoiceRecord invoiceRecord)
            throws CoreServiceException{
        ServiceHelper.checkNotNull(invoiceRecord, "invoiceRecord");
        try{
            Order order = mapper.findOne(orderId);
            if(order.getVipCardId() != null){
                VipCardTransaction transaction = new VipCardTransaction();
                transaction.setHandler(invoiceRecord.getHandler());
                transaction.setInvoiceAmount(invoiceRecord.getAmount());
                transaction.setTransactionType(VipCardTransactionType.INVOICE);
                transaction.setCreatedAt(invoiceRecord.getCreatedAt());
                Branch branch = new Branch();
                branch.setId(order.getBranchId());
                transaction.setBranch(branch);
                transaction.setVipCardId(order.getVipCardId());
                vipCardMapper.saveTransaction(transaction);
            }
            mapper.saveInvoice(orderId, invoiceRecord);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the status of the order.
     *
     * @param id     the order id.
     * @param status the new status value.
     * @throws EntityNotFoundException if the order cannot be found.
     * @throws CoreServiceException    if any error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void updateStatus(long id, OrderStatus status) throws CoreServiceException{
        try{
            if(mapper.updateStatus(id, status) == 0){
                throw new EntityNotFoundException("The order cannot be found.");
            }
        }catch(EntityNotFoundException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the order created after the given date by bill number.
     *
     * @param bill the partial string of the bill number.
     * @param date the date, optional.
     * @return the retrieved order, null if not found or more than one result is found.
     * @throws IllegalArgumentException if the bill is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public Order findOne(String bill, Date date) throws CoreServiceException{
        ServiceHelper.checkString(bill, "bill");
        try{
            return mapper.findByBillAndDate(bill, date);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the order by id.
     *
     * @param id the order id.
     * @return the retrieved order.
     * @throws CoreServiceException if any error occurs.
     */
    @Override
    public Order findOne(long id) throws CoreServiceException{
        try{
            return mapper.findOne(id);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Gets the payment amount of the given bill number.
     *
     * @param bill the order bill number.
     * @return the payment amount, null if not found.
     * @throws IllegalArgumentException if the argument is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public BigDecimal findPaymentAmount(String bill) throws CoreServiceException{
        ServiceHelper.checkString(bill, "bill");
        try{
            return mapper.findPaymentAmount(bill);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Checks if the order of the given bill number exists.
     *
     * @param bill the bill number.
     * @return the value indicates if the order exists.
     * @throws IllegalArgumentException if the bill is null or empty.
     * @throws CoreServiceException     if any error occurs.
     */
    @Override
    public boolean checkByBill(String bill) throws CoreServiceException{
        ServiceHelper.checkString(bill, "bill");
        try{
            return mapper.checkByBill(bill) > 0;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

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
    @Override
    public SearchResult<Order> search(OrderSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try{
            List<Order> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

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
    @Override
    public SearchResult<Order> searchInvoiceRecords(OrderSearchFilter filter)
            throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try{
            List<Order> items = mapper.searchInvoiceRecords(filter);
            long count = filter.getPage() != null ? mapper.countInvoiceRecords(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Counts the total amount of all invoice records that match the filter.
     *
     * @param filter the search filter.
     * @return counting result.
     * @throws CoreServiceException if any error occurs.
     */
    @Override
    public BigDecimal countInvoiceAmount(OrderSearchFilter filter) throws CoreServiceException{
        try{
            return mapper.countTotalInvoiceAmount(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

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
    @Override
    public Order getTotal(OrderSearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try{
            return mapper.getTotal(filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    private static void checkOrder(Order order){
        ServiceHelper.checkNotNull(order, "order");
        ServiceHelper.checkString(order.getBill(), "bill");
        if(order.getId() == 0){
            if(order.getPayType() == PayType.COMBINED &&
                    (order.getPaymentRecords() == null || order.getPaymentRecords().size() <= 1)){
                throw new IllegalArgumentException(
                        "There is only one payment records for COMBINED pay type.");
            }
        }
        if(order.getPayType() == PayType.VIPCARD && order.getVipCardId() == null){
            throw new IllegalArgumentException(
                    "The vipCardId cannot be null for order with VIPCARD pay type.");
        }

        if(order.getInvoiceRecords() != null && order.getInvoiceRecords().size() > 0){
            if(order.getInvoiceRecords().size() > 1){
                throw new IllegalArgumentException("There can be only one invoice record.");
            }
            OrderInvoiceRecord invoiceRecord = order.getInvoiceRecords().get(0);
            if(invoiceRecord.getAmount() == null){
                throw new IllegalArgumentException("The amount of the invoice record must not be null.");
            }
            if(invoiceRecord.getAmount().compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("The amount of the invoice record must be positive.");
            }
        }

        ServiceHelper.checkList(order.getItems(), "order.items");
        for(OrderItem item : order.getItems()){
            if(item.getProduct() == null){
                throw new IllegalArgumentException("The item product cannot be null.");
            }
            ServiceHelper.checkNotNull(item.getPrice(), "item price");
            if(item.getDiscounts() != null && item.getDiscounts().size() > 0){
                if(item.getOriginalPrice() == null){
                    throw new IllegalArgumentException("The item with discount(s) has no original price.");
                }else if(item.getOriginalPrice().compareTo(item.getPrice()) < 0){
                    throw new IllegalArgumentException("The item original price cannot be less than the price.");
                }
            }
        }
        ServiceHelper.checkNotNull(order.getPrice(), "price");
        if(order.getPrice().compareTo(BigDecimal.ZERO) == 0){
            if(order.getPayType() != PayType.CASH){
                throw new IllegalArgumentException(
                        "The order pay type must be cash when the price is 0.");
            }
            if(order.getPaymentRecords() != null && order.getPaymentRecords().size() > 0){
                throw new IllegalArgumentException("The payment records must be empty when the order price is 0.");
            }
        }else{
            if(order.getPaymentRecords() == null || order.getPaymentRecords().size() == 0){
                throw new IllegalArgumentException("The payment records cannot be null or empty when the order price " +
                        "is not 0.");
            }
            boolean payTypeValid = false;
            for(OrderPaymentRecord paymentRecord : order.getPaymentRecords()){
                if(paymentRecord.getPayType() == order.getPayType()){
                    payTypeValid = true;
                    break;
                }
            }
            if(!payTypeValid){
                throw new IllegalArgumentException("There must 1 payment record with its pay type equals the order " +
                        "pay type.");
            }
        }

        if(order.getOriginalPrice() != null && order.getPrice().compareTo(order.getOriginalPrice()) == 1){
            throw new IllegalArgumentException("The original price cannot be less than the price.");
        }
    }

}
