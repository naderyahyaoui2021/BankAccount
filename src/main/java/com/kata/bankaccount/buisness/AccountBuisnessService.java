package com.kata.bankaccount.buisness;

import com.kata.bankaccount.buisness.exception.BuisnessException;
import com.kata.bankaccount.model.dto.AccountStatement;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface AccountBuisnessService {
    BigDecimal deposit(BigDecimal amount, BigInteger accountId) throws BuisnessException;

    BigDecimal withdraw(BigDecimal amount, BigInteger accountId) throws BuisnessException;
    AccountStatement printAccountStatement(BigInteger accountId) throws BuisnessException;
}
