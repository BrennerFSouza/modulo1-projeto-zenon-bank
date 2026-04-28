package br.com.zenon.fraud;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionIngestor {


    public List<Transaction> listarTransacoes(String documentName) {
        ArrayList<Transaction> transactions = new ArrayList<>();

            Path path = Path.of("./data/" + documentName);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            Scanner scanner = new Scanner(fis)) {
            int countLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");
                if (countLines == 0) {
                    countLines++;
                    continue;
                }
                if (countLines == 1001) {
                    break;
                }
                int step = Integer.parseInt(lineArray[0]);
                TransactionType type = TransactionType.valueOf(lineArray[1]);
                BigDecimal amount = new BigDecimal(lineArray[2]);

                TransactionCustomer origin = new TransactionCustomer(lineArray[3], new BigDecimal(lineArray[4]), new BigDecimal(lineArray[5]));
                TransactionCustomer destin = new TransactionCustomer(lineArray[6], new BigDecimal(lineArray[7]), new BigDecimal(lineArray[8]));
                boolean isFraud = Boolean.parseBoolean(lineArray[9]);
                boolean isFlaggedFraud = Boolean.parseBoolean(lineArray[10]);

                Transaction transaction = new Transaction(step, type, amount, origin, destin, isFraud, isFlaggedFraud);
                transactions.add(transaction);

                countLines++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}

