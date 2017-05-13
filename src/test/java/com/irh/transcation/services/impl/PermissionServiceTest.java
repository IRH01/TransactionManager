package com.irh.transcation.services.impl;

import com.irh.transaction.model.account.Permission;
import com.irh.transaction.services.PermissionService;
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
 * PermissionServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class PermissionServiceTest extends BaseCase{

    @Autowired
    private PermissionService permissionService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: findAll()
     */
    @Test
    public void testFindAll() throws Exception{
        List<Permission> results = permissionService.findAll();
        for(Permission item : results){
            if(item.getId() == 1){
                Assert.assertEquals(1, item.getId());
                Assert.assertEquals("管理层", item.getName());
                Assert.assertEquals("manager", item.getCode());
                Assert.assertEquals("操作后台", item.getCategory());
            }
        }
    }
} 
