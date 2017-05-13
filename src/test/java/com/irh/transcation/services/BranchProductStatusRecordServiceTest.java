package com.irh.transcation.services;

import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchProductStatusRecord;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductStatus;
import com.irh.transaction.services.BranchProductStatusRecordService;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.EntityExistsException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Contains tests for {@link BranchProductStatusRecordService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class BranchProductStatusRecordServiceTest extends BaseTest{
    /**
     * The {@link BranchProductStatusRecordService} instance to test.
     */
    private BranchProductStatusRecordService service;

    private static void assertStatusRecord(long expectedId, BranchProductStatusRecord record){
        assertEquals(1, record.getBranch().getId());
        assertEquals(expectedId, record.getProduct().getId());
        assertEquals(ProductStatus.SOLDOUT, record.getStatus());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(BranchProductStatusRecordService.class);
    }

    /**
     * Tests the {@link BranchProductStatusRecordService#save(BranchProductStatusRecord)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        BranchProductStatusRecord record = new BranchProductStatusRecord();
        record.setStatus(ProductStatus.SOLDOUT);
        Branch branch = new Branch();
        branch.setId(1);
        record.setBranch(branch);
        Product product = new Product();
        product.setId(3);
        record.setProduct(product);
        record.setCreatedAt(new Date());
        service.save(record);

        assertStatusRecord(3, get(record.getId()));
    }

    /**
     * Tests the {@link BranchProductStatusRecordService#save(BranchProductStatusRecord)} method, with the status on sale.
     * {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveStatusRecordWithOnSale() throws CoreServiceException{
        BranchProductStatusRecord record = new BranchProductStatusRecord();
        record.setStatus(ProductStatus.ONSALE);
        service.save(record);
        fail();
    }

    /**
     * Tests the {@link BranchProductStatusRecordService#save(BranchProductStatusRecord)} method, with a record of the
     * same branch and product exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        BranchProductStatusRecord record = new BranchProductStatusRecord();
        record.setStatus(ProductStatus.SOLDOUT);
        Branch branch = new Branch();
        branch.setId(1);
        record.setBranch(branch);
        Product product = new Product();
        product.setId(1);
        record.setProduct(product);
        record.setCreatedAt(new Date());
        service.save(record);
        fail();
    }

    /**
     * Tests the {@link BranchProductStatusRecordService#delete(long)}} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void deleteStatusRecord() throws CoreServiceException{
        service.delete(1);
        assertNull(get(1));
    }

    private BranchProductStatusRecord get(long id) throws CoreServiceException{
        SearchFilter filter = new SearchFilter();
        filter.setBranchId(1L);
        return TestHelper.getEntity(service.search(filter).getItems(), id);
    }
}
