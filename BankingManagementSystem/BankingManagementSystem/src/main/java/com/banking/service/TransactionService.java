package com.banking.service;

import com.banking.dao.TransactionDAO;
import com.banking.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public List<Transaction> getAccountTransactions(long accountId) throws SQLException {
        return transactionDAO.findByAccount(accountId);
    }
}
