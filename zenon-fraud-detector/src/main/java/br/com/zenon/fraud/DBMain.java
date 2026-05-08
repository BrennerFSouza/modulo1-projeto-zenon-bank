package br.com.zenon.fraud;

import java.sql.Connection;

public class DBMain {
    static void main() {
        Connection connection = ConnectionFactory.getConnection();
        System.out.println("Conexão estabelecida com sucesso!");
    }
}
