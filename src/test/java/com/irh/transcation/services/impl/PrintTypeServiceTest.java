package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.services.PrintTypeService;
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
 * PrintTypeServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class PrintTypeServiceTest extends BaseCase{

    @Autowired
    private PrintTypeService printTypeService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(PrintType printType)
     */
    @Test
    public void testSave() throws Exception{
        PrintType printType = getPrintType();
        printTypeService.save(printType);
        PrintType result = printTypeService.findOne(printType.getId());
        assertPrintType(printType, result);
    }

    private void assertPrintType(PrintType printType, PrintType result){
        Assert.assertEquals(printType.getId(), result.getId());
        Assert.assertEquals(printType.getName(), result.getName());
        Assert.assertEquals(printType.getBranchId(), result.getBranchId());
    }

    private PrintType getPrintType(){
        PrintType printType = new PrintType();
        printType.setBranchId(1);
        printType.setName("湘菜打印类");
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        products.add(product1);
        printType.setProducts(products);
        return printType;
    }

    /**
     * Method: update(PrintType printType)
     */
    @Test
    public void testUpdate() throws Exception{
        PrintType printType = getPrintType();
        printType.setId(1);
        printTypeService.update(printType);
        PrintType result = printTypeService.findOne(printType.getId());
        assertPrintType(printType, result);
    }

    /**
     * Method: findOne(long id)
     */
    @Test
    public void testFindOne() throws Exception{
        PrintType result = printTypeService.findOne(1);
        assertPrintType1(result);
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
        SearchResult<PrintType> searchResult = printTypeService.search(filter);
        for(PrintType item : searchResult.getItems()){
            if(item.getId() == 1){
                assertPrintType1(item);
            }
        }
    }

    private void assertPrintType1(PrintType item){
        Assert.assertEquals(1, item.getId());
        Assert.assertEquals(1, item.getBranchId());
        Assert.assertEquals("湘菜类打印", item.getName());
    }
} 
