package com.irh.transcation.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The test suite that runs all tests.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountServiceTest.class,
        PermissionServiceTest.class,
        BranchGroupServiceTest.class,
        BranchServiceTest.class,
        BranchProductStatusRecordServiceTest.class,
        RoleServiceTest.class,
        CategoryServiceTest.class,
        ProductServiceTest.class,
        ProductOptionGroupServiceTest.class,
        PrintTypeServiceTest.class,
        BranchTableServiceTest.class,
        ProductDiscountServiceTest.class,
        OrderServiceTest.class,
        VipCardServiceTest.class,
        SalesStatisticServiceTest.class,
        FinancialReportServiceTest.class,
        VipCardReportServiceTest.class,
        BranchShiftReportTest.class
})
public class TestSuite{
}
