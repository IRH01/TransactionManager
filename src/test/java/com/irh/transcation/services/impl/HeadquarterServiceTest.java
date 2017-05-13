package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.services.HeadquarterService;
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
 * HeadquarterServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class HeadquarterServiceTest extends BaseCase{

    @Autowired
    private HeadquarterService headquarterService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Headquarter headquarter)
     */
    @Test
    public void testSave() throws Exception{
        Headquarter headquarter = getHeadquarter();
        headquarterService.save(headquarter);
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setBranchId(1L);
        filter.setHqId(1L);
        filter.setGroupId(1L);
        SearchResult<Headquarter> searchResult = headquarterService.search(filter);
        Headquarter result = null;
        for(Headquarter item : searchResult.getItems()){
            if(item.getId() == headquarter.getId()){
                result = item;
            }
        }
        Assert.assertEquals(headquarter.getId(), result.getId());
        Assert.assertEquals(headquarter.getCode(), result.getCode());
        Assert.assertEquals(headquarter.getName(), result.getName());
        Assert.assertEquals(headquarter.getLogo(), result.getLogo());
    }

    private Headquarter getHeadquarter(){
        Headquarter headquarter = new Headquarter();
        headquarter.setCode("2");
        headquarter.setEnabled(true);
        headquarter.setLogo("logo");
        headquarter.setName("2");
        return headquarter;
    }

    /**
     * Method: update(Headquarter headquarter)
     */
    @Test
    public void testUpdate() throws Exception{
        Headquarter headquarter = getHeadquarter();
        headquarter.setId(1);
        headquarterService.update(headquarter);
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setBranchId(1L);
        filter.setHqId(1L);
        filter.setGroupId(1L);
        SearchResult<Headquarter> searchResult = headquarterService.search(filter);
        Headquarter result = null;
        for(Headquarter item : searchResult.getItems()){
            if(item.getId() == headquarter.getId()){
                result = item;
            }
        }
        Assert.assertEquals(headquarter.getId(), result.getId());
        Assert.assertEquals(headquarter.getCode(), result.getCode());
        Assert.assertEquals(headquarter.getName(), result.getName());
        Assert.assertEquals(headquarter.getLogo(), result.getLogo());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: search(NamedEntitySearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setBranchId(1L);
        filter.setHqId(1L);
        filter.setGroupId(1L);
        SearchResult<Headquarter> searchResult = headquarterService.search(filter);
        for(Headquarter item : searchResult.getItems()){
            if(item.getId() == 1){
                Assert.assertEquals(1, item.getId());
                Assert.assertEquals("1", item.getCode());
                Assert.assertEquals("hq_name1", item.getName());
                Assert.assertEquals("logo", item.getLogo());
                Assert.assertTrue(item.isEnabled());
            }
        }

    }
} 
