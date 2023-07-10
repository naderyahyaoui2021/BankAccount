package com.kata.bankaccount.service;

import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.model.Account;
import com.kata.bankaccount.model.dto.AccountStatement;

import java.math.BigDecimal;


public interface TransactionService {

    BigDecimal deposit(Account account, BigDecimal amount) throws BuisnessException;
    BigDecimal withdrowal(Account account, BigDecimal amount) throws BuisnessException;
    AccountStatement getTransactionHistory(Account account);

}
