package br.com.zenon.fraud;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ReportMain {
    static void main() {
        Locale locale = Locale.of("pt", "br");
        var integgerFormatter = NumberFormat.getIntegerInstance(locale);
        var currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("USD"));


        String arquivo = "PS_20174392719_1491204439457_log.csv";

        System.out.println("------------------------------------");
        System.out.println("Requisitos tarefa 7\n");

        TransactionReport transactionReport = new TransactionReport();

        TransactionReport.Statistics statistics = transactionReport.generateReport(arquivo);
        String formattedTotalTransactions = integgerFormatter.format(statistics.totalTransactions());
        String formattedTotalFrauds = integgerFormatter.format(statistics.totalFrauds());
        String formattedTotalAmount = currencyFormatter.format(statistics.totalAmount());


        System.out.println("Total de linhas: " + formattedTotalTransactions);
        System.out.println("Total de fraudes: " + formattedTotalFrauds);
        System.out.println("Valor total transacionado: "    + formattedTotalAmount);
    }
}
