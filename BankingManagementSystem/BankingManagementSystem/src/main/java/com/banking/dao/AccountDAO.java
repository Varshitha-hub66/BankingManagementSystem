package com.banking.dao;

import com.banking.model.Account;
import com.banking.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public long create(int customerId, String accountNumber, String accountType, BigDecimal openingBalance) throws SQLException {
        String sql = "INSERT INTO accounts(customer_id,account_number,account_type,balance) VALUES(?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.setString(2, accountNumber);
            ps.setString(3, accountType);
            ps.setBigDecimal(4, openingBalance);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }
        return -1;
    }

    public Account findByNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return map(rs);
            }
        }
    }

    public List<Account> findByCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE customer_id=? ORDER BY created_at DESC";
        List<Account> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private Account map(ResultSet rs) throws SQLException {
        Account a = new Account();
        a.setAccountId(rs.getLong("account_id"));
        a.setCustomerId(rs.getInt("customer_id"));
        a.setAccountNumber(rs.getString("account_number"));
        a.setAccountType(rs.getString("account_type"));
        a.setBalance(rs.getBigDecimal("balance"));
        a.setStatus(rs.getString("status"));
        return a;
    }
}
