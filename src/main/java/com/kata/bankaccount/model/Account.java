package com.kata.bankaccount.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Account extends BaseEntity{

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account( BigInteger accountId,BigDecimal balance) {
        this.setId(accountId);
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        balance=balance.add(amount);
    }
    public void withdrawal(BigDecimal amount) {
        balance=balance.subtract(amount);
    }
}
