package com.irh.transcation.services.impl;

import com.irh.transaction.services.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class OrderServiceTest extends BaseCase{

    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Order order)
     */
    @Test
    public void testSave() throws Exception{
//        Order order = new Order();
//        order.setHqId(1);
//        order.setBranchId(1L);
//        order.setBill("bill");
//        order.setCreatedAt(new Date());
//        order.setChange(new BigDecimal(50));
//        order.setDevice("device");
//        order.setDiscount(0.8F);
//        order.setOriginalBill("originnal_bill");
//        order.setNumberOfPeople(3);
//        order.setRemark("remark");
//        order.setStatus(OrderStatus.COMPLETE);
//        List<OrderPaymentRecord> paymentRecords = new ArrayList<>();
//        OrderPaymentRecord orderPaymentRecord = new OrderPaymentRecord();
//        orderPaymentRecord.setAmount(new BigDecimal(50));
//        orderPaymentRecord.setPayType(PayType.CASH);
//        paymentRecords.add(orderPaymentRecord);
//        order.setPaymentRecords(paymentRecords);
//        orderService.save(order);
    }

    /**
     * Method: saveInvoice(long orderId, OrderInvoiceRecord invoiceRecord)
     */
    @Test
    public void testSaveInvoice() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: updateStatus(long id, OrderStatus status)
     */
    @Test
    public void testUpdateStatus() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: findOne(String bill, Date date)
     */
    @Test
    public void testFindOneForBillDate() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOneId() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: checkByBill(String bill)
     */
    @Test
    public void testCheckByBill() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: search(OrderSearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: searchInvoiceRecords(OrderSearchFilter filter)
     */
    @Test
    public void testSearchInvoiceRecords() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: countInvoiceAmount(OrderSearchFilter filter)
     */
    @Test
    public void testCountInvoiceAmount() throws Exception{
        //TODO: Test goes here... 
    }


    /**
     * Method: checkOrder(Order order)
     */
    @Test
    public void testCheckOrder() throws Exception{
        //TODO: Test goes here... 
            /* 
            try { 
               Method method = OrderServiceImpl.getClass().getMethod("checkOrder", Order.class); 
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

} 
