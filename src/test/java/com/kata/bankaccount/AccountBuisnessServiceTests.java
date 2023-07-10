package com.kata.bankaccount;


import com.kata.bankaccount.buisness.AccountBuisnessService;
import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.buisness.impl.AccountBuisnessServiceImpl;
import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.model.dto.AccountStatement;
import com.kata.bankaccount.model.type.OperationType;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;
import junit.framework.Assert;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.BigInteger;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountBuisnessServiceTests {

    public static final BigInteger ACCOUNT_ID = BigInteger.ONE;
    private static final Account account2= new Account(ACCOUNT_ID, BigDecimal.valueOf(1000));

    private final AccountService accountService = new AccountServiceImpl();
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final AccountBuisnessService accountBuisnessService = new AccountBuisnessServiceImpl(accountService,transactionService);
    private boolean initAccountFlag=false;


    @BeforeEach
    void beforeEach(){
        if(!initAccountFlag) {
            accountService.save(account2);
            initAccountFlag=true;
        }

        }
    @Test
    @Order(1)
    void deposit_nominal() throws BuisnessException {

        BigDecimal result = accountBuisnessService.deposit(BigDecimal.valueOf(100), ACCOUNT_ID);
        Assert.assertEquals(BigDecimal.valueOf(1100),result);
    }
    @Test
    @Order(2)
    void withdrawal_nominal() throws BuisnessException {

        BigDecimal result = accountBuisnessService.withdraw(BigDecimal.valueOf(100), ACCOUNT_ID);
        Assert.assertEquals(BigDecimal.valueOf(1000),result);
    }

    /**
     * INFO : This test depends on deposit and withdrawal tests
     */
    @Test
    @Order(3)
    void getHistory_nominal() throws BuisnessException {


        AccountStatement result = accountBuisnessService.printAccountStatement(ACCOUNT_ID);

        Assert.assertEquals(BigDecimal.valueOf(1000),result.getBalance());
        Assert.assertEquals(result.getTransactions().size(),2);
        Assert.assertEquals(result.getTransactions().get(0).getAccountId(), ACCOUNT_ID);
        Assert.assertEquals(result.getTransactions().get(0).getAmount(),BigDecimal.valueOf(100));
        Assert.assertNotNull(result.getTransactions().get(0).getCreationDate());
        Assert.assertNotNull(result.getTransactions().get(0).getTransactionId());
        Assert.assertEquals(result.getTransactions().get(0).getOperation(), OperationType.DEPOSIT);

        Assert.assertEquals(result.getTransactions().get(1).getAccountId(), ACCOUNT_ID);
        Assert.assertEquals(result.getTransactions().get(1).getAmount(),BigDecimal.valueOf(100));
        Assert.assertNotNull(result.getTransactions().get(1).getCreationDate());
        Assert.assertNotNull(result.getTransactions().get(1).getTransactionId());
        Assert.assertEquals(result.getTransactions().get(1).getOperation(), OperationType.WITHDRAWL);
    }

}

