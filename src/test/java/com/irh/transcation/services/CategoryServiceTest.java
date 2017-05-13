package com.irh.transcation.services;

import com.irh.transaction.dto.search.CategorySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.product.*;
import com.irh.transaction.services.BranchService;
import com.irh.transaction.services.CategoryService;
import com.irh.transaction.services.CoreServiceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Contains tests for {@link CategoryService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class CategoryServiceTest extends BaseTest{

    /**
     * The {@link BranchService} instance to test.
     */
    private CategoryService service;

    private static void assertCategory(long expectedId, Category category, boolean forMenu){
        assertEquals("c" + expectedId, category.getName());
        assertEquals("ce" + expectedId, category.getInterName());
        assertEquals(TestHelper.HQ_ID, category.getHqId());
        assertEquals(expectedId <= 2 ? Platform.POS : Platform.IPAD, category.getPlatform());
        if(expectedId <= 4){
            assertNotNull(category.getProducts());
            assertEquals(2, category.getProducts().size());
            for(int i = 0; i < 2; i++){
                CategoryProduct categoryProduct = category.getProducts().get(i);
                Product product = categoryProduct.getProduct();
                long expectedProductId;
                if(expectedId <= 2){
                    expectedProductId = i == 0 ? 2 : 1;
                }else{
                    expectedProductId = i == 0 ? 4 : 3;
                }
                assertEquals(expectedProductId, product.getId());
                assertEquals("product" + expectedProductId, product.getName());
                assertEquals("product-en" + expectedProductId, product.getInterName());
                if(forMenu){
                    assertEquals(100 * expectedProductId, product.getPrice().intValue());
                    assertNotNull(product.getOptionGroups());
                    assertEquals(2, product.getOptionGroups().size());
                    assertEquals("description" + expectedProductId, product.getDescription());
                    assertEquals("label" + expectedProductId, product.getLabel());
                    for(int j = 0; j < 2; j++){
                        ProductOptionGroup optionGroup = product.getOptionGroups().get(j);
                        assertEquals(2, optionGroup.getOptions().size());
                        assertEquals("group" + optionGroup.getId(), optionGroup.getName());
                        for(int k = 0; k < 2; k++){
                            ProductOption option = optionGroup.getOptions().get(k);
                            long expectedOptionId = optionGroup.getId() == 1 ? k + 1 : k + 3;
                            assertEquals(expectedOptionId, option.getId());
                            assertEquals("option" + expectedOptionId, option.getName());
                            assertEquals("option-en" + expectedOptionId, option.getInterName());
                            assertEquals(10 * expectedOptionId, option.getPrice().intValue());
                        }
                    }
                    assertNotNull(product.getPrintType());
                }
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
        service = TestHelper.Context.getBean(CategoryService.class);
    }

    /**
     * Tests the {@link CategoryService#save(Category)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        Category category = new Category();
        category.setName("c5");
        category.setInterName("ce5");
        category.setPlatform(Platform.IPAD);
        category.setHqId(TestHelper.HQ_ID);
        service.save(category);

        assertCategory(5, get(category.getId()), false);
    }

    /**
     * Tests the {@link CategoryService#update(Category)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        Category category = get(1);
        category.setName("c3");
        category.setInterName("ce3");
        category.setPlatform(Platform.IPAD);
        ArrayList<CategoryProduct> categoryProducts = new ArrayList<>();
        CategoryProduct categoryProduct1 = new CategoryProduct();
        Product product1 = new Product();
        product1.setId(3);
        categoryProduct1.setProduct(product1);
        categoryProduct1.setDisplayOrder(2);
        categoryProducts.add(categoryProduct1);
        CategoryProduct categoryProduct2 = new CategoryProduct();
        categoryProduct2.setDisplayOrder(1);
        Product product2 = new Product();
        product2.setId(4);
        categoryProduct2.setProduct(product2);
        categoryProducts.add(categoryProduct2);
        category.setProducts(categoryProducts);
        service.update(category);

        Category actual = get(1);
        assertCategory(3, actual, false);
    }

    /**
     * Tests the {@link CategoryService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertCategory(1, service.findOne(1), false);
    }

    /**
     * Tests the {@link CategoryService#search(CategorySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        CategorySearchFilter filter = new CategorySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setName("c");
        filter.setSize(1);
        filter.setPage(2);
        SearchResult<Category> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(4, result.getCount());
        assertEquals(2, result.getPage().intValue());
        assertEquals(1, result.getSize().intValue());
        assertEquals(4, result.getTotalPages());
        assertCategory(2, result.getItems().get(0), false);
    }

    /**
     * Tests the {@link CategoryService#findMenu(long, Long, Platform)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findMenu() throws CoreServiceException{
        List<Category> categories = service.findMenu(TestHelper.HQ_ID, 1L, Platform.POS);
        for(Category category : categories){
            assertCategory(category.getId(), category, true);
        }
    }

    private Category get(long id) throws CoreServiceException{
        return service.findOne(id);
    }
}
