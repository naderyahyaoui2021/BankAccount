package com.kata.bankaccount.buisness.impl;

import com.kata.bankaccount.buisness.AccountBuisnessService;
import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.model.dto.AccountStatement;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class AccountBuisnessServiceImpl implements AccountBuisnessService {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountBuisnessServiceImpl(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount, BigInteger accountId) throws BuisnessException {
        Optional<Account> account = accountService.getAccount(accountId);
        if (account.isPresent()) {
            BigDecimal newBalance = transactionService.deposit(account.get(), amount);
            accountService.updateBalance(account.get(), newBalance);
            return newBalance;
        } else {
            throw new BuisnessException("Account not found !!");
        }
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount, BigInteger accountId) throws BuisnessException {
        Optional<Account> account = accountService.getAccount(accountId);
        if (account.isPresent()) {
            BigDecimal newBalance = transactionService.withdrowal(account.get(), amount);
            accountService.updateBalance(account.get(), newBalance);
            return newBalance;

        } else {
            throw new BuisnessException("Account not found !!");
        }
    }

    @Override
    public AccountStatement printAccountStatement(BigInteger accountId) throws BuisnessException {
        Optional<Account> account = accountService.getAccount(accountId);
        if (account.isPresent()) {
            AccountStatement transactionHistory = transactionService.getTransactionHistory(account.get());
            // I used println to simulate a statement printing
            System.out.println(transactionHistory.toString());
            return transactionHistory;

        } else {
            throw new BuisnessException("Account not found !!");
        }
    }


}
