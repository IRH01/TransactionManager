package com.irh.transcation.services;

import com.irh.transaction.dto.search.BranchSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.services.BranchService;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link BranchService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchServiceTest extends BaseTest{

    /**
     * The {@link BranchService} instance to test.
     */
    private BranchService service;

    private static void assertBranch(long expectedId, Branch branch){
        assertEquals("branch" + expectedId, branch.getName());
        assertEquals("province" + expectedId, branch.getProvince());
        assertEquals("city" + expectedId, branch.getCity());
        assertEquals("address" + expectedId, branch.getAddress());
        assertEquals("district" + expectedId, branch.getDistrict());
        assertEquals(String.valueOf(expectedId), branch.getPhone());
        assertEquals(TestHelper.HQ_ID, branch.getHqId());
        assertEquals(Math.round(expectedId / 2) + (expectedId % 2), branch.getGroupId());
        assertTrue(branch.isEnabled());
        assertEquals(Double.valueOf("13." + expectedId), branch.getLatitude(), 0);
        assertEquals(Double.valueOf("20." + expectedId), branch.getLongitude(), 0);
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(BranchService.class);
    }

    /**
     * Tests the {@link BranchService#save(Branch)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Branch branch = new Branch();
        branch.setName("branch7");
        branch.setEnabled(true);
        branch.setProvince("province7");
        branch.setCity("city7");
        branch.setDistrict("district7");
        branch.setAddress("address7");
        branch.setPhone("7");
        Account manager = new Account();
        manager.setId(4);
        branch.setLongitude(20.7);
        branch.setLatitude(13.7);
        branch.setHqId(TestHelper.HQ_ID);
        branch.setGroupId(3);
        service.save(branch);

        Branch actual = get(branch.getId());
        assertEquals(3, actual.getGroupId());
        actual.setGroupId(4);
        assertBranch(7, actual);
    }

    /**
     * Tests the {@link BranchService#save(Branch)} method, saving with a branch with the same name in the area. {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        Branch branch = new Branch();
        branch.setName("branch6");
        branch.setEnabled(true);
        branch.setProvince("province7");
        branch.setCity("city7");
        branch.setAddress("address7");
        branch.setPhone("7");
        Account manager = new Account();
        manager.setId(4);
        branch.setLongitude(20.7);
        branch.setLatitude(13.7);
        branch.setHqId(TestHelper.HQ_ID);
        branch.setGroupId(3);
        service.save(branch);
        fail();
    }

    /**
     * Tests the {@link BranchService#update(Branch)} method.
     */
    @Test
    public void update() throws CoreServiceException{
        Branch branch = get(1);
        branch.setName("branch7");
        branch.setEnabled(false);
        branch.setProvince("province7");
        branch.setCity("city7");
        branch.setAddress("address7");
        branch.setDistrict("district7");
        branch.setPhone("7");
        Account manager = new Account();
        manager.setId(4);
        branch.setLongitude(20.7);
        branch.setLatitude(13.7);
        service.update(branch);

        Branch actual = get(1);
        assertFalse(actual.isEnabled());
        actual.setGroupId(4);
        actual.setEnabled(true);
        assertBranch(7, actual);
    }

    /**
     * Tests the {@link BranchService#update(Branch)} method, saving with a branch with the same name in the area.
     * {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void updateExists() throws CoreServiceException{
        Branch branch = get(1);
        branch.setName("branch2");
        service.update(branch);
        fail();
    }

    /**
     * Tests the {@link BranchService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertBranch(1, service.findOne(1));
    }

    /**
     * Tests the {@link BranchService#search(BranchSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        BranchSearchFilter filter = new BranchSearchFilter();
        filter.setName("branch");
        filter.setHqId(TestHelper.HQ_ID);
        filter.setGroupId(2L);
        filter.setPage(2);
        filter.setSize(1);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<Branch> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(2, result.getCount());
        assertEquals(1, result.getSize().intValue());
        assertEquals(2, result.getPage().intValue());
        assertEquals(2, result.getTotalPages());
        assertBranch(3, result.getItems().get(0));
    }

    /**
     * Tests the {@link BranchService#getBranchCities(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void getBranchCities() throws CoreServiceException{
        List<String> cities = service.getBranchCities(TestHelper.HQ_ID);
        assertEquals(6, cities.size());
        for(int i = 1; i <= 6; i++){
            assertEquals("city" + i, cities.get(i - 1));
        }
    }

    private Branch get(long id) throws CoreServiceException{
        return service.findOne(id);
    }
}
