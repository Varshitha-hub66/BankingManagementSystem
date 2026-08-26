package com.banking.service;

import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;
import com.banking.util.PasswordUtil;

import java.sql.SQLException;

public class CustomerService {
    private final CustomerDAO customerDAO = new CustomerDAO();

    public boolean register(String name, String email, String phone, String address, String password)
            throws SQLException {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must contain at least 6 characters.");
        }
        Customer customer = new Customer(name, email, phone, address, PasswordUtil.sha256(password));
        return customerDAO.register(customer);
    }

    public Customer login(String email, String password) throws SQLException {
        return customerDAO.login(email, PasswordUtil.sha256(password));
    }
}
