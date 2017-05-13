package com.irh.transcation.services;

import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.order.OrderServiceType;
import com.irh.transaction.model.statistic.*;
import com.irh.transaction.services.CorePersistenceException;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.SalesStatisticService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link SalesStatisticService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class SalesStatisticServiceTest extends BaseTest{

    /**
     * The {@link SalesStatisticService} instance to test.
     */
    private SalesStatisticService service;

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(SalesStatisticService.class);
    }

    /**
     * Tests the {@link SalesStatisticService#getBranchSalesSummaries(DateFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void getBranchSalesSummaries() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        List<BranchSalesSummary> result = service.getBranchSalesSummaries(filter);
        assertEquals(2, result.size());
        for(int i = 0; i < 2; i++){
            BranchSalesSummary salesSummary = result.get(i);
            long expectedBranchId = i == 0 ? 1 : 2;
            assertEquals(expectedBranchId, salesSummary.getBranch().getId());
            assertEquals("branch" + expectedBranchId, salesSummary.getBranch().getName());
            assertEquals(expectedBranchId == 2 ? 1680 : 770, salesSummary.getSales().intValue());
            assertEquals(expectedBranchId == 2 ? 4 : 2, salesSummary.getCount());
            assertEquals(expectedBranchId == 2 ? 420 : 385, salesSummary.getAverage().intValue());
            assertEquals(salesSummary.getTurnRate().compareTo(200f), expectedBranchId == 2 ? 0 : -1);
        }
    }

    /**
     * Failure test for {@link SalesStatisticService#getBranchSalesSummaries(DateFilter)} method ,
     * with a filter is null ,{@link IllegalArgumentException} is excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getBranchSalesSummariesIsNull1() throws CoreServiceException{
        service.getBranchSalesSummaries(null);
        fail();
    }

    /**
     * Failure test for {@link SalesStatisticService#getBranchSalesSummaries(DateFilter)} method ,
     * with a filter.hqId is null ,{@link IllegalArgumentException} is excepted.
     *
     * @throws CoreServiceException
     * @throws ParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getBranchSalesSummariesIsNull2() throws CoreServiceException, ParseException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getBranchSalesSummaries(filter);
        fail();
    }

    /**
     * Tests the {@link SalesStatisticService#getTotalSalesDetails(DateFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void getTotalSalesDetails() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        TotalSalesDetails result = service.getTotalSalesDetails(filter);

        final int[] HOURLY_SALES = new int[]{
                280, -30, 560, -40, 280, 560, 280, 560
        };
        final HashMap<PayType, Integer> PAY_TYPE_SALES = new HashMap<>();
        PAY_TYPE_SALES.put(PayType.ALIPAY, 240);
        PAY_TYPE_SALES.put(PayType.WECHAT, 110);
        PAY_TYPE_SALES.put(PayType.CASH, 420);
        PAY_TYPE_SALES.put(PayType.UNI_PAY, 140);
        PAY_TYPE_SALES.put(PayType.SIGNED, 280);
        PAY_TYPE_SALES.put(PayType.VIPCARD, 140);
        PAY_TYPE_SALES.put(PayType.COUPON, 1120);
        final HashMap<PayType, Integer> PAY_TYPE_ORDERS = new HashMap<>();
        PAY_TYPE_ORDERS.put(PayType.ALIPAY, 1);
        PAY_TYPE_ORDERS.put(PayType.WECHAT, 1);
        PAY_TYPE_ORDERS.put(PayType.CASH, 2);
        PAY_TYPE_ORDERS.put(PayType.UNI_PAY, 1);
        PAY_TYPE_ORDERS.put(PayType.SIGNED, 1);
        PAY_TYPE_ORDERS.put(PayType.VIPCARD, 1);
        PAY_TYPE_ORDERS.put(PayType.COUPON, 5);
        assertEquals(8, result.getHourlyDetails().size());
        for(int i = 0; i < result.getHourlyDetails().size(); i++){
            HourlyTotalSalesDetailItem item = result.getHourlyDetails().get(i);
            assertEquals("The sales of hour " + item.getHour()
                            + " is incorrect.", HOURLY_SALES[i],
                    item.getSales().intValue());
        }
        assertEquals(3, result.getServiceTypeDetails().size());
        for(TotalSalesDetailItem item : result.getServiceTypeDetails()){
            assertEquals("The orders of the service type " + item.getKey() + " is incorrect.",
                    2, item.getCount());
            assertEquals("The sales of the service type " + item.getKey() + " is incorrect.",
                    OrderServiceType.RESTAURANT.toString().equals(item.getKey().toString()) ? 770 : 840,
                    item.getSales().intValue());
        }
        assertEquals(7, result.getPayTypeDetails().size());
        for(TotalSalesDetailItem item : result.getPayTypeDetails()){
            PayType key = PayType.valueOf(item.getKey().toString());
            assertEquals("The sales of the pay type " + item.getKey() + " is incorrect.",
                    PAY_TYPE_SALES.get(key).intValue(), item.getSales().intValue());
            assertEquals("The orders of the pay type " + item.getKey() + " is incorrect.",
                    PAY_TYPE_ORDERS.get(key).intValue(), item.getCount());
        }
        assertEquals(3, result.getPlatformDetails().size());
        for(TotalSalesDetailItem item : result.getPlatformDetails()){
            assertEquals("The orders of the platform " + item.getKey() + " is incorrect.",
                    2, item.getCount());
            assertEquals("The sales of the platform " + item.getKey() + " is incorrect.",
                    Platform.POS.toString().equals(item.getKey().toString()) ? 770 : 840,
                    item.getSales().intValue());
        }
    }

    /**
     * Failure test for {@link SalesStatisticService#getTotalSalesDetails(DateFilter)} method ,with filter is null,
     * {@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getTotalSalesDetailsIsNull1() throws CoreServiceException{
        service.getTotalSalesDetails(null);
        fail();
    }

    /**
     * Failure test for {@link SalesStatisticService#getTotalSalesDetails(DateFilter)} method , with a filter.hqId is null,
     * {@link IllegalArgumentException} excepted.
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getTotalSalesDetailsIsNull2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getTotalSalesDetails(filter);
        fail();
    }

    /**
     * Tests the {@link SalesStatisticService#getProductSalesSummaries(DateFilter, Long)} method.
     *
     * @throws Exception
     */
    @Test
    public void getProductSalesSummaries() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        List<ProductSalesSummary> result = service.getProductSalesSummaries(filter, 1L);
        assertEquals(2, result.size());
        for(ProductSalesSummary item : result){
            long productId = item.getProduct().getId();
            assertEquals("product" + productId, item.getProduct().getName());
            assertEquals("product-en" + productId, item.getProduct().getInterName());
            assertEquals(productId == 1 ? 0 : 2170, item.getSales().intValue());
            assertEquals(productId == 1 ? 6 : 9, item.getCount());
            assertTrue(Float.compare(productId == 1 ? 100 : 150f, item.getOrderRate()) == 0);
        }
    }

    /**
     * Tests the {@link SalesStatisticService#getProductSalesSummaries(DateFilter, Long)} method.
     *
     * @throws Exception
     */
    @Test
    public void getProductSalesSummaries1() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        List<ProductSalesSummary> result = service.getProductSalesSummaries(filter, 1L);
        assertEquals(2, result.size());
        for(ProductSalesSummary item : result){
            long productId = item.getProduct().getId();
            assertEquals("product" + productId, item.getProduct().getName());
            assertEquals("product-en" + productId, item.getProduct().getInterName());
            assertEquals(productId == 1 ? 0 : 2660, item.getSales().intValue());
            assertEquals(productId == 1 ? 8 : 10, item.getCount());
            assertTrue(Float.compare(productId == 1 ? 100 : 125, item.getOrderRate()) == 0);
        }
    }

    /**
     * Failure test for {@link SalesStatisticService#getProductSalesSummaries(DateFilter, Long)} method ,
     * with a filter is null ,{@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getProductSalesSummariesIsNull1() throws CoreServiceException{
        service.getProductSalesSummaries(null, 1L);
        fail();
    }

    /**
     * Failure test for {@link SalesStatisticService#getProductSalesSummaries(DateFilter, Long)} method ,
     * with a filter.hqId is null ,{@link IllegalArgumentException} excepted.
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getProductSalesSummariesIsNull2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getProductSalesSummaries(filter, 1L);
        fail();
    }

    /**
     * Tests the {@link SalesStatisticService#getProductSalesDetails(DateFilter, long)} method.
     *
     * @throws Exception
     */
    @Test
    public void getProductSalesDetails1() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        ProductSalesDetails result = service.getProductSalesDetails(filter, 1);
        assertEquals(1, result.getProductId());
        assertEquals(1, result.getServiceTypeDetails().size());
        assertEquals(0, result.getServiceTypeDetails().get(0).getSales().intValue());
        assertEquals(1, result.getOptionDetails().size());
        assertEquals(0, result.getOptionDetails().get(0).getSales().intValue());
        assertEquals(6, result.getDailySummaries().size());
    }

    /**
     * Failure test for {@link SalesStatisticService#getProductSalesDetails(DateFilter, long)} method ,
     * with an filter is null ,{@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getProductSalesDetailsIsNull1() throws CoreServiceException{
        service.getProductSalesDetails(null, 1);
        fail();
    }

    /**
     * Failure test for {@link SalesStatisticService#getProductSalesDetails(DateFilter, long)} method ,
     * with a filter.hqId is null ,{@link IllegalArgumentException} excepted .
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getProductSalesDetailsIsNull2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getProductSalesDetails(filter, 1);
        fail();
    }

    /**
     * Tests the {@link SalesStatisticService#getProductSalesDetails(DateFilter, long)} method.
     *
     * @throws Exception
     */
    @Test
    public void getProductSalesDetails2() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        ProductSalesDetails result = service.getProductSalesDetails(filter, 2);
        assertEquals(2, result.getProductId());
        assertEquals(1, result.getServiceTypeDetails().size());
        assertEquals(2170, result.getServiceTypeDetails().get(0).getSales().intValue());
        assertEquals(1, result.getOptionDetails().size());
        assertEquals(2170, result.getOptionDetails().get(0).getSales().intValue());
        assertEquals(6, result.getDailySummaries().size());
    }

    /**
     * Tests the {@link SalesStatisticService#getDiscountSalesSummaries(DateFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void getDiscountSalesSummaries() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        List<ProductDiscountSalesSummary> result = service.getDiscountSalesSummaries(filter);
        assertEquals(2, result.size());
        for(ProductDiscountSalesSummary item : result){
            assertEquals("discount" + item.getDiscountId(), item.getDiscountName());
            assertEquals(item.getDiscountId() == 1 ? 2 : 4, item.getCount());
        }
    }

    /**
     * Failure test for {@link SalesStatisticService#getDiscountSalesSummaries(DateFilter)} method ,
     * with a filter is null , {@link IllegalArgumentException} is excepted.
     *
     * @throws CorePersistenceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getDiscountSalesSummariesIsNull1() throws CoreServiceException{
        service.getDiscountSalesSummaries(null);
        fail();
    }


    /**
     * Failure test for {@link SalesStatisticService#getDiscountSalesSummaries(DateFilter)} method ,
     * with a filter.hqId is null ,{@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     * @throws ParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getDiscountSalesSummariesIsNull2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getDiscountSalesSummaries(filter);
        fail();
    }

    /**
     * Tests the {@link SalesStatisticService#getDiscountSalesSummaries(DateFilter)} method, filtered by branch.
     *
     * @throws Exception
     */
    @Test
    public void getDiscountSalesSummariesByBranch() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setBranchId(1L);
        List<ProductDiscountSalesSummary> result = service.getDiscountSalesSummaries(filter);
        assertEquals(1, result.size());
        ProductDiscountSalesSummary discountSalesSummary = result.get(0);
        assertEquals(4, discountSalesSummary.getCount());
        assertEquals(1, discountSalesSummary.getDiscountId());
        assertEquals("discount1", discountSalesSummary.getDiscountName());
    }

    /**
     * test for {@link SalesStatisticService#getCategorySalesSummaries(DateFilter)} methoid .
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test
    public void getCategorySalesSummaries() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        List<CategorySalesSummary> result = service.getCategorySalesSummaries(filter);
        assertEquals(2, result.size());
        for(CategorySalesSummary item : result){
            long categoryId = item.getCategory().getId();
            assertEquals("c" + categoryId, item.getCategory().getName());
            assertEquals("ce" + categoryId, item.getCategory().getInterName());
            assertEquals(categoryId == 1 ? 2170 : 2170, item.getSales().intValue());
            assertEquals(categoryId == 1 ? 15 : 15, item.getCount());
            System.out.println(item.getOrderRate());
            assertTrue(Float.compare(categoryId == 1 ? 250 : 250, item.getOrderRate()) == 0);
        }
    }

    /**
     * test for {@link SalesStatisticService#getCategorySalesSummaries(DateFilter)} methoid .
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test
    public void getCategorySalesSummaries1() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        List<CategorySalesSummary> result = service.getCategorySalesSummaries(filter);
        assertEquals(2, result.size());
        for(CategorySalesSummary item : result){
            long categoryId = item.getCategory().getId();
            assertEquals("c" + categoryId, item.getCategory().getName());
            assertEquals("ce" + categoryId, item.getCategory().getInterName());
            assertEquals(categoryId == 1 ? 2660 : 2660, item.getSales().intValue());
            assertEquals(categoryId == 1 ? 18 : 18, item.getCount());
            System.out.println(item.getOrderRate());
            assertTrue(Float.compare(categoryId == 1 ? 225 : 225, item.getOrderRate()) == 0);
        }
    }

    /**
     * Failure test for {@link SalesStatisticService#getCategorySalesSummaries(DateFilter)} method ,
     * with a filter is null , {@link IllegalArgumentException} excepted.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCategorySalesSummariesIsNull1() throws CoreServiceException{
        service.getCategorySalesSummaries(null);
        fail();
    }

    /**
     * Failure test for {@link SalesStatisticService#getCategorySalesSummaries(DateFilter)} method,
     * with a filter.hqId is null, {@link IllegalArgumentException} excepted.
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCategorySalesSummariesIsNull2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(null);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        service.getCategorySalesSummaries(filter);
        fail();
    }

    /**
     * Test for {@link SalesStatisticService#getCategorySalesDetails(DateFilter, long)} method.
     *
     * @throws ParseException
     * @throws CorePersistenceException
     */
    @Test
    public void getCategorySalesDetails1() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        CategorySalesDetails result = service.getCategorySalesDetails(filter, 1L);
        assertEquals(1, result.getCategoryId());
        assertEquals(2, result.getServiceTypeDetails().size());
        assertEquals(2170, result.getServiceTypeDetails().get(0).getSales().intValue());
        assertEquals(2, result.getOptionDetails().size());
        assertEquals(2170, result.getOptionDetails().get(0).getSales().intValue());
        assertEquals(6, result.getDailySummaries().size());
    }

    /**
     * Test for {@link SalesStatisticService#getCategorySalesDetails(DateFilter, long)} method.
     *
     * @throws ParseException
     * @throws CoreServiceException
     */
    @Test
    public void getCategorySalesDetails2() throws ParseException, CoreServiceException{
        DateFilter filter = new DateFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-03"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-10"));
        CategorySalesDetails result = service.getCategorySalesDetails(filter, 2L);
        assertEquals(2, result.getCategoryId());
        assertEquals(2, result.getServiceTypeDetails().size());
        assertEquals(2170, result.getServiceTypeDetails().get(0).getSales().intValue());
        assertEquals(2, result.getOptionDetails().size());
        assertEquals(2170, result.getOptionDetails().get(0).getSales().intValue());
        assertEquals(6, result.getDailySummaries().size());
    }
}
