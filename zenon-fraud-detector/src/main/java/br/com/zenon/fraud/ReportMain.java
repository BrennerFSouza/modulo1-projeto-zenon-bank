package br.com.zenon.fraud;

import java.util.List;

public class ReportMain {
    static void main() {
        String arquivo = "PS_20174392719_1491204439457_log.csv";

        System.out.println("------------------------------------");
        System.out.println("Requisitos tarefa 7\n");

        TransactionReport transactionReport = new TransactionReport();

        TransactionReport.Statistics statistics = transactionReport.generateReport(arquivo);
        System.out.println("Total de linhas: " + statistics.totalTransactions());
        System.out.println("Total de fraudes: " + statistics.totalFrauds());
        System.out.println("Valor total transacionado: " + statistics.totalAmount().toPlainString());
    }
}
