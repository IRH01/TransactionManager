package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.services.ProductOptionGroupService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductOptionGroupServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class ProductOptionGroupServiceTest extends BaseCase{

    @Autowired
    private ProductOptionGroupService productOptionGroupService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(ProductOptionGroup group)
     */
    @Test
    public void testSave() throws Exception{
        ProductOptionGroup group = getProductOptionGroup();
        productOptionGroupService.save(group);
        ProductOptionGroup result = productOptionGroupService.findOne(group.getId());
        assertGroup(group, result);
    }

    private void assertGroup(ProductOptionGroup group, ProductOptionGroup result){
        Assert.assertEquals(group.getId(), result.getId());
        Assert.assertEquals(group.getHqId(), result.getHqId());
        Assert.assertEquals(group.getName(), result.getName());
    }

    private ProductOptionGroup getProductOptionGroup(){
        ProductOptionGroup group = new ProductOptionGroup();
        group.setHqId(1);
        group.setName("小吃组");
        group.setHqId(1);
        group.setEnabled(true);
        List<ProductOption> options = new ArrayList<>();
        ProductOption option = new ProductOption();
        option.setId(1);
        option.setName("辣椒酱");
        option.setInterName("剁辣椒");
        option.setPrice(new BigDecimal(2));
        options.add(option);
        group.setOptions(options);
        return group;
    }

    /**
     * Method: update(ProductOptionGroup group)
     */
    @Test
    public void testUpdate() throws Exception{
        ProductOptionGroup group = getProductOptionGroup();
        group.setId(1);
        productOptionGroupService.update(group);
        ProductOptionGroup result = productOptionGroupService.findOne(1);
        assertGroup(group, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        ProductOptionGroup result = productOptionGroupService.findOne(1);
        assertGroup1(result);
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
        SearchResult<ProductOptionGroup> searchResult = productOptionGroupService.search(filter);
        for(ProductOptionGroup item : searchResult.getItems()){
            if(item.getId() == 1){
                assertGroup1(item);
            }
        }
    }

    private void assertGroup1(ProductOptionGroup item){
        Assert.assertEquals(1, item.getId());
        Assert.assertEquals(1, item.getHqId());
        Assert.assertEquals("进口饮品", item.getName());
        Assert.assertTrue(item.isEnabled());
    }
} 
