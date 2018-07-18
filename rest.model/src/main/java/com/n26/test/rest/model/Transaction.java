package com.n26.test.rest.model;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Rest model for Transaction
 */

public class Transaction {


    @NotNull(message = "missing field amount")
    private Double amount;

    @NotNull(message = "missing field timestamp")
    private Long timestamp;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
