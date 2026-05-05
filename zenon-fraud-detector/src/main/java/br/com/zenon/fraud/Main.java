package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Transaction transacao1 = new Transaction(1, TransactionType.PAYMENT, new BigDecimal("9839.64"),
                new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
                new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")),
                false, false);
        Transaction transacao2 = new Transaction(2, TransactionType.CASH_OUT, new BigDecimal("850002.52"),
                new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
                true, false);

        System.out.println(transacao1);
        System.out.println(transacao2);

        System.out.println("------------------------------------");
        System.out.println("Requisitos tarefa 3\n");

        String arquivo = "PS_20174392719_1491204439457_log.csv";

        TransactionIngestor transactionIngestor = new TransactionIngestor();

        List<Transaction> transactions = transactionIngestor.read(arquivo);

        for (int i = 0; i < 10; i++) {
            System.out.println(transactions.get(i));

        }

        System.out.println("------------------------------------");
        System.out.println("Requisitos tarefa 4\n");

        String arquivo2 = "paysim_with_bad_data.csv";

        List<Transaction> transactionsBadData = transactionIngestor.read(arquivo2);

        for (int i = 0; i < transactionsBadData.size(); i++) {
            System.out.println(transactionsBadData.get(i));

        }

        System.out.println("------------------------------------");
        System.out.println("Requisitos tarefa 5\n");

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactions);

        int countFrauds = fraudAnalyzer.countFrauds();
        System.out.println("1. Total de Fraudes: " + countFrauds);

        List<Transaction> highestAmounts = fraudAnalyzer.findHighestFrauds(3);
        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        highestAmounts.forEach(transaction -> System.out.println(transaction.amount().toPlainString()));
        System.out.println("3. Clientes Suspeitos:");

        List<String> suspiciousClients = fraudAnalyzer.findTopSuspiciousClients(5);
        suspiciousClients.forEach(System.out::println);

        BigDecimal totalLost = fraudAnalyzer.sumFraudAmount();
        System.out.println("4. Prejuízo Total: " + totalLost.toPlainString());
        System.out.println("5. Fraudes por Tipo:");

        Map<TransactionType, Long> fraudCountByType = fraudAnalyzer.countFraudsByType();
        fraudCountByType.forEach((type, count) -> System.out.println(type + ": " + count));

}
}
