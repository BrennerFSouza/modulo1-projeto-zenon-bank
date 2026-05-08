package br.com.zenon.fraud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepository{
    public void insertNewTransaction(Transaction transaction){
        String sql = """
                INSERT INTO zenon_frauds.transactions
                (id, step, `type`, amount, name_origin, old_balance_origin, new_balance_origin, name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;

        
    }


    @Override
    public Optional<Transaction> findTransactionByOriginName(String originName) {
        String sql = """
                SELECT id, step, `type`, amount, name_origin, old_balance_origin, new_balance_origin, name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, is_flagged_fraud
                FROM zenon_frauds.transactions
                where name_origin = ?
                LIMIT 1;
                """;
        try (
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, originName);

            try (var rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Nenhum resultado encontrado: " + originName);
                    return Optional.empty();
                }
                IO.println(rs.getString("name_origin"));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
