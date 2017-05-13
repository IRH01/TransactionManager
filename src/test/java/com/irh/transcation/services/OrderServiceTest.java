package com.irh.transcation.services;

import com.irh.transaction.dto.search.OrderSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.dto.search.VipCardTransactionSearchFilter;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.model.marketing.VipCardTransactionType;
import com.irh.transaction.model.order.*;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.services.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link OrderService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class OrderServiceTest extends BaseTest{

    /**
     * The {@link OrderService} instance to test.
     */
    private OrderService service;

    /**
     * Asserts the order is correct.
     *
     * @param expectedId      the expected order id.
     * @param order           the actual order.
     * @param compareChildren the value indicates if child properties should be compared.
     */
    private static void assertOrder(long expectedId, Order order, boolean compareChildren){
        String expectedIdStr = String.valueOf(expectedId);
        boolean even = expectedId % 2 == 0;
        PayType[] expectedPayTypes = {
                PayType.CASH,
                PayType.UNI_PAY,
                PayType.WECHAT,
                PayType.ALIPAY,
                PayType.COMBINED,
                PayType.SIGNED,
                PayType.VIPCARD,
                PayType.CASH,
                PayType.CASH,
                PayType.UNI_PAY,
                PayType.WECHAT,
                PayType.ALIPAY
        };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getCreatedAt());
        assertEquals(expectedId, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(TestHelper.HQ_ID, order.getHqId());
        assertEquals(expectedId > 4 && expectedId <= 8 ? 2 : 1, order.getBranchId().longValue());
        assertEquals(expectedIdStr, order.getBill());
        if(expectedId <= 8){
            assertEquals(even ? 560 : 280, order.getPrice().intValue());
            assertEquals(even ? 600 : 300, order.getOriginalPrice().intValue());
            assertNull(order.getOriginalBill());
            assertEquals(new Float(0.93), order.getDiscount());
            assertEquals(even ? 600 : 300, order.getReceived().intValue());
            assertEquals(even ? 40 : 20, order.getChange().intValue());
            Platform expectedPlatform;
            OrderServiceType expectedServiceType;
            if(expectedId % 3 == 0){
                expectedPlatform = Platform.APP;
                expectedServiceType = OrderServiceType.SELF_SERVICE;
            }else if(expectedId % 3 == 2){
                expectedPlatform = Platform.IPAD;
                expectedServiceType = OrderServiceType.PACKAGE;
            }else{
                expectedPlatform = Platform.POS;
                expectedServiceType = OrderServiceType.RESTAURANT;
            }
            assertEquals(expectedPlatform, order.getPlatform());
            assertEquals(expectedServiceType, order.getServiceType());
            if(expectedId == 7){
                assertEquals(1, order.getVipCardId().intValue());
            }else{
                assertNull(order.getVipCardId());
            }
        }else{
            assertEquals((expectedId - 8) * -10, order.getPrice().intValue());
            assertNull(order.getOriginalPrice());
            assertEquals(String.valueOf(expectedId - 8), order.getOriginalBill());
            assertNull(order.getDiscount());
            assertNull(order.getReceived());
            assertNull(order.getChange());
            assertEquals(Platform.POS, order.getPlatform());
            assertEquals(OrderServiceType.RESTAURANT, order.getServiceType());
        }
        assertEquals(OrderStatus.COMPLETE, order.getStatus());
        assertEquals(expectedPayTypes[(int) expectedId - 1], order.getPayType());
        assertEquals(even ? 2 : 1, order.getNumberOfPeople().intValue());
        assertEquals("remark", order.getRemark());
        assertEquals(expectedId, order.getDevice().intValue());
        if(compareChildren){
            assertEquals(even ? 2 : 1, order.getHandler().getId());
            assertEquals(expectedId <= 8 ? 2 : 1, order.getItems().size());
            if(expectedId == 6){
                assertEquals(1, order.getSigner().getId());
            }else{
                assertNull(order.getSigner());
            }
            for(OrderItem item : order.getItems()){
                int itemPriceSeed = even ? 140 : 70;
                long productId = item.getProduct().getId();
                assertEquals(1, item.getOptions().size());
                assertEquals(productId, item.getOptions().get(0).getId());
                if(expectedId <= 8){
                    assertEquals(productId == 1 ? 1 : 2, item.getCount());
                    assertEquals(productId == 1 ? 0 : itemPriceSeed * 2, item.getPrice().intValue());
                    assertEquals(productId == 1 ? new BigDecimal(itemPriceSeed + ".0") : null, item.getOriginalPrice());
                    if(productId == 1){
                        assertEquals(1, item.getDiscounts().size());
                        assertEquals(expectedId > 4 ? 2 : 1, item.getDiscounts().get(0).getId());
                    }else{
                        assertEquals(0, item.getDiscounts().size());
                    }
                    assertNull(item.getOriginalId());
                    assertEquals(1, item.getOptions().size());
                    assertEquals(productId == 1 ? 1 : 2, item.getOptions().get(0).getId());
                    assertEquals(productId == 2 ? OrderServiceType.PACKAGE : OrderServiceType.RESTAURANT,
                            item.getServiceType());
                }else{
                    assertEquals(OrderServiceType.PACKAGE, item.getServiceType());
                    assertEquals(even ? -2 : -1, item.getCount());
                    assertEquals(even ? 140 : 70, item.getPrice().intValue());
                    assertNull(item.getOriginalPrice());
                    assertEquals(0, item.getDiscounts().size());
                    assertEquals(0, item.getOptions().size());
                    assertEquals(2, productId);
                    assertEquals((expectedId - 8) * 2, item.getOriginalId().intValue());
                }
            }

            if(expectedId <= 8){
                assertEquals(2, order.getPaymentRecords().size());
                assertEquals(even ? 2 : 1, order.getTable().getId());
                for(int i = 0; i < order.getPaymentRecords().size(); i++){
                    OrderPaymentRecord paymentRecord = order.getPaymentRecords().get(i);
                    if(expectedId == 5){
                        assertEquals(i == 0 ? PayType.CASH :
                                PayType.UNI_PAY, paymentRecord.getPayType());
                    }else{
                        assertEquals(i != 0 ? PayType.COUPON : order.getPayType(),
                                paymentRecord.getPayType());
                        assertEquals(order.getPrice().divide(
                                new BigDecimal(2), BigDecimal.ROUND_DOWN).intValue(),
                                paymentRecord.getAmount().intValue());
                    }
                }
                assertNotNull(order.getInvoiceRecords());
                assertEquals(1, order.getInvoiceRecords().size());
                OrderInvoiceRecord invoiceRecord = order.getInvoiceRecords().get(0);
                assertEquals(even ? 560 : 280, invoiceRecord.getAmount().intValue());
                assertEquals(even ? 2 : 1, invoiceRecord.getHandler().getId());
            }else{
                assertEquals(1, order.getPaymentRecords().size());
                assertEquals(order.getPayType(), order.getPaymentRecords().get(0).getPayType());
                assertEquals(order.getPrice(), order.getPaymentRecords().get(0).getAmount());
                assertEquals(0, order.getInvoiceRecords().size());
                assertNull(order.getTable());
            }
        }
    }

    private static Order createOrder() throws ParseException{
        Order order = new Order();
        order.setHqId(TestHelper.HQ_ID);
        order.setBranchId(2L);
        Account handler = new Account();
        handler.setId(2L);
        order.setHandler(handler);
        order.setCreatedAt(TestHelper.parseDate(true, "2016-01-06 06:00"));
        order.setBill("14");
        order.setPrice(new BigDecimal(560));
        order.setOriginalPrice(new BigDecimal(600));
        order.setDiscount(0.93f);
        order.setNumberOfPeople(2);
        order.setPayType(PayType.SIGNED);
        order.setPlatform(Platform.APP);
        order.setStatus(OrderStatus.COMPLETE);
        ArrayList<OrderItem> items = new ArrayList<>();
        items.add(createItem(false));
        items.add(createItem(true));
        order.setItems(items);
        order.setServiceType(OrderServiceType.SELF_SERVICE);
        Account signer = new Account();
        signer.setId(1L);
        order.setSigner(signer);
        order.setRemark("remark");
        order.setDevice(6);
        order.setReceived(new BigDecimal(600));
        order.setChange(new BigDecimal(40));
        ArrayList<OrderPaymentRecord> paymentRecords = new ArrayList<>();
        OrderPaymentRecord paymentRecord1 = new OrderPaymentRecord();
        paymentRecord1.setAmount(new BigDecimal(280));
        paymentRecord1.setPayType(PayType.SIGNED);
        paymentRecords.add(paymentRecord1);
        OrderPaymentRecord paymentRecord2 = new OrderPaymentRecord();
        paymentRecord2.setPayType(PayType.COUPON);
        paymentRecord2.setAmount(new BigDecimal(280));
        paymentRecords.add(paymentRecord2);
        order.setPaymentRecords(paymentRecords);
        OrderInvoiceRecord invoiceRecord = new OrderInvoiceRecord();
        invoiceRecord.setAmount(new BigDecimal(560));
        invoiceRecord.setCreatedAt(new Date());
        invoiceRecord.setHandler(handler);
        ArrayList<OrderInvoiceRecord> invoiceRecords = new ArrayList<>();
        invoiceRecords.add(invoiceRecord);
        order.setInvoiceRecords(invoiceRecords);
        BranchTable table = new BranchTable();
        table.setId(2);
        order.setTable(table);
        return order;
    }

    private static OrderItem createItem(boolean even){
        OrderItem item = new OrderItem();
        if(even){
            item.setPrice(new BigDecimal(280));
        }else{
            ProductDiscount discount = new ProductDiscount();
            ArrayList<ProductDiscount> discounts = new ArrayList<>();
            discount.setId(2);
            item.setOriginalPrice(new BigDecimal(140));
            item.setPrice(BigDecimal.ZERO);
            discounts.add(discount);
            item.setDiscounts(discounts);
        }
        item.setServiceType(even ? OrderServiceType.PACKAGE : OrderServiceType.RESTAURANT);
        Product product = new Product();
        product.setId(even ? 2L : 1L);
        item.setProduct(product);
        item.setCount(even ? 2 : 1);
        ArrayList<ProductOption> options = new ArrayList<>();
        ProductOption option = new ProductOption();
        option.setId(even ? 2 : 1);
        options.add(option);
        item.setOptions(options);
        return item;
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(OrderService.class);
    }

    /**
     * Tests the {@link OrderService#save(Order)} method.
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception{
        Order order = createOrder();

        service.save(order);
        assertTrue(order.getId() != 0);
        Order actual = service.findOne(order.getId());
        assertEquals("14", actual.getBill());
        actual.setBill("6");
        assertOrder(6, actual, true);
    }

    /**
     * Tests the {@link OrderService#save(Order)} method, with payment through VIP card.
     *
     * @throws Exception
     */
    @Test
    public void saveWithVipCard() throws Exception{
        Order order = createOrder();
        order.setPayType(PayType.VIPCARD);
        order.setVipCardId(1L);
        OrderPaymentRecord paymentRecord = order.getPaymentRecords().get(0);
        paymentRecord.setPayType(PayType.VIPCARD);
        paymentRecord.setAmount(new BigDecimal("100"));
        order.getInvoiceRecords().get(0).setAmount(new BigDecimal("50"));
        service.save(order);
        Order actual = service.findOne(order.getId());
        assertEquals(PayType.VIPCARD, actual.getPayType());
        VipCardService vipCardService = TestHelper.Context.getBean(VipCardService.class);
        VipCard vipCard = vipCardService.findOne(1);
        assertEquals(100, vipCard.getBalance().intValue());
        assertEquals(50, vipCard.getInvoiceQuota().intValue());
        assertEquals(1, vipCard.getPoint());

        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = vipCardService.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction transaction = result.getItems().get(6);
        assertEquals(PayType.VIPCARD, transaction.getPayType());
        assertEquals(VipCardTransactionType.CONSUME, transaction.getTransactionType());
        assertEquals(100, transaction.getAmount().intValue());
        assertEquals(50, transaction.getInvoiceAmount().intValue());
        assertEquals(200, transaction.getBalanceBefore().intValue());
        assertEquals(100, transaction.getBalanceAfter().intValue());
        assertNull(transaction.getPoint());
        assertNull(transaction.getBonus());
    }

    /**
     * Failure test for the {@link OrderService#save(Order)} method, with VIP card balance insufficient. {@link
     * InsufficientBalanceException} is expected.
     *
     * @throws Exception
     */
    @Test(expected = InsufficientBalanceException.class)
    public void saveWithBalanceInsufficient() throws Exception{
        Order order = createOrder();
        order.setVipCardId(1L);
        order.getPaymentRecords().get(0).setPayType(PayType.VIPCARD);
        order.setPayType(PayType.VIPCARD);
        order.getInvoiceRecords().get(0).setAmount(new BigDecimal("50"));

        service.save(order);
    }

    /**
     * Tests the {@link OrderService#updateStatus(long, OrderStatus)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void updateStatus() throws CoreServiceException{
        service.updateStatus(1, OrderStatus.CANCELED);
        assertEquals(OrderStatus.CANCELED, service.findOne(1).getStatus());
    }

    /**
     * Tests the {@link OrderService#updateStatus(long, OrderStatus)} method, with the order not found. {@link
     * EntityNotFoundException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityNotFoundException.class)
    public void updateStatusWithNotFound() throws CoreServiceException{
        service.updateStatus(20, OrderStatus.CANCELED);
        fail();
    }

    /**
     * Tests the {@link OrderService#saveInvoice(long, OrderInvoiceRecord)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveInvoice() throws CoreServiceException{
        OrderInvoiceRecord record = new OrderInvoiceRecord();
        record.setAmount(new BigDecimal(100));
        Account handler = new Account();
        handler.setId(2);
        record.setHandler(handler);
        record.setCreatedAt(new Date());
        service.saveInvoice(7, record);
        Order order = service.findOne(7);
        assertEquals(2, order.getInvoiceRecords().size());
        OrderInvoiceRecord actual = order.getInvoiceRecords().get(1);
        assertNotNull(actual);
        assertEquals(100, actual.getAmount().intValue());
        assertEquals(2, actual.getHandler().getId());

        VipCardService vipCardService = TestHelper.Context.getBean(VipCardService.class);
        VipCard vipCard = vipCardService.findOne(1);
        assertEquals(0, vipCard.getInvoiceQuota().intValue());

        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(vipCard.getId());
        List<VipCardTransaction> transactions = vipCardService.searchTransactions(filter).getItems();
        assertEquals(7, transactions.size());
        VipCardTransaction transaction = transactions.get(6);
        assertEquals(VipCardTransactionType.INVOICE, transaction.getTransactionType());
        assertEquals(100, transaction.getInvoiceAmount().intValue());
    }

    /**
     * Tests the {@link OrderService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertOrder(2, service.findOne(2), true);
    }

    /**
     * Tests the {@link OrderService#findOne(String, Date)} method, finding by bill.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOneByBill() throws CoreServiceException{
        assertOrder(3, service.findOne("3", null), true);
    }

    /**
     * Tests the {@link OrderService#findOne(String, Date)} method, finding by bill and date.
     *
     * @throws Exception
     */
    @Test
    public void findOneByBillAndDate() throws Exception{
        assertOrder(3, service.findOne("3", TestHelper.parseDate(false, "2016-01-02")), true);
    }

    /**
     * Tests the {@link OrderService#findOne(String, Date)} method, finding by bill and date. No order is expected to be
     * found.
     *
     * @throws Exception
     */
    @Test
    public void findOneByBillAndDateWithNotFound() throws Exception{
        assertNull(service.findOne("3", TestHelper.parseDate(false, "2016-01-04")));
    }

    /**
     * Tests the {@link OrderService#findPaymentAmount(String)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findPaymentAmount() throws CoreServiceException{
        BigDecimal amount = service.findPaymentAmount("4");
        assertNotNull(amount);
        assertEquals(280, amount.intValue());
    }

    /**
     * Tests the {@link OrderService#checkByBill(String)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void checkByBill() throws CoreServiceException{
        assertTrue(service.checkByBill("1"));
        assertFalse(service.checkByBill("bad"));
    }

    /**
     * Tests the {@link OrderService#search(OrderSearchFilter)} method, searching by price range with pagination and
     * sort.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        OrderSearchFilter filter = new OrderSearchFilter();
        filter.setPriceFrom(BigDecimal.ZERO);
        filter.setPriceTo(new BigDecimal(300));
        filter.setHqId(TestHelper.HQ_ID);
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortOrder(SortOrder.DESC);
        filter.setSortBy("created_at");
        SearchResult<Order> result = service.search(filter);
        assertEquals(4, result.getCount());
        assertEquals(2, result.getItems().size());
        assertEquals(2, result.getTotalPages());
        assertOrder(3, result.getItems().get(0), false);
        assertOrder(1, result.getItems().get(1), false);
    }

    /**
     * Tests the {@link OrderService#search(OrderSearchFilter)} method, searching all orders with sort but no
     * pagination.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchAll() throws CoreServiceException{
        OrderSearchFilter filter = new OrderSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<Order> result = service.search(filter);
        assertEquals(12, result.getCount());
        assertEquals(12, result.getItems().size());
        assertEquals(1, result.getTotalPages());
        for(int seed = 12; seed >= 1; seed--){
            Order actual = result.getItems().get(12 - seed);
            assertEquals(seed, actual.getId());
            assertOrder(seed, actual, false);
        }
    }

    /**
     * Tests the {@link OrderService#getTotal(OrderSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getTotal() throws CoreServiceException{
        OrderSearchFilter filter = new OrderSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        Order order = service.getTotal(filter);
        assertEquals(3260, order.getPrice().intValue());
        assertEquals(3500, order.getOriginalPrice().intValue());
        assertEquals(1540, order.getCoupon().intValue());
        assertEquals(3360, order.getTotalInvoices().intValue());
    }

    /**
     * Tests the {@link OrderService#searchInvoiceRecords(OrderSearchFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void searchInvoiceRecord() throws Exception{
        OrderSearchFilter filter = new OrderSearchFilter();
        filter.setCreatedAtFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setCreatedAtTo(TestHelper.parseDate(false, "2016-01-10"));
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("oin.created_at");
        filter.setSortOrder(SortOrder.DESC);
        filter.setHqId(TestHelper.HQ_ID);
        SearchResult<Order> result = service.searchInvoiceRecords(filter);
        assertEquals(2, result.getItems().size());
        assertEquals(3, result.getTotalPages());
        assertEquals(6, result.getCount());
        assertEquals(6, result.getItems().get(0).getId());
        assertEquals(5, result.getItems().get(1).getId());
        for(Order order : result.getItems()){
            assertEquals(String.valueOf(order.getId()), order.getBill());
            assertEquals(order.getId() <= 4 ? 1 : 2, order.getBranchId().intValue());
            assertEquals(order.getId() == 6 ? 560 : 280,
                    order.getInvoiceRecords().get(0).getAmount().intValue());
            assertEquals(order.getId() == 6 ? 2 : 1,
                    order.getInvoiceRecords().get(0).getHandler().getId());
        }
    }
}
