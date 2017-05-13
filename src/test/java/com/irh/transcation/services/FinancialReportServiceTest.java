package com.irh.transcation.services;

import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.FinancialReportService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link FinancialReportService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class FinancialReportServiceTest extends BaseTest{

    /**
     * The {@link FinancialReportService} instance to test.
     */
    private FinancialReportService service;

    private static void assertTotalReport(long seed, FinancialReport report){
        assertEquals(10000 * seed, report.getTotalSales().intValue());
        assertEquals(1000 * seed, report.getCashSales().intValue());
        assertEquals(1000 * seed, report.getUnipaySales().intValue());
        assertEquals(1000 * seed, report.getAlipaySales().intValue());
        assertEquals(1000 * seed, report.getWechatSales().intValue());
        assertEquals(1000 * seed, report.getVipCardSales().intValue());
        assertEquals(1000 * seed, report.getSignedBills().intValue());
        assertEquals(1000 * seed, report.getCoupons().intValue());
        assertEquals(100 * seed, report.getDiscounts().intValue());
        assertEquals(100 * seed, report.getTotalRefund().intValue());
        assertEquals(25 * seed, report.getCashRefund().intValue());
        assertEquals(25 * seed, report.getUnipayRefund().intValue());
        assertEquals(25 * seed, report.getAlipayRefund().intValue());
        assertEquals(25 * seed, report.getWechatRefund().intValue());
        assertEquals(1000 * seed, report.getTotalRecharge().intValue());
        assertEquals(500 * seed, report.getCashRecharge().intValue());
        assertEquals(500 * seed, report.getUnipayRecharge().intValue());
        assertEquals(100 * seed, report.getOrders());
        assertEquals(100 * seed, report.getInvoices().intValue());
        assertEquals(100 * seed, report.getGifts().intValue());
    }

    private static void assertGroupedReport(long expectedBranchId, FinancialReport report){
        assertNotNull(report.getBranch());
        assertEquals(expectedBranchId, report.getBranch().getId());
        assertEquals("branch" + expectedBranchId, report.getBranch().getName());

        long seed = expectedBranchId == 1 ? 3 : 7;
        assertEquals(10000 * seed, report.getTotalSales().intValue());
        assertEquals(1000 * seed, report.getCashSales().intValue());
        assertEquals(1000 * seed, report.getUnipaySales().intValue());
        assertEquals(1000 * seed, report.getAlipaySales().intValue());
        assertEquals(1000 * seed, report.getWechatSales().intValue());
        assertEquals(1000 * seed, report.getVipCardSales().intValue());
        assertEquals(1000 * seed, report.getSignedBills().intValue());
        assertEquals(1000 * seed, report.getCoupons().intValue());
        assertEquals(100 * seed, report.getDiscounts().intValue());
        assertEquals(100 * seed, report.getTotalRefund().intValue());
        assertEquals(25 * seed, report.getCashRefund().intValue());
        assertEquals(25 * seed, report.getUnipayRefund().intValue());
        assertEquals(25 * seed, report.getAlipayRefund().intValue());
        assertEquals(25 * seed, report.getWechatRefund().intValue());
        assertEquals(1000 * seed, report.getTotalRecharge().intValue());
        assertEquals(500 * seed, report.getCashRecharge().intValue());
        assertEquals(500 * seed, report.getUnipayRecharge().intValue());
        assertEquals(100 * seed, report.getOrders());
        assertEquals(100 * seed, report.getInvoices().intValue());
        assertEquals(100 * seed, report.getGifts().intValue());
    }

    private static void assertReport(long expectedId, FinancialReport report) throws Exception{
        assertEquals(expectedId, report.getId());
        assertEquals(TestHelper.HQ_ID, report.getHqId());
        assertEquals(expectedId < 6 ? 1L : null, report.getGroupId());
        assertNull(report.getBranch());
        assertEquals(TestHelper.parseDate(false, "2016-02-0" + expectedId), report.getDate());
        assertEquals(10000 * expectedId, report.getTotalSales().intValue());
        assertEquals(1000 * expectedId, report.getCashSales().intValue());
        assertEquals(1000 * expectedId, report.getUnipaySales().intValue());
        assertEquals(1000 * expectedId, report.getAlipaySales().intValue());
        assertEquals(1000 * expectedId, report.getWechatSales().intValue());
        assertEquals(1000 * expectedId, report.getVipCardSales().intValue());
        assertEquals(1000 * expectedId, report.getSignedBills().intValue());
        assertEquals(1000 * expectedId, report.getCoupons().intValue());
        assertEquals(100 * expectedId, report.getDiscounts().intValue());
        assertEquals(100 * expectedId, report.getTotalRefund().intValue());
        assertEquals(25 * expectedId, report.getCashRefund().intValue());
        assertEquals(25 * expectedId, report.getUnipayRefund().intValue());
        assertEquals(25 * expectedId, report.getAlipayRefund().intValue());
        assertEquals(25 * expectedId, report.getWechatRefund().intValue());
        assertEquals(1000 * expectedId, report.getTotalRecharge().intValue());
        assertEquals(500 * expectedId, report.getCashRecharge().intValue());
        assertEquals(500 * expectedId, report.getUnipayRecharge().intValue());
        assertEquals(100 * expectedId, report.getOrders());
        assertEquals(100 * expectedId, report.getInvoices().intValue());
        assertEquals(100 * expectedId, report.getGifts().intValue());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        ScriptUtils.executeSqlScript(TestHelper.Context.getBean(DataSource.class).getConnection(),
                new ClassPathResource("financial-report.sql"));
        service = TestHelper.Context.getBean(FinancialReportService.class);
    }

    /**
     * Tests the {@link FinancialReportService#create(long, Date)} method.
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception{
        service.create(TestHelper.HQ_ID, TestHelper.parseDate(false, "2016-01-01"));

        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setDateFrom(TestHelper.parseDate(false, "2016-01-01"));
        filter.setDateTo(TestHelper.parseDate(false, "2016-01-01"));
        SearchResult<FinancialReport> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        FinancialReport report = result.getItems().get(0);
        assertEquals(TestHelper.HQ_ID, report.getHqId());
        assertNull(report.getGroupId());
        assertNull(report.getBranch());
        assertEquals(8, report.getOrders());
        assertEquals(3260, report.getTotalSales().intValue());
        assertEquals(550, report.getCashSales().intValue());
        assertEquals(400, report.getUnipaySales().intValue());
        assertEquals(240, report.getAlipaySales().intValue());
        assertEquals(110, report.getWechatSales().intValue());
        assertEquals(1540, report.getCoupons().intValue());
        assertEquals(140, report.getVipCardSales().intValue());
        assertEquals(280, report.getSignedBills().intValue());
        assertEquals(840, report.getGifts().intValue());
        assertEquals(240, report.getDiscounts().intValue());
        assertEquals(3560, report.getInvoices().intValue());
        assertEquals(400, report.getTotalRecharge().intValue());
        assertEquals(200, report.getCashRecharge().intValue());
        assertEquals(200, report.getUnipayRecharge().intValue());
        assertEquals(100, report.getTotalRefund().intValue());
        assertEquals(10, report.getCashRefund().intValue());
        assertEquals(20, report.getUnipayRefund().intValue());
        assertEquals(40, report.getAlipayRefund().intValue());
        assertEquals(30, report.getWechatRefund().intValue());
    }

    /**
     * Tests the {@link FinancialReportService#search(ReportSearchFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void search() throws Exception{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupId(1L);
        filter.setBranchId(1L);
        filter.setDateTo(TestHelper.parseDate(false, "2016-02-04"));
        filter.setDateFrom(TestHelper.parseDate(false, "2016-02-02"));
        SearchResult<FinancialReport> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getCount());
        assertReport(2, result.getItems().get(0));
    }

    /**
     * Tests the  {@link FinancialReportService#search(ReportSearchFilter)} method, grouping by branch.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchGroupByBranch() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupByBranch(true);
        SearchResult<FinancialReport> result = service.search(filter);
        assertEquals(2, result.getItems().size());
        assertEquals(2, result.getCount());
        for(int i = 0; i < result.getItems().size(); i++){
            assertGroupedReport(i + 1, result.getItems().get(i));
        }
    }

    /**
     * Tests the {@link FinancialReportService#getTotal(ReportSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getTotal() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        FinancialReport report = service.getTotal(filter);
        assertTotalReport(6, report);
    }

    /**
     * Tests the {@link FinancialReportService#getTotal(ReportSearchFilter)} method, get the total of reports grouped by
     * branch.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getTotalByBranch() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupByBranch(true);
        FinancialReport report = service.getTotal(filter);
        assertTotalReport(10, report);
    }
}
