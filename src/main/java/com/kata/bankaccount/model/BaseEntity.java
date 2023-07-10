package com.kata.bankaccount.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    private BigInteger Id;

    public BigInteger getId() {
        return Id;
    }

    public void setId(BigInteger id) {
        Id = id;
    }
}
