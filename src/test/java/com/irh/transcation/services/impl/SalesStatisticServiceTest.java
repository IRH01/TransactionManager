package com.irh.transcation.services.impl;

import com.irh.transaction.services.SalesStatisticService;
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
 * SalesStatisticServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class SalesStatisticServiceTest extends BaseCase{

    @Autowired
    private SalesStatisticService salesStatisticService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: getBranchSalesSummaries(DateFilter filter)
     */
    @Test
    public void testGetBranchSalesSummaries() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getProductSalesSummaries(DateFilter filter, Long categoryId)
     */
    @Test
    public void testGetProductSalesSummaries() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getProductSalesDetails(DateFilter filter, long productId)
     */
    @Test
    public void testGetProductSalesDetails() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getTotalSalesDetails(DateFilter filter)
     */
    @Test
    public void testGetTotalSalesDetails() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getCategorySalesSummaries(DateFilter filter)
     */
    @Test
    public void testGetCategorySalesSummaries() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getCategorySalesDetails(DateFilter filter, long categoryId)
     */
    @Test
    public void testGetCategorySalesDetails() throws Exception{
        //TODO: Test goes here... 
    }

    /**
     * Method: getDiscountSalesSummaries(DateFilter filter)
     */
    @Test
    public void testGetDiscountSalesSummaries() throws Exception{
        //TODO: Test goes here... 
    }


    /**
     * Method: checkFilter(DateFilter filter)
     */
    @Test
    public void testCheckFilter() throws Exception{
        //TODO: Test goes here... 
            /* 
            try { 
               Method method = SalesStatisticServiceImpl.getClass().getMethod("checkFilter", DateFilter.class);
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

} 
