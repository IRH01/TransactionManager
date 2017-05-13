package com.irh.transcation.services;

import com.irh.transaction.dto.search.ProductSearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for {@link ProductService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class ProductServiceTest extends BaseTest{

    /**
     * The {@link ProductService} instance to test.
     */
    private ProductService service;

    private static void assertProduct(long expectedId, Product product){
        assertEquals("product" + expectedId, product.getName());
        assertEquals("product-en" + expectedId, product.getInterName());
        assertEquals(100 * expectedId, product.getPrice().intValue());
        assertEquals(ProductStatus.ONSALE, product.getStatus());
        assertEquals(TestHelper.HQ_ID, product.getHqId());
        assertEquals("url", product.getImgUrl());
        assertEquals("description" + expectedId, product.getDescription());
        assertEquals("label" + expectedId, product.getLabel());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(ProductService.class);
    }

    /**
     * Tests the {@link ProductService#save(Product)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Product product = new Product();
        product.setName("product5");
        product.setInterName("product-en5");
        product.setPrice(new BigDecimal(500));
        product.setStatus(ProductStatus.ONSALE);
        product.setHqId(TestHelper.HQ_ID);
        product.setImgUrl("url");
        product.setDescription("description5");
        product.setLabel("label5");
        service.save(product);

        assertProduct(5, service.findOne(product.getId()));
    }

    /**
     * Tests the {@link ProductService#update(Product)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        Product product = service.findOne(1);
        product.setName("product5");
        product.setInterName("product-en5");
        product.setPrice(new BigDecimal(500));
        product.setStatus(ProductStatus.DISABLED);
        product.setImgUrl("url");
        product.setDescription("description5");
        product.setLabel("label5");
        service.update(product);

        Product actual = service.findOne(1);
        assertEquals(ProductStatus.DISABLED, actual.getStatus());
        actual.setStatus(ProductStatus.ONSALE);
        assertProduct(5, actual);
    }

    /**
     * Tests the {@link ProductService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertProduct(2, service.findOne(2));
    }

    /**
     * Tests the {@link ProductService#search(ProductSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        ProductSearchFilter filter = new ProductSearchFilter();
        filter.setName("p");
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("price");
        filter.setSortOrder(SortOrder.DESC);
        filter.setHqId(TestHelper.HQ_ID);
        SearchResult<Product> result = service.search(filter);

        assertEquals(2, result.getItems().size());
        assertEquals(4, result.getCount());
        assertEquals(2, result.getPage().intValue());
        assertEquals(2, result.getSize().intValue());
        assertEquals(2, result.getTotalPages());
        for(int i = 2; i >= 1; i--){
            assertProduct(i, result.getItems().get(2 - i));
        }
    }

    /**
     * Tests the {@link ProductService#search(ProductSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchAll() throws CoreServiceException{
        ProductSearchFilter filter = new ProductSearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        List<Product> products = service.search(filter).getItems();
        for(Product product : products){
            assertProduct(product.getId(), product);
        }
    }
}
