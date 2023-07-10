package com.kata.bankaccount.service;

import com.kata.bankaccount.model.Account;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public interface AccountService {
    void save(Account account);
    Optional<Account> getAccount(BigInteger accountId);

    void updateBalance(Account account, BigDecimal newBalance);
}
