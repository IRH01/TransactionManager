package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.ProductSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.ProductService;
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
 * ProductServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class ProductServiceTest extends BaseCase{

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(Product product)
     */
    @Test
    public void testSave() throws Exception{
        Product product = getProduct();
        productService.save(product);
        Product result = productService.findOne(product.getId());
        assertProduct(product, result);
    }

    private void assertProduct(Product product, Product result){
        Assert.assertEquals(product.getId(), result.getId());
        Assert.assertEquals(product.getName(), result.getName());
        Assert.assertEquals(product.getInterName(), result.getInterName());
        Assert.assertEquals(product.getHqId(), result.getHqId());
        Assert.assertEquals(product.getImgUrl(), result.getImgUrl());
        Assert.assertEquals(0, product.getPrice().compareTo(result.getPrice()));
        Assert.assertEquals(product.getStatus(), result.getStatus());
    }

    private Product getProduct(){
        Product product = new Product();
        product.setStatus(ProductStatus.ONSALE);
        product.setHqId(1);
        product.setPrice(new BigDecimal(50));
        PrintType printType = new PrintType();
        printType.setId(1);
        product.setPrintType(printType);
        product.setName("法国咖啡");
        product.setImgUrl("img.com");
        product.setInterName("咖啡");
        List<ProductOptionGroup> optionGroups = new ArrayList<>();
        ProductOptionGroup optionGroup = new ProductOptionGroup();
        optionGroup.setId(1);
        optionGroups.add(optionGroup);
        product.setOptionGroups(optionGroups);
        return product;
    }

    /**
     * Method: update(Product product)
     */
    @Test
    public void testUpdate() throws Exception{
        Product product = getProduct();
        product.setId(1);
        productService.update(product);
        Product result = productService.findOne(product.getId());
        assertProduct(product, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        Product result = productService.findOne(1);
        assertProduct1(result);
    }

    /**
     * Method: search(ProductSearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        ProductSearchFilter filter = new ProductSearchFilter();
        filter.setHqId(1L);
        filter.setGroupId(1L);
        filter.setBranchId(1L);
        SearchResult<Product> searchResult = productService.search(filter);
        for(Product item : searchResult.getItems()){
            if(item.getId() == 1){
                assertProduct1(item);
            }
        }

    }

    private void assertProduct1(Product item){
        Assert.assertEquals(1, item.getId());
        Assert.assertEquals(1, item.getHqId());
        Assert.assertEquals("满堂红", item.getName());
        Assert.assertEquals("剁椒鱼头", item.getInterName());
        Assert.assertEquals("img.com", item.getImgUrl());
        Assert.assertEquals(0, item.getPrice().compareTo(new BigDecimal(56)));
        Assert.assertEquals(ProductStatus.ONSALE, item.getStatus());
    }
} 
