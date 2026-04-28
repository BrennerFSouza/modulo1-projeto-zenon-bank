package br.com.zenon.fraud;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionIngestor(String documentName) {
        try {
            Path path = Path.of(documentName);
            List<String> linesList = Files.readAllLines(path);
            for (int i = 1; i <= 1000; i++) {
                String[] line = linesList.get(i).split(",");
                int step = Integer.parseInt(line[0]);
                TransactionType type = TransactionType.valueOf(line[1]);
                BigDecimal amount = new BigDecimal(line[2]);

                TransactionCustomer origin = new TransactionCustomer(line[3], new BigDecimal(line[4]), new BigDecimal(line[5]));
                TransactionCustomer destin = new TransactionCustomer(line[6], new BigDecimal(line[7]), new BigDecimal(line[8]));
                boolean isFraud = Boolean.parseBoolean(line[9]);
                boolean isFlaggedFraud = Boolean.parseBoolean(line[10]);

                Transaction transaction = new Transaction(step, type, amount, origin, destin, isFraud, isFlaggedFraud);
                transactions.add(transaction);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> listarTransacoes() {
        return transactions;
    }
}

