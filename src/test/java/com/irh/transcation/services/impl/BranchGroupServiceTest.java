package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.services.BranchGroupService;
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
 * BranchGroupServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class BranchGroupServiceTest extends BaseCase{

    @Autowired
    private BranchGroupService branchGroupService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(BranchGroup branchGroup)
     */
    @Test
    public void testSave() throws Exception{
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setEnabled(true);
        branchGroup.setName("店铺组1");
        branchGroup.setHqId(1);
        branchGroupService.save(branchGroup);
        BranchGroup result = branchGroupService.findOne(branchGroup.getId());
        Assert.assertEquals(branchGroup.getName(), result.getName());
        Assert.assertEquals(branchGroup.getHqId(), result.getHqId());
        Assert.assertEquals(branchGroup.getId(), result.getId());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: update(BranchGroup branchGroup)
     */
    @Test
    public void testUpdate() throws Exception{
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setEnabled(true);
        branchGroup.setName("店铺组1");
        branchGroup.setHqId(1);
        branchGroup.setId(1);
        branchGroupService.update(branchGroup);
        BranchGroup result = branchGroupService.findOne(branchGroup.getId());
        Assert.assertEquals(branchGroup.getName(), result.getName());
        Assert.assertEquals(branchGroup.getHqId(), result.getHqId());
        Assert.assertEquals(branchGroup.getId(), result.getId());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        BranchGroup result = branchGroupService.findOne(1);
        Assert.assertEquals("branch_group1", result.getName());
        Assert.assertEquals(1, result.getHqId());
        Assert.assertEquals(1, result.getId());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: search(NamedEntitySearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(1L);
        SearchResult<BranchGroup> searchResult = branchGroupService.search(filter);
        Assert.assertEquals(1, searchResult.getCount());
        Assert.assertNotNull(searchResult.getItems());
        Assert.assertEquals(null, searchResult.getPage());
        Assert.assertEquals(null, searchResult.getSize());
        Assert.assertEquals(1, searchResult.getTotalPages());
        BranchGroup result1 = searchResult.getItems().get(0);
        Assert.assertEquals("branch_group1", result1.getName());
        Assert.assertEquals(1, result1.getHqId());
        Assert.assertEquals(1, result1.getId());
        Assert.assertTrue(result1.isEnabled());
    }

} 
