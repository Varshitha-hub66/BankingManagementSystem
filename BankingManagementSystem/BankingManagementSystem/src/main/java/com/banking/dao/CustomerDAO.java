package com.banking.dao;

import com.banking.model.Customer;
import com.banking.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    public boolean register(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers(full_name,email,phone,address,password_hash) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getPasswordHash());
            return ps.executeUpdate() == 1;
        }
    }

    public Customer login(String email, String passwordHash) throws SQLException {
        String sql = "SELECT customer_id,full_name,email,phone,address,password_hash FROM customers WHERE email=? AND password_hash=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setFullName(rs.getString("full_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setPasswordHash(rs.getString("password_hash"));
                return c;
            }
        }
    }

    public Customer findById(int id) throws SQLException {
        String sql = "SELECT customer_id,full_name,email,phone,address FROM customers WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setFullName(rs.getString("full_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                return c;
            }
        }
    }
}
