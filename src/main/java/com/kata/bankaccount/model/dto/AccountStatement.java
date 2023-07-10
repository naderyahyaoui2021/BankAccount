package com.kata.bankaccount.model.dto;

import com.kata.bankaccount.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class AccountStatement {
    BigDecimal balance;
    List<Transaction> transactions;

    @Override
    public String toString() {
        return "AccountStatement{" + "balance=" + balance + ", transactions=" + transactions + '}';
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}