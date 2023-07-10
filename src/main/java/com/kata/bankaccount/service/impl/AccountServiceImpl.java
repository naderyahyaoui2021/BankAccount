package com.kata.bankaccount.service.impl;

import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.service.AccountService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final List<Account> accounts = new ArrayList<>();


    @Override
    public void save(Account account) {
        accounts.add(account);

    }


    @Override
    public Optional<Account> getAccount(BigInteger accountId) {

        return accounts.stream().filter(account -> account.getId().equals(accountId)).findFirst();

    }

    @Override
    public void updateBalance(Account account, BigDecimal newBalance) {
        account.setBalance(newBalance);
    }
}
