package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.PrintTypeService;
import com.irh.transaction.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Contains tests for {@link PrintTypeService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class PrintTypeServiceTest extends BaseTest{

    /**
     * The {@link PrintTypeService} instance to test.
     */
    private PrintTypeService service;

    private static void assertPrintType(long expectedId, PrintType printType){
        assertEquals("printType" + expectedId, printType.getName());
        assertEquals(expectedId <= 2 ? 1 : 2, printType.getBranchId());
    }

    private static void assertProducts(PrintType printType){
        assertNotNull(printType.getProducts());
        for(int i = 0; i < 2; i++){
            Product product = printType.getProducts().get(i);
            long expectedProductId = i == 0 ? 3 : 4;
            assertEquals(expectedProductId, product.getId());
            assertEquals("product" + expectedProductId, product.getName());
            assertEquals("product-en" + expectedProductId, product.getInterName());
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
        service = TestHelper.Context.getBean(PrintTypeService.class);
    }

    /**
     * Tests the {@link PrintTypeService#save(PrintType)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        PrintType printType = new PrintType();
        printType.setBranchId(2);
        printType.setName("printType5");
        service.save(printType);

        assertPrintType(5, service.findOne(printType.getId()));
    }

    /**
     * Tests the {@link PrintTypeService#update(PrintType)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        PrintType printType = service.findOne(2);
        printType.setName("printType1");
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(3);
        products.add(product1);
        Product product2 = new Product();
        product2.setId(4);
        products.add(product2);
        printType.setProducts(products);

        service.update(printType);

        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setBranchId(1L);
        PrintType actual = service.findOne(2);
        assertPrintType(1, actual);
        assertProducts(actual);
    }

    /**
     * Tests the {@link ProductService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertPrintType(3, service.findOne(3));
    }

    /**
     * Tests the {@link PrintTypeService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setBranchId(2L);
        filter.setName("typ");
        filter.setPage(2);
        filter.setSize(1);
        SearchResult<PrintType> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(2, result.getPage().intValue());
        assertEquals(1, result.getSize().intValue());
        assertEquals(2, result.getCount());
        assertEquals(2, result.getTotalPages());
        PrintType printType = result.getItems().get(0);
        assertPrintType(4, printType);
    }
}
