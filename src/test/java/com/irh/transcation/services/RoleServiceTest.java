package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Permission;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import com.irh.transaction.services.RoleService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link RoleService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class RoleServiceTest extends BaseTest{

    /**
     * The {@link RoleService} instance to test.
     */
    private RoleService service;

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(RoleService.class);
    }

    /**
     * Tests the {@link RoleService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchAll() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        List<Role> roles = service.search(filter).getItems();
        assertEquals(8, roles.size());
    }

    /**
     * Tests the {@link RoleService#save(Role)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Role role = new Role();
        role.setName("new role");
        role.setHqId(TestHelper.HQ_ID);
        role.setLevel(RoleLevel.BRANCH);
        List<Permission> permissions = new ArrayList<>();
        Permission permission1 = new Permission();
        permission1.setId(1);
        Permission permission2 = new Permission();
        permission2.setId(2);
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        service.save(role);
        Role actual = service.findOne(role.getId());
        assertEquals("new role", actual.getName());
        assertEquals(TestHelper.HQ_ID, actual.getHqId());
        assertEquals(RoleLevel.BRANCH, actual.getLevel());
        assertEquals(2, role.getPermissions().size());
        for(Permission p : actual.getPermissions()){
            if(p.getId() == 1){
                assertEquals("data", p.getCode());
            }else if(p.getId() == 2){
                assertEquals("finance", p.getCode());
            }else{
                fail();
            }
        }
    }

    /**
     * Tests the  {@link RoleService#save(Role)} method, with a role with the same name exists. {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        Role role = new Role();
        role.setName("总部管理员");
        role.setHqId(TestHelper.HQ_ID);
        role.setLevel(RoleLevel.BRANCH);
        service.save(role);
    }

    /**
     * Tests the {@link RoleService#update(Role)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        Role role = service.findOne(1);
        role.getPermissions().remove(8);
        role.setName("new role");
        role.setLevel(RoleLevel.BRANCH_GROUP);
        service.update(role);

        Role actual = service.findOne(1);
        assertEquals("new role", actual.getName());
        assertEquals(RoleLevel.BRANCH_GROUP, actual.getLevel());
        assertEquals(11, actual.getPermissions().size());
        boolean deleted = true;
        for(Permission permission : actual.getPermissions()){
            if(permission.getId() == 9){
                deleted = false;
                break;
            }
        }
        assertTrue(deleted);
    }

    /**
     * Tests the {@link RoleService#update(Role)} method, with a role with the same name exists. {@link
     * EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void updateExists() throws CoreServiceException{
        Role role = service.findOne(1);
        role.setName("总部财务");
        service.update(role);
    }

    /**
     * Tests the {@link RoleService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setName("总部");
        filter.setHqId(TestHelper.HQ_ID);
        SearchResult<Role> result = service.search(filter);
        assertEquals(2, result.getItems().size());
        assertEquals(2, result.getCount());
        assertEquals(1, result.getTotalPages());
        assertNull(result.getPage());
        assertNull(result.getSize());
        assertEquals(1, result.getItems().get(0).getId());
        assertEquals(2, result.getItems().get(1).getId());
    }
}
