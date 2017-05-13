package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.services.BranchService;
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

import java.util.List;

/**
 * BranchServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class BranchServiceTest extends BaseCase{

    @Autowired
    private BranchService branchService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Branch branch)
     */
    @Test
    public void testSave() throws Exception{
        Branch branch = getBranch();
        branchService.save(branch);
        Branch result = branchService.findOne(branch.getId());
        assertBranch(branch, result);
    }


    /**
     * Method: update(Branch branch)
     */
    @Test
    public void testUpdate() throws Exception{
        Branch branch = getBranch();
        branch.setId(1);
        branchService.update(branch);
        Branch result = branchService.findOne(branch.getId());
        assertBranch(branch, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        Branch result = branchService.findOne(1);
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals("岳麓山店", result.getName());
        Assert.assertEquals(1, result.getGroupId());
        Assert.assertEquals(1, result.getHqId());
        Assert.assertEquals("18676853505", result.getPhone());
        Assert.assertEquals("湖南省", result.getProvince());
        Assert.assertEquals("长沙市", result.getCity());
        Assert.assertEquals("岳麓区", result.getDistrict());
        Assert.assertEquals("枫林路113号", result.getAddress());
        Assert.assertEquals(null, result.getLatitude());
        Assert.assertEquals(null, result.getLongitude());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: search(BranchSearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        BranchSearchFilter filter = new BranchSearchFilter();
        filter.setHqId(1L);
        SearchResult<Branch> searchResult = branchService.search(filter);
        Assert.assertEquals(null, searchResult.getSize());
        Assert.assertEquals(1, searchResult.getTotalPages());
        Assert.assertEquals(1, searchResult.getCount());
        Assert.assertEquals(null, searchResult.getPage());
        Branch result1 = searchResult.getItems().get(0);
        Assert.assertEquals(1, result1.getId());
        Assert.assertEquals("岳麓山店", result1.getName());
        Assert.assertEquals(1, result1.getGroupId());
        Assert.assertEquals(1, result1.getHqId());
        Assert.assertEquals("18676853505", result1.getPhone());
        Assert.assertEquals("湖南省", result1.getProvince());
        Assert.assertEquals("长沙市", result1.getCity());
        Assert.assertEquals("岳麓区", result1.getDistrict());
        Assert.assertEquals("枫林路113号", result1.getAddress());
        Assert.assertEquals(null, result1.getLatitude());
        Assert.assertEquals(null, result1.getLongitude());
        Assert.assertTrue(result1.isEnabled());
    }

    /**
     * Method: getBranchCities(long hqId)
     */
    @Test
    public void testGetBranchCities() throws Exception{
        List<String> list = branchService.getBranchCities(1);
        Assert.assertEquals("长沙市", list.get(0));
    }

    private void assertBranch(Branch branch, Branch result){
        Assert.assertEquals(branch.getName(), result.getName());
        Assert.assertEquals(branch.getId(), result.getId());
        Assert.assertEquals(branch.getHqId(), result.getHqId());
        Assert.assertEquals(branch.getAddress(), result.getAddress());
        Assert.assertEquals(branch.getCity(), result.getCity());
        Assert.assertEquals(branch.getProvince(), result.getProvince());
        Assert.assertEquals(branch.getDistrict(), result.getDistrict());
        Assert.assertEquals(branch.getPhone(), result.getPhone());
        Assert.assertEquals(branch.getLatitude(), result.getLatitude());
        Assert.assertEquals(branch.getLongitude(), result.getLongitude());
        Assert.assertEquals(branch.getGroupId(), result.getGroupId());
    }

    private Branch getBranch(){
        Branch branch = new Branch();
        branch.setName("南山店");
        branch.setHqId(1);
        branch.setEnabled(true);
        branch.setAddress("中山公园");
        branch.setCity("深圳市");
        branch.setProvince("广东省");
        branch.setDistrict("南山区");
        branch.setGroupId(1);
        branch.setLatitude(11D);
        branch.setLongitude(112D);
        branch.setPhone("13974245443");
        return branch;
    }

} 
