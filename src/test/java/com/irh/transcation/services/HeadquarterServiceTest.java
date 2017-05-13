package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import com.irh.transaction.services.HeadquarterService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link HeadquarterService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class HeadquarterServiceTest extends BaseTest{

    /**
     * The {@link HeadquarterService} instance to test.
     */
    private HeadquarterService service;

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(HeadquarterService.class);
    }

    /**
     * Tests the {@link HeadquarterService#save(Headquarter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Headquarter headquarter = new Headquarter();
        headquarter.setName("new hq");
        headquarter.setCode("newHQ");
        headquarter.setLogo("new logo");
        headquarter.setEnabled(true);
        service.save(headquarter);

        Headquarter actual = get(headquarter.getId());
        assertEquals("new hq", actual.getName());
        assertEquals("newHQ", actual.getCode());
        assertTrue(actual.isEnabled());
        assertEquals("new logo", actual.getLogo());
    }

    /**
     * Tests the {@link HeadquarterService#save(Headquarter)} method, with a headquarter with the same code exists.
     * {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        Headquarter headquarter = new Headquarter();
        headquarter.setName("new hq");
        headquarter.setCode("maancoffee");
        headquarter.setLogo("new logo");
        headquarter.setEnabled(true);
        service.save(headquarter);
        fail();
    }

    /**
     * Tests the {@link HeadquarterService#update(Headquarter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        Headquarter headquarter = get(1);
        headquarter.setName("new hq");
        headquarter.setEnabled(false);
        headquarter.setLogo("new logo");
        service.update(headquarter);

        Headquarter actual = get(headquarter.getId());
        assertEquals("new hq", actual.getName());
        assertFalse(actual.isEnabled());
        assertEquals("new logo", actual.getLogo());
    }

    /**
     * Tests the {@link HeadquarterService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setName("maan");
        SearchResult<Headquarter> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getCount());
        Headquarter actual = result.getItems().get(0);
        assertEquals("Maan Coffee", actual.getName());
        assertEquals("maancoffee", actual.getCode());
        assertTrue(actual.isEnabled());
        assertEquals("logo1", actual.getLogo());
    }

    private Headquarter get(long id) throws CoreServiceException{
        return TestHelper.getEntity(service.search(null).getItems(), id);
    }
}
