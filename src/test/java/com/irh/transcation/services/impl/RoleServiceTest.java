package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Permission;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.RoleService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * RoleServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class RoleServiceTest extends BaseCase{

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Role role)
     */
    @Test
    public void testSave() throws Exception{
        Role role = getRole();
        roleService.save(role);
        Role result = roleService.findOne(role.getId());
        assertRole(role, result);
    }

    private Role getRole(){
        Role role = new Role();
        role.setHqId(1);
        role.setName("管理员");
        role.setLevel(RoleLevel.BRANCH);
        List<Permission> permissions = new ArrayList<>();
        Permission permission1 = new Permission();
        permission1.setId(1);
        permissions.add(permission1);
        role.setPermissions(permissions);
        return role;
    }

    /**
     * Method: update(Role role)
     */
    @Test
    public void testUpdate() throws Exception{
        Role role = getRole();
        role.setId(1);
        roleService.update(role);
        Role result = roleService.findOne(role.getId());
        assertRole(role, result);
    }

    private void assertRole(Role role, Role result){
        Assert.assertEquals(role.getId(), result.getId());
        Assert.assertEquals(role.getHqId(), result.getHqId());
        Assert.assertEquals(role.getName(), result.getName());
        Assert.assertEquals(role.getLevel(), result.getLevel());
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws CoreServiceException{
        Role result = roleService.findOne(1);
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals("role1", result.getName());
        Assert.assertEquals(RoleLevel.HQ, result.getLevel());
        Assert.assertEquals(1, result.getHqId());
    }

    /**
     * Method: search(NamedEntitySearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(1L);
        filter.setGroupId(1L);
        filter.setBranchId(1L);
        SearchResult<Role> searchResult = roleService.search(filter);
        for(Role item : searchResult.getItems()){
            if(item.getId() == 1){
                Assert.assertEquals(1, item.getId());
                Assert.assertEquals("role1", item.getName());
                Assert.assertEquals(RoleLevel.HQ, item.getLevel());
                Assert.assertEquals(1, item.getHqId());
            }
        }
    }
} 
