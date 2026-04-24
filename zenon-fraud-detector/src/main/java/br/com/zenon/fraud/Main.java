package br.com.zenon.fraud;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Transaction transacao1 = new Transaction(1L, Transaction.Type.PAYMENT, new BigDecimal(9839.64), "C1231006815", new BigDecimal(170136.0), new BigDecimal(160296.36), "M1979787155", new BigDecimal(0.0), new BigDecimal(0.0), false, false);
        Transaction transacao2 = new Transaction(2L, Transaction.Type.CASH_OUT, new BigDecimal(850002.52), "C1280323807", new BigDecimal(850002.52), new BigDecimal(0.0), "C873221189", new BigDecimal(6510099.11), new BigDecimal(7360101.63), true, false);

        System.out.println(transacao1);
        System.out.println(transacao2);

    }
}
