package com.irh.transcation.services.impl;


import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.services.BranchTableService;
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
 * BranchTableServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class BranchTableServiceTest extends BaseCase{

    @Autowired
    private BranchTableService branchTableService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(BranchTable table)
     */
    @Test
    public void testSave() throws Exception{
        BranchTable branchTable = getBranchTable();
        branchTableService.save(branchTable);
        BranchTable result = branchTableService.findOne(branchTable.getId());
        assertBranchTable(branchTable, result);
    }

    /**
     * Method: update(BranchTable table)
     */
    @Test
    public void testUpdate() throws Exception{
        BranchTable branchTable = getBranchTable();
        branchTable.setId(1);
        branchTableService.update(branchTable);
        BranchTable result = branchTableService.findOne(1);
        assertBranchTable(branchTable, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        BranchTable result = branchTableService.findOne(1);
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals("大明湖畔桌", result.getCode());
        Assert.assertEquals(new Long(1), result.getParentId());
        Assert.assertEquals(1, result.getBranchId());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: search(SearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        SearchFilter filter = new SearchFilter();
        filter.setHqId(1L);
        filter.setGroupId(1L);
        filter.setBranchId(1L);
        SearchResult<BranchTable> searchResult = branchTableService.search(filter);
        BranchTable result1 = searchResult.getItems().get(0);
        Assert.assertEquals(1, result1.getId());
        Assert.assertEquals("大明湖畔桌", result1.getCode());
        Assert.assertEquals(new Long(1), result1.getParentId());
        Assert.assertEquals(1, result1.getBranchId());
        Assert.assertTrue(result1.isEnabled());
    }


    private void assertBranchTable(BranchTable branchTable, BranchTable result){
        Assert.assertEquals(branchTable.getId(), result.getId());
        Assert.assertEquals(branchTable.getBranchId(), result.getBranchId());
        Assert.assertEquals(branchTable.getCode(), result.getCode());
        Assert.assertEquals(branchTable.isEnabled(), result.isEnabled());
    }

    private BranchTable getBranchTable(){
        BranchTable branchTable = new BranchTable();
        branchTable.setEnabled(true);
        branchTable.setBranchId(1);
        branchTable.setCode("branch code");
        branchTable.setParentId(1L);
        return branchTable;
    }
} 
