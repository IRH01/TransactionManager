package com.irh.transcation.services;

import com.irh.transaction.dto.search.DateFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.statistic.BranchShiftReport;
import com.irh.transaction.services.BranchShiftReportService;
import com.irh.transaction.services.EntityExistsException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Contains tests for {@link BranchShiftReportService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchShiftReportTest extends BaseTest{

    /**
     * The {@link BranchShiftReportService} instance to test.
     */
    private BranchShiftReportService service;

    private static void assertReport(long expectedId, BranchShiftReport report){
        assertEquals(10000 * expectedId, report.getTotalSales().intValue());
        assertEquals(1000 * expectedId, report.getCashSales().intValue());
        assertEquals(1000 * expectedId, report.getUnipaySales().intValue());
        assertEquals(1000 * expectedId, report.getAlipaySales().intValue());
        assertEquals(1000 * expectedId, report.getWechatSales().intValue());
        assertEquals(1000 * expectedId, report.getSignedBills().intValue());
        assertEquals(1000 * expectedId, report.getVipCardSales().intValue());
        assertEquals(1000 * expectedId, report.getCoupons().intValue());
        assertEquals(100 * expectedId, report.getDiscounts().intValue());
        assertEquals(500 * expectedId, report.getCashRecharge().intValue());
        assertEquals(500 * expectedId, report.getUnipayRecharge().intValue());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(BranchShiftReportService.class);
    }

    /**
     * Tests the {@link BranchShiftReportService#save(BranchShiftReport)} method.
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception{
        BranchShiftReport report = new BranchShiftReport();
        Branch branch = new Branch();
        branch.setId(4);
        report.setBranch(branch);
        Account account = new Account();
        account.setId(4);
        report.setAccount(account);
        report.setStartTime(TestHelper.parseDate(true, "2016-01-07 10:00"));
        report.setEndTime(TestHelper.parseDate(true, "2016-01-07 22:00"));
        report.setTotalSales(new BigDecimal("70000"));
        report.setCashSales(new BigDecimal("7000"));
        report.setUnipaySales(new BigDecimal("7000"));
        report.setAlipaySales(new BigDecimal("7000"));
        report.setWechatSales(new BigDecimal("7000"));
        report.setVipCardSales(new BigDecimal("7000"));
        report.setSignedBills(new BigDecimal("7000"));
        report.setCoupons(new BigDecimal("7000"));
        report.setDiscounts(new BigDecimal("700"));
        report.setCashRecharge(new BigDecimal("3500"));
        report.setUnipayRecharge(new BigDecimal("3500"));
        report.setDevice(7);
        service.save(report);

        DateFilter filter = new DateFilter();
        filter.setBranchId(4L);
        SearchResult<BranchShiftReport> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertReport(7, result.getItems().get(0));
    }

    /**
     * Failure test for the {@link BranchShiftReportService#save(BranchShiftReport)} method, saving with a report of the
     * same branch, device and start time exists. {@link EntityExistsException} is expected.
     *
     * @throws Exception
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws Exception{
        BranchShiftReport report = new BranchShiftReport();
        Branch branch = new Branch();
        branch.setId(3);
        report.setBranch(branch);
        Account account = new Account();
        account.setId(5);
        report.setAccount(account);
        report.setStartTime(TestHelper.parseDate(true, "2016-01-05 10:00"));
        report.setEndTime(TestHelper.parseDate(true, "2016-01-07 22:00"));
        report.setTotalSales(new BigDecimal("70000"));
        report.setCashSales(new BigDecimal("7000"));
        report.setUnipaySales(new BigDecimal("7000"));
        report.setAlipaySales(new BigDecimal("7000"));
        report.setWechatSales(new BigDecimal("7000"));
        report.setVipCardSales(new BigDecimal("7000"));
        report.setSignedBills(new BigDecimal("7000"));
        report.setCoupons(new BigDecimal("7000"));
        report.setDiscounts(new BigDecimal("700"));
        report.setCashRecharge(new BigDecimal("3500"));
        report.setUnipayRecharge(new BigDecimal("3500"));
        report.setDevice(5);
        service.save(report);
        fail();
    }

    /**
     * Tests the {@link BranchShiftReportService#search(DateFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void search() throws Exception{
        DateFilter filter = new DateFilter();
        filter.setBranchId(2L);
        filter.setDateFrom(TestHelper.parseDate(true, "2016-01-03 18:00"));
        filter.setDateTo(TestHelper.parseDate(true, "2016-01-04 18:00"));
        SearchResult<BranchShiftReport> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertReport(4, result.getItems().get(0));
    }
}
