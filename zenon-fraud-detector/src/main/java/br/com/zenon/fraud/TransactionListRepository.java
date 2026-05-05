package br.com.zenon.fraud;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository {
    private final List<Transaction> transactions;
    public TransactionListRepository(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public Optional<Transaction> findTransactionByOriginName(String OriginName){
            Objects.requireNonNull(OriginName);
            return transactions.stream()
                    .filter(transaction -> transaction.origin().name().equals(OriginName)).findFirst();

    }
}
