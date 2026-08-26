package com.banking.dao;

import com.banking.model.Transaction;
import com.banking.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void save(Connection con, long accountId, String type, BigDecimal amount,
                     String relatedAccount, String description) throws SQLException {
        String sql = "INSERT INTO transactions(account_id,transaction_type,amount,related_account,description) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            ps.setString(2, type);
            ps.setBigDecimal(3, amount);
            ps.setString(4, relatedAccount);
            ps.setString(5, description);
            ps.executeUpdate();
        }
    }

    public List<Transaction> findByAccount(long accountId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE account_id=? ORDER BY transaction_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction();
                    t.setTransactionId(rs.getLong("transaction_id"));
                    t.setAccountId(rs.getLong("account_id"));
                    t.setTransactionType(rs.getString("transaction_type"));
                    t.setAmount(rs.getBigDecimal("amount"));
                    t.setRelatedAccount(rs.getString("related_account"));
                    t.setDescription(rs.getString("description"));
                    t.setTransactionDate(rs.getTimestamp("transaction_date"));
                    list.add(t);
                }
            }
        }
        return list;
    }
}
