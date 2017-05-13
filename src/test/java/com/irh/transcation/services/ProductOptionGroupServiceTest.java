package com.irh.transcation.services;

import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.product.ProductOption;
import com.irh.transaction.model.product.ProductOptionGroup;
import com.irh.transaction.services.CoreServiceException;
import com.irh.transaction.services.ProductOptionGroupService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Contains tests for {@link ProductOptionGroupService}.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class ProductOptionGroupServiceTest extends BaseTest{

    /**
     * The {@link ProductOptionGroupService} instance to test.
     */
    private ProductOptionGroupService service;

    private static void assertOptionGroup(long expectedId, ProductOptionGroup optionGroup,
                                          boolean optionIncluded){
        assertEquals("group" + expectedId, optionGroup.getName());
        assertEquals(TestHelper.HQ_ID, optionGroup.getHqId());
        assertTrue(optionGroup.isEnabled());
        if(optionIncluded){
            assertEquals(2, optionGroup.getOptions().size());
            for(int i = 0; i < 2; i++){
                long expectedOptionId = expectedId * 2 - 1 + i;
                assertOption(expectedOptionId, optionGroup.getOptions().get(i), true);
            }
        }
    }

    private static void assertOption(long expectedId, ProductOption option, boolean enabled){
        assertEquals("option" + expectedId, option.getName());
        assertEquals("option-en" + expectedId, option.getInterName());
        assertEquals(10 * expectedId, option.getPrice().intValue());
        assertEquals(enabled, option.isEnabled());
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(ProductOptionGroupService.class);
    }

    /**
     * Tests the {@link ProductOptionGroupService#save(ProductOptionGroup)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void save() throws CoreServiceException{
        ProductOptionGroup optionGroup = new ProductOptionGroup();
        optionGroup.setHqId(TestHelper.HQ_ID);
        optionGroup.setName("group3");
        optionGroup.setEnabled(true);
        ArrayList<ProductOption> options = new ArrayList<>();
        ProductOption option1 = new ProductOption();
        option1.setName("option5");
        option1.setInterName("option-en5");
        option1.setPrice(new BigDecimal(50));
        option1.setEnabled(true);
        options.add(option1);
        ProductOption option2 = new ProductOption();
        option2.setName("option6");
        option2.setInterName("option-en6");
        option2.setPrice(new BigDecimal(60));
        option2.setEnabled(true);
        options.add(option2);
        optionGroup.setOptions(options);
        service.save(optionGroup);

        assertOptionGroup(3, service.findOne(optionGroup.getId()), true);
    }

    /**
     * Tests the {@link ProductOptionGroupService#update(ProductOptionGroup)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        ProductOptionGroup optionGroup = service.findOne(2);
        optionGroup.setHqId(TestHelper.HQ_ID);
        optionGroup.setName("group3");
        ProductOption option = optionGroup.getOptions().get(0);
        option.setName("option5");
        option.setPrice(new BigDecimal(50));
        option.setInterName("option-en5");
        option.setEnabled(false);
        ProductOption newOption = new ProductOption();
        newOption.setName("option6");
        newOption.setInterName("option-en6");
        newOption.setPrice(new BigDecimal(60));
        newOption.setEnabled(true);
        optionGroup.getOptions().add(newOption);
        service.update(optionGroup);

        ProductOptionGroup actual = service.findOne(2);
        assertEquals("group3", actual.getName());
        assertEquals(3, actual.getOptions().size());
        assertOption(5, actual.getOptions().get(0), false);
        assertOption(4, actual.getOptions().get(1), true);
        assertOption(6, actual.getOptions().get(2), true);
    }

    /**
     * Tests the {@link ProductOptionGroupService#findOne(long)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void findOne() throws CoreServiceException{
        assertOptionGroup(2, service.findOne(2), true);
    }

    /**
     * Tests the {@link ProductOptionGroupService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void search() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        filter.setName("group2");
        SearchResult<ProductOptionGroup> result = service.search(filter);
        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getCount());
        assertEquals(1, result.getTotalPages());
        assertNull(result.getPage());
        assertNull(result.getSize());
        assertOptionGroup(2, result.getItems().get(0), false);
    }

    /**
     * Tests the {@link ProductOptionGroupService#search(NamedEntitySearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchAll() throws CoreServiceException{
        NamedEntitySearchFilter filter = new NamedEntitySearchFilter();
        filter.setHqId(TestHelper.HQ_ID);
        List<ProductOptionGroup> optionGroups = service.search(filter).getItems();
        for(ProductOptionGroup optionGroup : optionGroups){
            assertOptionGroup(optionGroup.getId(), optionGroup, false);
        }
    }
}
