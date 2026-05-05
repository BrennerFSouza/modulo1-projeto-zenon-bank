package br.com.zenon.fraud;

import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository{

    @Override
    public Optional<Transaction> findTransactionByOriginName(String OriginName) {
        return Optional.empty();
    }
}
