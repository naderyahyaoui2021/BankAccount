package com.kata.bankaccount;


import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class AccountServiceTests {
    private final AccountService accountService = new AccountServiceImpl();
    private static final Account account1= new Account(BigInteger.ONE, BigDecimal.valueOf(1000));


    @BeforeEach
    void beforeEach(){
        accountService.save(account1);
    }

    @Test
    void get_nominal()  {
        Optional<Account> result = accountService.getAccount(account1.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getBalance(),BigDecimal.valueOf(1000));
    }

    @Test
    void update_nominal()  {
        accountService.updateBalance(account1,BigDecimal.valueOf(900));
        Optional<Account> result = accountService.getAccount(account1.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getBalance(),BigDecimal.valueOf(900));
    }
}

