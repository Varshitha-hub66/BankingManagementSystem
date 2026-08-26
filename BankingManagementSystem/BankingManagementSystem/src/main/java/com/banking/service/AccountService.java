package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.model.Account;
import com.banking.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AccountService {
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public long createAccount(int customerId, String type, BigDecimal openingBalance) throws SQLException {
        if (openingBalance == null || openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        String number = generateAccountNumber();
        return accountDAO.create(customerId, number, type, openingBalance);
    }

    public List<Account> getCustomerAccounts(int customerId) throws SQLException {
        return accountDAO.findByCustomer(customerId);
    }

    public void deposit(String accountNumber, BigDecimal amount) throws SQLException {
        validateAmount(amount);
        changeBalance(accountNumber, amount, true, "DEPOSIT", null, "Cash deposit");
    }

    public void withdraw(String accountNumber, BigDecimal amount) throws SQLException {
        validateAmount(amount);
        changeBalance(accountNumber, amount, false, "WITHDRAW", null, "Cash withdrawal");
    }

    public void transfer(String fromNumber, String toNumber, BigDecimal amount) throws SQLException {
        validateAmount(amount);
        if (fromNumber.equals(toNumber)) throw new IllegalArgumentException("Source and destination must differ.");

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                Account from = lockAccount(con, fromNumber);
                Account to = lockAccount(con, toNumber);

                if (from == null || to == null) throw new IllegalArgumentException("Account not found.");
                if (!"ACTIVE".equals(from.getStatus()) || !"ACTIVE".equals(to.getStatus()))
                    throw new IllegalArgumentException("Both accounts must be active.");
                if (from.getBalance().compareTo(amount) < 0)
                    throw new IllegalArgumentException("Insufficient balance.");

                updateBalance(con, from.getAccountId(), from.getBalance().subtract(amount));
                updateBalance(con, to.getAccountId(), to.getBalance().add(amount));

                transactionDAO.save(con, from.getAccountId(), "TRANSFER_OUT", amount, toNumber, "Transfer to " + toNumber);
                transactionDAO.save(con, to.getAccountId(), "TRANSFER_IN", amount, fromNumber, "Transfer from " + fromNumber);

                con.commit();
            } catch (Exception e) {
                con.rollback();
                if (e instanceof SQLException se) throw se;
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    private void changeBalance(String number, BigDecimal amount, boolean deposit,
                               String type, String related, String description) throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                Account account = lockAccount(con, number);
                if (account == null) throw new IllegalArgumentException("Account not found.");
                if (!"ACTIVE".equals(account.getStatus())) throw new IllegalArgumentException("Account is not active.");
                if (!deposit && account.getBalance().compareTo(amount) < 0)
                    throw new IllegalArgumentException("Insufficient balance.");

                BigDecimal newBalance = deposit
                        ? account.getBalance().add(amount)
                        : account.getBalance().subtract(amount);

                updateBalance(con, account.getAccountId(), newBalance);
                transactionDAO.save(con, account.getAccountId(), type, amount, related, description);
                con.commit();
            } catch (Exception e) {
                con.rollback();
                if (e instanceof SQLException se) throw se;
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    private Account lockAccount(Connection con, String number) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number=? FOR UPDATE";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, number);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return null;
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
    }

    private void updateBalance(Connection con, long accountId, BigDecimal balance) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE accounts SET balance=? WHERE account_id=?")) {
            ps.setBigDecimal(1, balance);
            ps.setLong(2, accountId);
            ps.executeUpdate();
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");
    }

    private String generateAccountNumber() {
        long value = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
        return String.valueOf(value);
    }
}
