package com.irh.transcation.services.impl;

import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.BranchProductStatusRecordService;
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
import java.util.Date;
import java.util.List;

/**
 * BranchProductStatusRecordServiceImpl Tester.
 *
 * @author iritchie.ren
 * @version 1.0
 * @since <pre>���� 14, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigCase.class)
@ActiveProfiles("test")
@Transactional
public class BranchProductStatusRecordServiceTest extends BaseCase{

    @Autowired
    private BranchProductStatusRecordService branchProductStatusRecordService;

    @Before
    public void setUp() throws Exception{
        HelperCase.restData();
    }

    @After
    public void after() throws Exception{
    }

    /**
     * Method: save(BranchProductStatusRecord record)
     */
    @Test
    public void testSave() throws Exception{
        Branch branch = getBranch();
        Product product = getProduct();
        PrintType printType = getPrintType(product);
        product.setPrintType(printType);
        BranchProductStatusRecord record = new BranchProductStatusRecord();
        record.setBranch(branch);
        record.setProduct(product);
        record.setCreatedAt(new Date());
        record.setStatus(ProductStatus.DISABLED);
        branchProductStatusRecordService.save(record);
        SearchFilter filter = new SearchFilter();
        filter.setHqId(1L);
        filter.setBranchId(1L);
        List<BranchProductStatusRecord> list = branchProductStatusRecordService.search(filter).getItems();
        BranchProductStatusRecord result = null;
        for(BranchProductStatusRecord item : list){
            if(item.getId() == record.getId()){
                result = item;
            }
        }
        Assert.assertEquals(record.getId(), result.getId());
        Assert.assertEquals(record.getStatus(), result.getStatus());
        Assert.assertEquals(1, record.getCreatedAt().compareTo(result.getCreatedAt()));
    }

    private PrintType getPrintType(Product product){
        PrintType printType = new PrintType();
        printType.setName("湘菜类打印");
        printType.setBranchId(1);
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        printType.setProducts(products);
        printType.setId(1);
        return printType;
    }

    private Product getProduct(){
        Product product = new Product();
        product.setId(2);
        product.setHqId(1);
        product.setName("口味虾");
        product.setInterName("龙虾");
        product.setImgUrl("img.com");
        product.setStatus(ProductStatus.ONSALE);
        product.setPrice(new BigDecimal(56));
        return product;
    }

    /**
     * Method: delete(long id)
     */
    @Test
    public void testDelete() throws Exception{
        branchProductStatusRecordService.delete(1);
        SearchFilter filter = new SearchFilter();
        filter.setHqId(1L);
        filter.setBranchId(1L);
        SearchResult<BranchProductStatusRecord> searchResult = branchProductStatusRecordService.search(filter);
        List<BranchProductStatusRecord> list = searchResult.getItems();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Method: search(SearchFilter filter)
     */
    @Test
    public void testSearch() throws Exception{
        SearchFilter filter = new SearchFilter();
        filter.setHqId(1L);
        filter.setBranchId(1L);
        SearchResult<BranchProductStatusRecord> searchResult = branchProductStatusRecordService.search(filter);
        BranchProductStatusRecord result1 = searchResult.getItems().get(0);
        Assert.assertEquals(1, result1.getId());
        Assert.assertEquals(1, result1.getBranch().getId());
        Assert.assertEquals(ProductStatus.ONSALE, result1.getStatus());
        Assert.assertEquals(1, result1.getProduct().getId());
    }

    private Branch getBranch(){
        Branch branch = new Branch();
        branch.setId(1);
        branch.setHqId(1);
        branch.setName("branch2");
        branch.setGroupId(1);
        branch.setProvince("湖南");
        branch.setCity("长沙");
        branch.setDistrict("岳麓区");
        branch.setAddress("枫林路133号");
        branch.setLatitude(11D);
        branch.setPhone("18676823505");
        branch.setEnabled(true);
        return branch;
    }
}
