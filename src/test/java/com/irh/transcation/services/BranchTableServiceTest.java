package com.irh.transcation.services;

import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.BranchTable;
import com.irh.transaction.services.BranchTableService;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link BranchTableService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class BranchTableServiceTest extends BaseTest{

    /**
     * The {@link BranchTableService} instance to test.
     */
    private BranchTableService service;

    private static void assertTable(long expectedId, BranchTable table){
        assertEquals("table" + expectedId, table.getCode());
        assertEquals(expectedId % 2 == 0 ? Math.round(expectedId / 2) : Math.round(expectedId / 2) + 1,
                table.getBranchId());
        assertTrue(table.isEnabled());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(BranchTableService.class);
    }

    /**
     * Tests the {@link BranchTableService#save(BranchTable)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        BranchTable table = new BranchTable();
        table.setBranchId(3);
        table.setCode("table5");
        table.setEnabled(true);
        service.save(table);

        assertTable(5, service.findOne(table.getId()));
    }

    /**
     * Tests the {@link BranchTableService#save(BranchTable)} method, with a table with the same code and branch id
     * already exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        BranchTable table = new BranchTable();
        table.setBranchId(2);
        table.setCode("table4");
        service.save(table);
        fail();
    }

    /**
     * Tests the {@link BranchTableService#update(BranchTable)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        BranchTable table = service.findOne(1);
        table.setCode("table5");
        table.setEnabled(false);
        service.update(table);
        BranchTable actual = service.findOne(1);
        assertEquals("table5", actual.getCode());
        assertFalse(actual.isEnabled());
    }

    /**
     * Tests the {@link BranchTableService#update(BranchTable)} method, with a table with the same code and branch id
     * already exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void updateExists() throws CoreServiceException{
        BranchTable table = service.findOne(1);
        table.setCode("table2");
        service.update(table);
        fail();
    }

    /**
     * Tests the {@link BranchTableService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertTable(2, service.findOne(2));
    }

    /**
     * Tests the {@link BranchTableService#search(SearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        SearchFilter filter = new SearchFilter();
        filter.setBranchId(1L);
        filter.setPage(2);
        filter.setSize(1);
        SearchResult<BranchTable> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(2, result.getCount());
        assertEquals(2, result.getPage().intValue());
        assertEquals(1, result.getSize().intValue());
        assertEquals(2, result.getTotalPages());
        assertTable(2, result.getItems().get(0));
    }
}
