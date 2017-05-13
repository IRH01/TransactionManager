package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.VipCardService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * VipCardServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 23, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class VipCardServiceTest extends BaseCase{

    @Autowired
    private VipCardService vipCardService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after(){
    }

    /**
     * Method: save(VipCard vipCard)
     */
    @Test
    public void testSave(){
    }

    /**
     * Method: saveTransaction(VipCardTransaction transaction)
     */
    @Test
    public void testSaveTransaction(){
        //TODO: Test goes here... 
    }

    /**
     * Method: update(VipCard vipCard)
     */
    @Test
    public void testUpdate(){
        //TODO: Test goes here... 
    }

    /**
     * Method: updateStatus(long id, VipCardStatus status)
     */
    @Test
    public void testUpdateStatus(){
        //TODO: Test goes here... 
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne(){
        //TODO: Test goes here... 
    }

    /**
     * Method: search(VipCardSearchFilter filter)
     */
    @Test
    public void testSearch() throws CoreServiceException{
        VipCardSearchFilter filter = new VipCardSearchFilter();
        filter.setHqId(1L);
        filter.setBranchId(1L);
        filter.setStatus(VipCardStatus.DISABLED);
        SearchResult<VipCard> search = this.vipCardService.search(filter);
        Assert.assertEquals(0, search.getCount());
    }

    /**
     * Method: searchTransactions(VipCardTransactionSearchFilter filter)
     */
    @Test
    public void testSearchTransactions(){
        //TODO: Test goes here... 
    }

    /**
     * Method: replace(VipCard newCard, VipCard oldCard)
     */
    @Test
    public void testReplace(){
        //TODO: Test goes here... 
    }

    /**
     * Method: statisticsByStatus(VipCardSearchFilter filter, VipCardStatus status)
     */
    @Test
    public void testStatisticsByStatus() throws CoreServiceException{
        VipCardSearchFilter filter = new VipCardSearchFilter();
        filter.setHqId(1L);
        VipCardStatus status = VipCardStatus.ACTIVE;
        long result = this.vipCardService.statisticsByStatus(filter, status);
        Assert.assertEquals(1, result);
    }
} 
