package com.irh.transcation.services;

import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.VipCardReportService;
import com.irh.transaction.services.VipCardService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for {@link VipCardService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class VipCardReportServiceTest extends BaseTest{

    /**
     * The {@link VipCardReportService} instance to test.
     */
    private VipCardReportService service;

    private static void assertTotalReport(long seed, VipCardReport report){
        assertEquals(200 * seed, report.getTotalRecharge().intValue());
        assertEquals(100 * seed, report.getCashRecharge().intValue());
        assertEquals(100 * seed, report.getUnipayRecharge().intValue());
        assertEquals(50 * seed, report.getConsume().intValue());
        assertEquals(200 * seed, report.getInvoice().intValue());
    }

    private static void assertBranchReport(long branchId, VipCardReport report){
        assertEquals(branchId, report.getBranch().getId());
        assertEquals("branch" + branchId, report.getBranch().getName());
        assertEquals(200 * branchId, report.getTotalRecharge().intValue());
        assertEquals(100 * branchId, report.getCashRecharge().intValue());
        assertEquals(100 * branchId, report.getUnipayRecharge().intValue());
        assertEquals(50 * branchId, report.getConsume().intValue());
        assertEquals(200 * branchId, report.getInvoice().intValue());
    }

    private static void assertSummaryReport(long vipCardId, VipCardReport report){
        assertEquals(vipCardId, report.getVipCard().getId());
        assertEquals("card" + vipCardId, report.getVipCard().getName());
        assertEquals(String.valueOf(vipCardId), report.getVipCard().getNumber());
        assertEquals(200 * vipCardId, report.getVipCard().getBalance().intValue());
        assertEquals(100 * vipCardId, report.getVipCard().getInvoiceQuota().intValue());
        assertEquals(200 * vipCardId, report.getTotalRecharge().intValue());
        assertEquals(100 * vipCardId, report.getCashRecharge().intValue());
        assertEquals(100 * vipCardId, report.getUnipayRecharge().intValue());
        assertEquals(50 * vipCardId, report.getConsume().intValue());
        assertEquals(200 * vipCardId, report.getInvoice().intValue());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(VipCardReportService.class);
    }

    /**
     * Tests the {@link VipCardReportService#search(ReportSearchFilter)} method, searching the summary reports
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchSummaryReport() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        SearchResult<VipCardReport> result = service.search(filter);
        assertEquals(4, result.getCount());
        assertEquals(4, result.getItems().size());
        for(int i = 0; i < 2; i++){
            assertSummaryReport(i + 1, result.getItems().get(i));
        }
    }

    /**
     * Tests the {@link VipCardReportService#search(ReportSearchFilter)} method, searching the branch reports.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchBranchReport() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupByBranch(true);
        SearchResult<VipCardReport> result = service.search(filter);
        assertEquals(4, result.getCount());
        assertEquals(4, result.getItems().size());
        for(int i = 0; i < 2; i++){
            assertBranchReport(i + 1, result.getItems().get(i));
        }
    }

    /**
     * Tests the {@link VipCardReportService#getTotal(ReportSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getTotal() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        VipCardReport report = service.getTotal(filter);
        assertTotalReport(3, report);
    }

    /**
     * Tests the {@link VipCardReportService#getTotal(ReportSearchFilter)} method, getting the total of reports grouped
     * by branch.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getTotalByBranch() throws CoreServiceException{
        ReportSearchFilter filter = new ReportSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupByBranch(true);
        VipCardReport report = service.getTotal(filter);
        assertTotalReport(3, report);
    }
}
