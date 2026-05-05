package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<Transaction> findHighestFrauds(int limit){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .toList();

    }

    public List<String> findTopSuspiciousClients(int limit) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .map(transaction -> transaction.origin().name())
                .toList();
    }

    public BigDecimal sumFraudAmount() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Map<TransactionType, Long> countFraudsByType() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::transationType, Collectors.counting()));

    }
}
