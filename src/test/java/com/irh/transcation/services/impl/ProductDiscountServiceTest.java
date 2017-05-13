package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.marketing.DiscountType;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.services.ProductDiscountService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ProductDiscountServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class ProductDiscountServiceTest extends BaseCase{

    @Autowired
    private ProductDiscountService productDiscountService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(ProductDiscount productDiscount)
     */
    @Test
    public void testSave() throws Exception{
        ProductDiscount productDiscount = getProductDiscount();
        productDiscountService.save(productDiscount);
        ProductDiscount result = productDiscountService.findOne(productDiscount.getId());
        assertProductDiscount(productDiscount, result);
    }

    private void assertProductDiscount(ProductDiscount productDiscount, ProductDiscount result){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(productDiscount.getId(), result.getId());
        Assert.assertEquals(productDiscount.getHqId(), result.getHqId());
        Assert.assertEquals(productDiscount.getName(), result.getName());
        Assert.assertEquals(productDiscount.getEndTime(), result.getEndTime());
        Assert.assertEquals(fmt.format(productDiscount.getEndDate()), fmt.format(result.getEndDate()));
        Assert.assertEquals(fmt.format(productDiscount.getStartDate()), fmt.format(result.getStartDate()));
        Assert.assertEquals(productDiscount.getStartTime(), result.getStartTime());
        Assert.assertEquals(productDiscount.getDiscountType(), result.getDiscountType());
        Assert.assertEquals(productDiscount.getDiscountValue(), result.getDiscountValue());
    }

    private ProductDiscount getProductDiscount(){
        ProductDiscount productDiscount = new ProductDiscount();
        productDiscount.setHqId(1);
        productDiscount.setName("夏季促销");
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        products.add(product);
        productDiscount.setProducts(products);
        productDiscount.setDiscountType(DiscountType.AMOUNT);
        productDiscount.setDiscountValue(10);
        productDiscount.setEndDate(new Date());
        productDiscount.setEndTime(50);
        productDiscount.setStartDate(new Date());
        productDiscount.setStartTime(50);
        return productDiscount;
    }

    /**
     * Method: update(ProductDiscount productDiscount)
     */
    @Test
    public void testUpdate() throws Exception{
        ProductDiscount productDiscount = getProductDiscount();
        productDiscount.setId(1);
        productDiscountService.update(productDiscount);
        ProductDiscount result = productDiscountService.findOne(productDiscount.getId());
        assertProductDiscount(productDiscount, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        ProductDiscount result = productDiscountService.findOne(1);
        assertProductDiscount1(result);
    }

    private void assertProductDiscount1(ProductDiscount result){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(1, result.getId());
        Assert.assertEquals(1, result.getHqId());
        Assert.assertEquals("换季促销", result.getName());
        Assert.assertEquals(DiscountType.AMOUNT, result.getDiscountType());
        Assert.assertEquals(new Integer(100), result.getDiscountValue());
        Assert.assertEquals("2016-05-20", sdf.format(result.getStartDate()).toString());
        Assert.assertEquals("2016-06-01", sdf.format(result.getEndDate()).toString());
        Assert.assertEquals(new Integer(50), result.getStartTime());
        Assert.assertEquals(new Integer(50), result.getEndTime());
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
        SearchResult<ProductDiscount> searchResult = productDiscountService.search(filter);
        for(ProductDiscount item : searchResult.getItems()){
            if(item.getId() == 1){
                assertProductDiscount1(item);
            }
        }
    }

    /**
     * Method: findAllAvailable(long hqId)
     */
    @Test
    public void testFindAllAvailable() throws Exception{
        List<ProductDiscount> list = productDiscountService.findAllAvailable(1);
        for(ProductDiscount item : list){
            if(item.getId() == 1){
                assertProductDiscount1(item);
            }
        }
    }
} 
