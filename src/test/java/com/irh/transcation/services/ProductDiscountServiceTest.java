package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.marketing.DiscountType;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.services.ProductDiscountService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Contains tests for {@link ProductDiscountService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class ProductDiscountServiceTest extends BaseTest{

    /**
     * The {@link ProductDiscountService} instance to test.
     */
    private ProductDiscountService service;

    private static void assertDiscount(long expectedId, ProductDiscount discount,
                                       boolean productsIncluded) throws ParseException{
        assertEquals(TestHelper.HQ_ID, discount.getHqId());
        assertEquals("discount" + expectedId, discount.getName());
        DiscountType expectedDiscountType;
        Integer expectedDiscountValue = null;
        if(expectedId <= 2){
            expectedDiscountType = DiscountType.FREE;
            assertNull(discount.getStartTime());
            assertNull(discount.getEndTime());
            assertNull(discount.getStartTime());
            assertNull(discount.getEndTime());
        }else{
            assertEquals(TestHelper.parseDate(false, "2016-01-01").getTime(),
                    discount.getStartDate().getTime());
            assertEquals(TestHelper.parseDate(false, "2016-12-01").getTime(),
                    discount.getEndDate().getTime());
            assertEquals(10, discount.getStartTime().intValue());
            assertEquals(22, discount.getEndTime().intValue());
            if(expectedId == 3){
                expectedDiscountType = DiscountType.PERCENTAGE;
                expectedDiscountValue = 93;
            }else{
                expectedDiscountType = DiscountType.AMOUNT;
                expectedDiscountValue = 10;
            }
        }
        assertEquals(expectedDiscountType, discount.getDiscountType());
        assertEquals(expectedDiscountValue, discount.getDiscountValue());
        if(productsIncluded){
            assertEquals(2, discount.getProducts().size());
            for(int i = 0; i < discount.getProducts().size(); i++){
                assertEquals(expectedId <= 2 ? i + 1 : i + 3, discount.getProducts().get(i).getId());
            }
        }
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(ProductDiscountService.class);
    }

    /**
     * Tests the {@link ProductDiscountService#save(ProductDiscount)} method.
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception{
        ProductDiscount discount = new ProductDiscount();
        discount.setHqId(TestHelper.HQ_ID);
        discount.setName("discount5");
        discount.setDiscountType(DiscountType.AMOUNT);
        discount.setDiscountValue(10);
        discount.setStartDate(TestHelper.parseDate(false, "2016-01-01"));
        discount.setEndDate(TestHelper.parseDate(false, "2016-12-01"));
        discount.setStartTime(10);
        discount.setEndTime(22);
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(3);
        products.add(product1);
        Product product2 = new Product();
        product2.setId(4);
        products.add(product2);
        discount.setProducts(products);
        service.save(discount);

        assertDiscount(5, service.findOne(discount.getId()), true);
    }

    /**
     * Tests the {@link ProductDiscountService#update(ProductDiscount)} method.
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception{
        ProductDiscount discount = service.findOne(2);
        discount.setName("discount5");
        discount.setDiscountType(DiscountType.AMOUNT);
        discount.setDiscountValue(10);
        discount.setStartDate(TestHelper.parseDate(false, "2016-01-01"));
        discount.setEndDate(TestHelper.parseDate(false, "2016-12-01"));
        discount.setStartTime(10);
        discount.setEndTime(22);
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(3);
        products.add(product1);
        Product product2 = new Product();
        product2.setId(4);
        products.add(product2);
        discount.setProducts(products);
        service.update(discount);

        assertDiscount(5, service.findOne(2), true);
    }

    /**
     * Tests the {@link ProductDiscountService#findOne(long)} method.
     *
     * @throws Exception
     */
    @Test
    public void findOne() throws Exception{
        assertDiscount(1, service.findOne(1), true);
    }

    /**
     * Tests the {@link ProductDiscountService#search(NamedEntitySearchFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void search() throws Exception{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setName("count3");
        SearchResult<ProductDiscount> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getCount());
        assertNull(result.getPage());
        assertNull(result.getSize());
        assertEquals(1, result.getTotalPages());
        assertDiscount(3, result.getItems().get(0), false);
    }

    /**
     * Tests the {@link ProductDiscountService#findAllAvailable(long)} method.
     *
     * @throws Exception
     */
    @Test
    public void findAllAvailable() throws Exception{
        List<ProductDiscount> discounts = service.findAllAvailable(TestHelper.HQ_ID);
        for(int i = 0; i < discounts.size(); i++){
            assertDiscount(i + 1, discounts.get(i), true);
        }
    }
}
