package com.irh.transcation.services.impl;


import com.irh.transaction.dto.search.CategorySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.Category;
import com.irh.transaction.model.product.CategoryProduct;
import com.irh.transaction.services.CategoryService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CategoryServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class CategoryServiceTest extends BaseCase{

    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Category category)
     */
    @Test
    public void testSave() throws Exception{
        Category category = getCategory();
        categoryService.save(category);
        Category result = categoryService.findOne(category.getId());
        assertCategory(category, result);
    }

    /**
     * Method: update(Category category)
     */
    @Test
    public void testUpdateCategory() throws Exception{
        Category category = getCategory();
        category.setId(1);
        categoryService.update(category);
        Category result = categoryService.findOne(category.getId());
        assertCategory(category, result);
    }

    /**
     * Method: update(Map<Long, Integer> displayOrders)
     */
    @Test
    public void testUpdateDisplayOrders() throws Exception{
        Map<Long, Integer> displayOrders = new HashMap<Long, Integer>();
        displayOrders.put(1L, 2);
        categoryService.update(displayOrders);
        Category result = categoryService.findOne(1);
        Assert.assertEquals(2, result.getDisplayOrder());
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals("主菜", result.getName());
        Assert.assertEquals("大菜", result.getInterName());
        Assert.assertEquals(1, result.getHqId());
        Assert.assertEquals(Platform.APP, result.getPlatform());
        Assert.assertTrue(result.isEnabled());
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        Category result = categoryService.findOne(1);
        assertCategory1(result);
    }

    /**
     * Method: search(CategorySearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        CategorySearchFilter filter = new CategorySearchFilter();
        filter.setHqId(1L);
        filter.setGroupId(1L);
        filter.setBranchId(1L);
        SearchResult<Category> searchResult = categoryService.search(filter);
        for(Category item : searchResult.getItems()){
            if(item.getId() == 1){
                assertCategory1(item);
            }
        }
    }


    /**
     * Method: findMenu(long hqId, Long branchId, Platform platform)
     */
    @Test
    public void testFindMenu() throws Exception{
        List<Category> list = categoryService.findMenu(1, 1L, Platform.APP);
        for(Category item : list){
            if(item.getId() == 1){
                assertCategory1(item);
            }
        }
    }

    private void assertCategory1(Category item){
        Assert.assertEquals(5, item.getDisplayOrder());
        Assert.assertEquals(1, item.getId());
        Assert.assertEquals("主菜", item.getName());
        Assert.assertEquals("大菜", item.getInterName());
        Assert.assertEquals(1, item.getHqId());
        Assert.assertEquals(Platform.APP, item.getPlatform());
        Assert.assertTrue(item.isEnabled());
    }

    private void assertCategory(Category category, Category result){
        Assert.assertEquals(category.getId(), result.getId());
        Assert.assertEquals(category.getHqId(), result.getHqId());
        Assert.assertEquals(category.getName(), result.getName());
        Assert.assertEquals(category.getDisplayOrder(), result.getDisplayOrder());
        Assert.assertEquals(category.getInterName(), result.getInterName());
        Assert.assertEquals(category.getPlatform(), result.getPlatform());
    }

    private Category getCategory(){
        Category category = new Category();
        category.setEnabled(true);
        category.setName("饮料");
        category.setInterName("茶水");
        category.setHqId(1);
        category.setDisplayOrder(2);
        category.setPlatform(Platform.APP);
        List<CategoryProduct> products = new ArrayList<CategoryProduct>();
        category.setProducts(products);
        return category;
    }
}
