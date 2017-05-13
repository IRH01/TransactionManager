package com.irh.transcation.services;

import com.irh.transaction.model.account.Permission;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.PermissionService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for {@link PermissionService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class PermissionServiceTest extends BaseTest{

    /**
     * The {@link PermissionService} instance to test.
     */
    private PermissionService service;

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(PermissionService.class);
    }

    /**
     * Tests the {@link PermissionService#findAll()} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findAll() throws CoreServiceException{
        List<Permission> permissions = service.findAll();
        assertEquals(12, permissions.size());
    }
}
