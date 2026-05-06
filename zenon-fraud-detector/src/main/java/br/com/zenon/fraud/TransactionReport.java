package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {
    public static final int FRAUD_LIMIT = 100_000;

    private record ReportTransaction(BigDecimal amount, boolean isFraud) {
    }

    public record Statistics(long totalTransactions, long totalFrauds, BigDecimal totalAmount) {
    }

    public Statistics generateReport(String documentName){
        Path path = Path.of("./data/" + documentName);

        try (Stream<String> lines = Files.lines(path)){
            return lines
                    .skip(1)
                    .map(this::parseReportTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(
                            new Statistics(0,0,BigDecimal.ZERO),
                            (Statistics acc, ReportTransaction rt) -> {
                                return new Statistics(acc.totalTransactions + 1,
                                        acc.totalFrauds + (rt.isFraud? 1:0),
                                        acc.totalAmount.add(rt.amount));
                            },(s1, s2) -> s1
                    );


        } catch (Exception e) {
            System.err.println("ERRO: " + e);
            return new Statistics(0,0,BigDecimal.ZERO);
        }
    }

    private Optional<ReportTransaction> parseReportTransaction(String line) {
        String[] lineArray = line.split(",");
        try{
            BigDecimal amount = new BigDecimal(lineArray[2]);
            boolean isFraud = "1".equals(lineArray[9]);
            return Optional.of(new ReportTransaction(amount, isFraud));
        }catch (Exception e) {
            System.err.println("ERRO: " + line + " | " + e);
            return Optional.empty();

        }


    }
}
