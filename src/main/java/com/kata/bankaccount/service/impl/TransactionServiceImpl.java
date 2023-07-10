package com.kata.bankaccount.service.impl;

import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.model.Transaction;
import com.kata.bankaccount.model.dto.AccountStatement;
import com.kata.bankaccount.model.type.OperationType;
import com.kata.bankaccount.service.TransactionService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    private final List<Transaction> transactions=new ArrayList<>();
    @Override
    public BigDecimal deposit(Account account, BigDecimal amount) throws BuisnessException {
        if(amount.compareTo(BigDecimal.ZERO)>0)
        {
            account.deposit(amount);
            transactions.add(transactionBuilder(OperationType.DEPOSIT,account.getId(),amount));
            return account.getBalance();
        }

        else throw new BuisnessException("Deposit must be greater than 0");


    }

    @Override
    public BigDecimal withdrowal(Account account, BigDecimal amount) throws BuisnessException {
        if(amount.compareTo(account.getBalance())<=0)
        {
            account.withdrawal(amount);
            transactions.add(transactionBuilder(OperationType.WITHDRAWL,account.getId(),amount));
            return account.getBalance();
        }

        else throw new BuisnessException("insuffisant funds");


    }

    @Override
    public AccountStatement getTransactionHistory(Account account) {
        AccountStatement accountStatement =new AccountStatement();
        accountStatement.setBalance(account.getBalance());
        accountStatement.setTransactions(transactions.stream()
                .filter(transaction -> transaction.getAccountId().equals(account.getId()))
                .toList());
return accountStatement;
    }

    private Transaction transactionBuilder(OperationType operationtype, BigInteger accountId,BigDecimal amount)
    {
        return new Transaction( operationtype,  accountId, amount, LocalDateTime.now(), UUID.randomUUID().toString());
    }
}
