package br.com.zenon.fraud;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TransactionIngestor {
    public List<Optional<Transaction>> read(String documentName){
        Path path = Path.of("./data/" + documentName);
        try{
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(1000)
                    .map(this::parseTransaction)
                    .filter(Objects::nonNull)
                    .limit(1000)
                    .toList();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> readOld(String documentName) {
        ArrayList<Transaction> transactions = new ArrayList<>();

            Path path = Path.of("./data/" + documentName);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            Scanner scanner = new Scanner(fis)) {
            int countLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (countLines == 0) {
                    countLines++;
                    continue;
                }
                if (countLines == 1001) {
                    break;
                }

                var transaction = parseTransaction(line);

                transactions.add(transaction);

                countLines++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    private Optional<Transaction> parseTransaction(String line) {
        String[] lineArray = line.split(",");
        try{
            if (lineArray[0].isEmpty()) throw new
                    IllegalArgumentException("step should not be empty");
            if (lineArray[1].isEmpty())
                throw new IllegalArgumentException("type should not be empty");
            if (lineArray[2].isEmpty())
                throw new IllegalArgumentException("amount should not be empty");
            if (lineArray[3].isEmpty())
                throw new IllegalArgumentException("origin should not be empty");
            if (lineArray[4].isEmpty())
                throw new IllegalArgumentException("origin balance should not be empty");
            if (lineArray[5].isEmpty())
                throw new IllegalArgumentException("origin available should not be empty");
            if (lineArray[6].isEmpty())
                throw new IllegalArgumentException("destin should not be empty");
            if (lineArray[7].isEmpty())
                throw new IllegalArgumentException("destin balance should not be empty");
            if (lineArray[8].isEmpty())
                throw new IllegalArgumentException("destin available should not be empty");
            if (lineArray[9].isEmpty())
                throw new IllegalArgumentException("isFraud should not be empty");
            if (lineArray[10].isEmpty())
                throw new IllegalArgumentException("isFlaggedFraud should not be empty");


            int step = Integer.parseInt(lineArray[0]);
            if (step <= 0) throw new IllegalArgumentException("step should be positive: " + step);


            TransactionType type = TransactionType.valueOf(lineArray[1]);

            BigDecimal amount = new BigDecimal(lineArray[2]);
            if (amount.compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("amount should be positive: " + amount);

            TransactionCustomer origin = new TransactionCustomer(lineArray[3], new BigDecimal(lineArray[4]), new BigDecimal(lineArray[5]));
            if (origin.getOldBalance().compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("origin balance should be positive: " + origin.getOldBalance());
            if (origin.getNewBalance().compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("origin available should be positive: " + origin.getNewBalance());
            TransactionCustomer destin = new TransactionCustomer(lineArray[6], new BigDecimal(lineArray[7]), new BigDecimal(lineArray[8]));
            if (destin.getOldBalance().compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("destin balance should be positive: " + destin.getOldBalance());
            if (destin.getNewBalance().compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("destin available should be positive: " + destin.getNewBalance());
            boolean isFraud = "1".equals(lineArray[9]);
            boolean isFlaggedFraud = "1".equals(lineArray[10]);
            return Optional.of(new Transaction(step, type, amount, origin, destin, isFraud, isFlaggedFraud));
        }catch (Exception e) {
            System.err.println("ERRO: " + line + " | " + e);
            return Optional.empty();

        }


    }
}

