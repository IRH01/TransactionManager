package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.services.BranchGroupService;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link BranchGroupService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class BranchGroupServiceTest extends BaseTest{
    /**
     * The {@link BranchGroupService} instance to test.
     */
    private BranchGroupService service;

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(BranchGroupService.class);
    }

    /**
     * Tests the {@link BranchGroupService#save(BranchGroup)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setName("new branchGroup");
        branchGroup.setHqId(TestHelper.HQ_ID);
        branchGroup.setEnabled(true);
        service.save(branchGroup);

        BranchGroup actual = get(branchGroup.getId());
        assertEquals("new branchGroup", actual.getName());
        assertEquals(TestHelper.HQ_ID, branchGroup.getHqId());
        assertTrue(branchGroup.isEnabled());
    }

    /**
     * Tests the {@link BranchGroupService#save(BranchGroup)} method, with an area with the same name exists. {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setName("area1");
        branchGroup.setHqId(TestHelper.HQ_ID);
        branchGroup.setEnabled(true);
        service.save(branchGroup);
        fail();
    }

    /**
     * Tests the {@link BranchGroupService#update(BranchGroup)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        BranchGroup branchGroup = get(2);
        branchGroup.setName("new branchGroup");
        Account manager = new Account();
        manager.setId(1);
        branchGroup.setEnabled(false);
        service.update(branchGroup);

        BranchGroup actual = get(2);
        assertEquals("new branchGroup", actual.getName());
        assertFalse(branchGroup.isEnabled());
    }

    /**
     * Tests the {@link BranchGroupService#update(BranchGroup)} method, with an area with the same name exists. {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void updateExists() throws CoreServiceException{
        BranchGroup branchGroup = get(2);
        branchGroup.setName("area3");
        service.update(branchGroup);
        fail();
    }

    /**
     * Tests the {@link BranchGroupService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        BranchGroup branchGroup = service.findOne(1);
        assertEquals("area1", branchGroup.getName());
        assertEquals(TestHelper.HQ_ID, branchGroup.getHqId());
        assertTrue(branchGroup.isEnabled());
    }

    /**
     * Tests the {@link BranchGroupService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setName("a");
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<BranchGroup> result = service.search(filter);
        assertEquals(3, result.getItems().size());
        assertEquals(3, result.getCount());
        for(int i = 0; i < result.getItems().size(); i++){
            BranchGroup actual = result.getItems().get(i);
            int seed = 3 - i;
            assertEquals(seed, actual.getId());
            assertEquals("area" + seed, actual.getName());
            assertEquals(TestHelper.HQ_ID, actual.getHqId());
            assertTrue(actual.isEnabled());
        }
    }

    private BranchGroup get(long id) throws CoreServiceException{
        return service.findOne(id);
    }
}
