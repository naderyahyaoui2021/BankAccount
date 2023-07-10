package com.kata.bankaccount.model;

import com.kata.bankaccount.model.type.OperationType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Transaction extends BaseEntity {
    String transactionId;
    BigInteger accountId;
    LocalDateTime creationDate;
    BigDecimal amount;

    OperationType operation;
    private BigInteger Id;

    public Transaction(OperationType operationtype, BigInteger accountId, BigDecimal amount, LocalDateTime creationDate, String transactionId) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.creationDate = creationDate;
        this.amount = amount;
        this.operation = operationtype;
    }

    public BigInteger getId() {
        return Id;
    }

    public void setId(BigInteger id) {
        Id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }


    public BigInteger getAccountId() {
        return accountId;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }



    public BigDecimal getAmount() {
        return amount;
    }



    public OperationType getOperation() {
        return operation;
    }



    @Override
    public String toString() {

        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", operation=" + operation +
                ", date=" + creationDate +
                ", amount=" + amount +
                '}';
    }
}
