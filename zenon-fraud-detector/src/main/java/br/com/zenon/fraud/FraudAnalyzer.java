package br.com.zenon.fraud;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class FraudAnalyzer {
    private final List<Transaction> transactions;
    public FraudAnalyzer(List<Transaction> transactions){
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public int countFrauds(){
        return Math.toIntExact(transactions.stream()
                .filter(Transaction::isFraud)
                .count());
    }

    public List<Transaction> findHighestFrauds(long limit){
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .toList();

    }
}
