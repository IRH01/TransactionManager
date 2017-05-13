package com.irh.transcation.services;

import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.SortOrder;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.dto.search.VipCardTransactionSearchFilter;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.model.marketing.VipCardTransaction;
import com.irh.transaction.model.marketing.VipCardTransactionType;
import com.irh.transaction.services.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Contains tests for {@link VipCardService}.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class VipCardServiceTest extends BaseTest{

    /**
     * The {@link VipCardService} instance to test.
     */
    private VipCardService service;

    private static void assertVipCard(long expectedId, VipCard vipCard) throws ParseException{
        String expectedIdStr = String.valueOf(expectedId);
        assertEquals(TestHelper.HQ_ID, vipCard.getHqId());
        assertEquals(expectedIdStr, vipCard.getNumber());
        assertEquals("card" + expectedId, vipCard.getName());
        assertEquals(expectedIdStr, vipCard.getMobile());
        assertEquals("password" + expectedIdStr, vipCard.getPassword());
        assertEquals(expectedId <= 2 ? 1 : 2, vipCard.getBranch().getId());
        assertEquals(200 * expectedId, vipCard.getBalance().intValue());
        assertEquals(100 * expectedId, vipCard.getInvoiceQuota().intValue());
        assertEquals(VipCardStatus.ACTIVE, vipCard.getStatus());
        assertEquals(expectedId, vipCard.getPoint());
        assertEquals(expectedId, vipCard.getGrade());
        assertEquals(expectedIdStr, vipCard.getIdCardNumber());
        assertEquals(TestHelper.parseDate(false, "1986-07-0" + expectedIdStr), vipCard.getBirthDate());
    }

    private static void assertTransaction(long expectedId, VipCardTransaction transaction){
        assertEquals(expectedId, transaction.getId());
        final int[] AMOUNT = new int[]{100, 100, 25, 50, 25};
        final Integer[] BONUS = new Integer[]{25, 25, null, null, null};
        final Integer[] BALANCE_BEFORE = new Integer[]{0, 125, 250, 225, 175, null};
        final Integer[] BALANCE_AFTER = new Integer[]{125, 250, 225, 175, 200, null};
        final PayType[] PAY_TYPE = new PayType[]{PayType.CASH, PayType.UNI_PAY, PayType.CASH,
                PayType.UNI_PAY, PayType.UNI_PAY, null};
        final VipCardTransactionType[] TRANSACTION_TYPE = new VipCardTransactionType[]{
                VipCardTransactionType.RECHARGE,
                VipCardTransactionType.RECHARGE,
                VipCardTransactionType.CONSUME,
                VipCardTransactionType.CONSUME,
                VipCardTransactionType.REFUND,
                VipCardTransactionType.INVOICE
        };
        final Integer[] INVOICE_AMOUNT = new Integer[]{50, 50, null, null, null, 100};

        int index = (int) expectedId - 1;
        assertValue(AMOUNT[index], transaction.getAmount());
        assertValue(BONUS[index], transaction.getBonus());
        assertValue(BALANCE_BEFORE[index], transaction.getBalanceBefore());
        assertValue(BALANCE_AFTER[index], transaction.getBalanceAfter());
        assertValue(PAY_TYPE[index], transaction.getPayType());
        assertValue(TRANSACTION_TYPE[index], transaction.getTransactionType());
        assertValue(INVOICE_AMOUNT[index], transaction.getInvoiceAmount());
    }

    private static void assertValue(Object expected, Object actual){
        if(expected == null){
            assertNull(actual);
        }else if(actual instanceof BigDecimal){
            assertEquals(((Integer) expected).intValue(), ((BigDecimal) actual).intValue());
        }else{
            assertEquals(expected, actual);
        }
    }

    private static VipCardTransaction createTransaction(VipCardTransactionType transactionType){
        VipCardTransaction transaction = new VipCardTransaction();
        transaction.setTransactionType(transactionType);

        if(transactionType != VipCardTransactionType.REFUND &&
                transactionType != VipCardTransactionType.RECHARGE){
            transaction.setInvoiceAmount(new BigDecimal("100"));
        }
        if(transactionType != VipCardTransactionType.INVOICE){
            transaction.setAmount(new BigDecimal("200"));
            transaction.setPayType(PayType.UNI_PAY);
            transaction.setNumber("new");
        }
        if(transactionType == VipCardTransactionType.RECHARGE){
            transaction.setBonus(new BigDecimal("50"));
            transaction.setPoint(10);
        }
        if(transactionType == VipCardTransactionType.CONSUME){
            transaction.setPoint(10);
        }

        transaction.setVipCardId(1);
        Branch branch = new Branch();
        branch.setId(1);
        transaction.setBranch(branch);
        Account handler = new Account();
        handler.setId(2);
        transaction.setHandler(handler);
        transaction.setCreatedAt(new Date());
        return transaction;
    }

    /**
     * Sets up the test environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        TestHelper.resetData();
        service = TestHelper.Context.getBean(VipCardService.class);
    }

    /**
     * Tests the {@link VipCardService#save(VipCard)} method.
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception{
        VipCard vipCard = new VipCard();
        vipCard.setHqId(TestHelper.HQ_ID);
        vipCard.setCreatedAt(new Date());
        Branch branch = new Branch();
        branch.setId(2);
        vipCard.setBranch(branch);
        vipCard.setGender("female");
        vipCard.setBalance(new BigDecimal("1000"));
        vipCard.setInvoiceQuota(new BigDecimal("500"));
        vipCard.setGrade(5);
        vipCard.setPoint(5);
        vipCard.setStatus(VipCardStatus.ACTIVE);
        vipCard.setMobile("5");
        vipCard.setName("card5");
        vipCard.setNumber("5");
        vipCard.setPassword("password5");
        vipCard.setIdCardNumber("5");
        vipCard.setBirthDate(TestHelper.parseDate(false, "1986-07-05"));
        service.save(vipCard);

        assertVipCard(5, service.findOne(vipCard.getId()));
    }

    /**
     * Tests the {@link VipCardService#save(VipCard)} method, with a VIP card with the same number and headquarter
     * exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveExists() throws CoreServiceException{
        VipCard vipCard = new VipCard();
        vipCard.setHqId(TestHelper.HQ_ID);
        vipCard.setCreatedAt(new Date());
        Branch branch = new Branch();
        branch.setId(2);
        vipCard.setGender("female");
        vipCard.setBranch(branch);
        vipCard.setBalance(new BigDecimal("1000"));
        vipCard.setInvoiceQuota(new BigDecimal("500"));
        vipCard.setGrade(5);
        vipCard.setPoint(5);
        vipCard.setStatus(VipCardStatus.ACTIVE);
        vipCard.setMobile("5");
        vipCard.setName("card5");
        vipCard.setNumber("1");
        vipCard.setPassword("password5");
        service.save(vipCard);

        fail();
    }

    /**
     * Tests the {@link VipCardService#update(VipCard)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void update() throws CoreServiceException{
        VipCard vipCard = service.findOne(3);
        vipCard.setMobile("new mobile");
        vipCard.setPassword("new password");
        vipCard.setName("new name");
        vipCard.setGender("male");
        vipCard.setStatus(VipCardStatus.FROZEN);
        service.update(vipCard);

        VipCard actual = service.findOne(3);
        assertEquals("new mobile", actual.getMobile());
        assertEquals("new name", actual.getName());
        assertEquals(VipCardStatus.FROZEN, vipCard.getStatus());
        assertEquals("male", vipCard.getGender());
    }

    /**
     * Tests the {@link VipCardService#updateStatus(long, VipCardStatus)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void updateStatus() throws CoreServiceException{
        service.updateStatus(1, VipCardStatus.FROZEN);
        assertEquals(VipCardStatus.FROZEN, service.findOne(1).getStatus());
    }

    /**
     * Failure test for the {@link VipCardService#updateStatus(long, VipCardStatus)} method, updating with the VIP card
     * status DISABLED. {@link EntityUnavailableException} is expected.
     *
     * @throws EntityUnavailableException
     */
    @Test(expected = EntityUnavailableException.class)
    public void updateStatusWithDisabled() throws Exception{
        VipCard vipCard = new VipCard();
        vipCard.setHqId(TestHelper.HQ_ID);
        vipCard.setCreatedAt(new Date());
        Branch branch = new Branch();
        branch.setId(2);
        vipCard.setBranch(branch);
        vipCard.setGender("female");
        vipCard.setBalance(new BigDecimal("1000"));
        vipCard.setInvoiceQuota(new BigDecimal("500"));
        vipCard.setGrade(5);
        vipCard.setPoint(5);
        vipCard.setStatus(VipCardStatus.DISABLED);
        vipCard.setMobile("5");
        vipCard.setName("card5");
        vipCard.setNumber("5");
        vipCard.setPassword("password5");
        vipCard.setIdCardNumber("5");
        vipCard.setBirthDate(TestHelper.parseDate(false, "1986-07-05"));
        service.save(vipCard);

        service.updateStatus(vipCard.getId(), VipCardStatus.FROZEN);
    }

    /**
     * Failure test for the {@link VipCardService#updateStatus(long, VipCardStatus)} method, with VIP card not found.
     * {@link EntityNotFoundException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityNotFoundException.class)
    public void updateStatusNotFound() throws CoreServiceException{
        service.updateStatus(99, VipCardStatus.FROZEN);
        fail();
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, recharging.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionRecharge() throws CoreServiceException{
        service.saveTransaction(createTransaction(VipCardTransactionType.RECHARGE));
        VipCard vipCard = service.findOne(1);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.RECHARGE);
    }

    /**
     * Test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, recharging with invoice quota
     * insufficient.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionRechargeInvoiceInsufficient() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.RECHARGE);
        transaction.setInvoiceAmount(new BigDecimal("350"));
        service.saveTransaction(transaction);
        VipCard vipCard = service.findOne(1);
        assertEquals(-50, vipCard.getInvoiceQuota().intValue());
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, recharging with point null.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionRechargeWithPointNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.RECHARGE);
        transaction.setPoint(null);
        service.saveTransaction(transaction);

        VipCard vipCard = service.findOne(1);
        assertEquals(1, vipCard.getPoint());
        vipCard.setPoint(11);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertNull(actual.getPoint());
        actual.setPoint(10);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.RECHARGE);
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, recharging with invoice null.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionRechargeWithInvoice() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.RECHARGE);
        transaction.setInvoiceAmount(new BigDecimal("100"));
        service.saveTransaction(transaction);

        VipCard vipCard = service.findOne(1);
        assertEquals(200, vipCard.getInvoiceQuota().intValue());
        vipCard.setInvoiceQuota(new BigDecimal("300"));
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertEquals(100, actual.getInvoiceAmount().intValue());
        actual.setInvoiceAmount(null);

        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.RECHARGE);
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionConsume() throws CoreServiceException{
        service.saveTransaction(createTransaction(VipCardTransactionType.CONSUME));
        VipCard vipCard = service.findOne(1);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.CONSUME);
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming with invoice null.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionConsumeWithInvoiceNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.CONSUME);
        transaction.setInvoiceAmount(null);
        service.saveTransaction(transaction);

        VipCard vipCard = service.findOne(1);
        assertEquals(100, vipCard.getInvoiceQuota().intValue());
        vipCard.setInvoiceQuota(BigDecimal.ZERO);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertNull(actual.getInvoiceAmount());
        actual.setInvoiceAmount(new BigDecimal("100"));
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming with point null.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionConsumeWithPointNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.CONSUME);
        transaction.setPoint(null);
        service.saveTransaction(transaction);

        VipCard vipCard = service.findOne(1);
        assertEquals(1, vipCard.getPoint());
        vipCard.setPoint(11);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertNull(actual.getPoint());
        actual.setPoint(10);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.CONSUME);
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming with bonus not
     * null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionConsumeWithBonusNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.CONSUME);
        transaction.setBonus(new BigDecimal("10"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, refunding.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionRefund() throws CoreServiceException{
        service.saveTransaction(createTransaction(VipCardTransactionType.REFUND));
        VipCard vipCard = service.findOne(1);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.REFUND);
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, refunding with invoice
     * not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionRefundWithInvoiceNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.REFUND);
        transaction.setInvoiceAmount(new BigDecimal("10"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, refunding with point not
     * null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionRefundWithPointNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.REFUND);
        transaction.setPoint(10);
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, refunding with bonus not
     * null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionRefundWithBonusNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.REFUND);
        transaction.setBonus(new BigDecimal("10"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Tests the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice.
     *
     * @throws CoreServiceException
     */
    @Test
    public void saveTransactionInvoice() throws CoreServiceException{
        service.saveTransaction(createTransaction(VipCardTransactionType.INVOICE));
        VipCard vipCard = service.findOne(1);
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(7, result.getItems().size());
        VipCardTransaction actual = result.getItems().get(6);
        assertCardAndTransaction(vipCard, actual, VipCardTransactionType.INVOICE);
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice with
     * transaction number not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionInvoiceWithNumberNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.INVOICE);
        transaction.setNumber("new");
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice with
     * point not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionInvoiceWithPointNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.INVOICE);
        transaction.setPoint(10);
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice with
     * bonus not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionInvoiceWithBonusNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.INVOICE);
        transaction.setBonus(new BigDecimal("10"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice with
     * amount not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionInvoiceWithAmountNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.INVOICE);
        transaction.setAmount(new BigDecimal("10"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, getting invoice with
     * invoice amount not null. {@link IllegalArgumentException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void saveTransactionInvoiceWithInvoiceAmountNotNull() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.INVOICE);
        transaction.setInvoiceAmount(null);
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming with balance
     * insufficient. {@link InsufficientBalanceException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = InsufficientBalanceException.class)
    public void saveTransactionConsumeBalanceInsufficient() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.CONSUME);
        transaction.setAmount(new BigDecimal("250"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, consuming with invoice
     * quota insufficient. {@link InsufficientBalanceException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = InsufficientBalanceException.class)
    public void saveTransactionConsumeInvoiceInsufficient() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.CONSUME);
        transaction.setAmount(new BigDecimal("250"));
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, recharging with VIP card
     * not found. {@link EntityNotFoundException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityNotFoundException.class)
    public void saveTransactionRechargeNotFound() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.RECHARGE);
        transaction.setVipCardId(99);
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Failure test for the {@link VipCardService#saveTransaction(VipCardTransaction)} method, with a transaction with
     * the same number already exists. {@link EntityExistsException} is expected.
     *
     * @throws CoreServiceException
     */
    @Test(expected = EntityExistsException.class)
    public void saveTransactionExists() throws CoreServiceException{
        VipCardTransaction transaction = createTransaction(VipCardTransactionType.RECHARGE);
        transaction.setNumber("1");
        service.saveTransaction(transaction);
        fail();
    }

    /**
     * Tests the {@link VipCardService#search(VipCardSearchFilter)} method.
     *
     * @throws Exception
     */
    @Test
    public void search() throws Exception{
        VipCardSearchFilter filter = new VipCardSearchFilter();
        filter.setHqId(1L);
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<VipCard> result = service.search(filter);
        assertEquals(2, result.getItems().size());
        assertEquals(4, result.getCount());
        assertEquals(2, result.getTotalPages());
        assertEquals(2, result.getPage().intValue());
        assertEquals(2, result.getSize().intValue());
        for(int i = 0; i < result.getItems().size(); i++){
            assertVipCard(2 - i, result.getItems().get(i));
        }
    }

    /**
     * Tests the {@link VipCardService#searchTransactions(VipCardTransactionSearchFilter)} method.
     *
     * @throws CoreServiceException
     */
    @Test
    public void searchTransactions() throws CoreServiceException{
        VipCardTransactionSearchFilter filter = new VipCardTransactionSearchFilter();
        filter.setVipCardId(1);
        filter.setPage(2);
        filter.setSize(2);
        filter.setSortBy("id");
        filter.setSortOrder(SortOrder.DESC);
        SearchResult<VipCardTransaction> result = service.searchTransactions(filter);
        assertEquals(2, result.getItems().size());
        assertEquals(6, result.getCount());
        assertEquals(3, result.getTotalPages());
        assertEquals(2, result.getPage().intValue());
        assertEquals(2, result.getSize().intValue());
        for(int i = 0; i < result.getItems().size(); i++){
            assertTransaction(4 - i, result.getItems().get(i));
        }
    }

    private void assertCardAndTransaction(VipCard vipCard, VipCardTransaction transaction,
                                          VipCardTransactionType transactionType) throws CoreServiceException{
        assertEquals(2, transaction.getHandler().getId());
        assertEquals("account2", transaction.getHandler().getFullname());
        assertEquals(1, transaction.getBranch().getId());
        assertEquals("branch1", transaction.getBranch().getName());
        assertEquals(transactionType, transaction.getTransactionType());
        if(transactionType == VipCardTransactionType.INVOICE){
            assertNull(transaction.getNumber());
        }else{
            assertEquals("new", transaction.getNumber());
        }
        if(transaction.getTransactionType() == VipCardTransactionType.INVOICE){
            assertEquals(200, vipCard.getBalance().intValue());
            assertNull(transaction.getAmount());
            assertNull(transaction.getBalanceBefore());
            assertNull(transaction.getBalanceAfter());
            assertNull(transaction.getBonus());
            assertNull(transaction.getPoint());
            assertNull(transaction.getPayType());
            assertEquals(100, transaction.getInvoiceAmount().intValue());
        }else{
            assertEquals(PayType.UNI_PAY, transaction.getPayType());
            assertEquals(200, transaction.getBalanceBefore().intValue());
            if(transaction.getTransactionType() == VipCardTransactionType.REFUND){
                assertNull(transaction.getInvoiceAmount());
                assertNull(transaction.getBonus());
                assertNull(transaction.getPoint());
                assertEquals(400, transaction.getBalanceAfter().intValue());
            }else{
                assertEquals(10, transaction.getPoint().intValue());
                if(transaction.getTransactionType() == VipCardTransactionType.CONSUME){
                    assertEquals(0, transaction.getBalanceAfter().intValue());
                    assertEquals(0, vipCard.getBalance().intValue());
                    assertNull(transaction.getBonus());
                    assertEquals(100, transaction.getInvoiceAmount().intValue());
                }else{
                    assertEquals(450, transaction.getBalanceAfter().intValue());
                    assertEquals(450, vipCard.getBalance().intValue());
                    assertEquals(50, transaction.getBonus().intValue());
                    assertNull(transaction.getInvoiceAmount());
                }
            }
        }

        assertEquals(transaction.getPoint() == null ? 1 : 11, vipCard.getPoint());
        if(transactionType != VipCardTransactionType.RECHARGE){
            assertEquals(transaction.getInvoiceAmount() == null ? 100 : 0, vipCard.getInvoiceQuota().intValue());
        }else{
            assertEquals(300, vipCard.getInvoiceQuota().intValue());
        }
    }
}
