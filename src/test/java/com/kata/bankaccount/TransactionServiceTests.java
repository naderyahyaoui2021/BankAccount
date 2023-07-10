package com.kata.bankaccount;


import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.model.dto.AccountStatement;
import com.kata.bankaccount.model.type.OperationType;
import com.kata.bankaccount.service.TransactionService;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionServiceTests {
    private static final Account account1= new Account(BigInteger.ONE, BigDecimal.valueOf(1000));
    private final TransactionService transactionService = new TransactionServiceImpl();
    @AfterEach
     void afterEach(){
        account1.setBalance(BigDecimal.valueOf(1000));
    }
    @Test
    void deposit_nominal() throws BuisnessException {

        BigDecimal result = transactionService.deposit(account1, BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(1100),result);
    }

    @Test
    void withdrawal_nominal() throws BuisnessException {

        BigDecimal result = transactionService.withdrowal(account1, BigDecimal.valueOf(200));
        Assert.assertEquals(BigDecimal.valueOf(800),result);
    }

    @Test
    void getHistory_nominal() throws BuisnessException {
        transactionService.deposit(account1, BigDecimal.valueOf(100));
        transactionService.withdrowal(account1, BigDecimal.valueOf(200));

        AccountStatement result = transactionService.getTransactionHistory(account1);

        Assert.assertEquals(BigDecimal.valueOf(900),result.getBalance());
        Assert.assertEquals(result.getTransactions().size(),2);
        Assert.assertEquals(result.getTransactions().get(0).getAccountId(),account1.getId());
        Assert.assertEquals(result.getTransactions().get(0).getAmount(),BigDecimal.valueOf(100));
        Assert.assertNotNull(result.getTransactions().get(0).getCreationDate());
        Assert.assertNotNull(result.getTransactions().get(0).getTransactionId());
        Assert.assertEquals(result.getTransactions().get(0).getOperation(), OperationType.DEPOSIT);

        Assert.assertEquals(result.getTransactions().get(1).getAccountId(),account1.getId());
        Assert.assertEquals(result.getTransactions().get(1).getAmount(),BigDecimal.valueOf(200));
        Assert.assertNotNull(result.getTransactions().get(1).getCreationDate());
        Assert.assertNotNull(result.getTransactions().get(1).getTransactionId());
        Assert.assertEquals(result.getTransactions().get(1).getOperation(), OperationType.WITHDRAWL);

    }

    @Test
    void deposit_fail_case() {

        assertThrows(BuisnessException.class, () -> transactionService.deposit(account1, BigDecimal.valueOf(-100)));
    }

    @Test
    void withdrawal_fail_case()  {

        assertThrows(BuisnessException.class, () -> transactionService.withdrowal(account1, BigDecimal.valueOf(2000)));
    }

}
