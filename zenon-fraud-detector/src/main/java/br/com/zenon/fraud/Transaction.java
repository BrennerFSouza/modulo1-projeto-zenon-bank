package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType transationType,
        BigDecimal amount,
        TransactionCustomer origin,
        TransactionCustomer destin,
        boolean isFraud,
        boolean isFlaggedFraud){

    public Transaction{

        if (step <= 0)
            throw new IllegalArgumentException("step should be positive: " + step);
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("amount should be positive: " + amount);
    }


}