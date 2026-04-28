package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType transationType,
        BigDecimal amount,
        TransactionCustomer origin,
        TransactionCustomer destin,
        boolean isFraud,
        boolean isFlaggedFraud) {



}

