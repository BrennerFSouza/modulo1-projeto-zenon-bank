package br.com.zenon.fraud;

import java.math.BigDecimal;

public class TransactionCustomer {
    private String name;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;

    public TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
        this.name = name;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }
}
