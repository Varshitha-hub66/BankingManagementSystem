package com.banking.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private long transactionId;
    private long accountId;
    private String transactionType;
    private BigDecimal amount;
    private String relatedAccount;
    private String description;
    private Timestamp transactionDate;

    public long getTransactionId() { return transactionId; }
    public void setTransactionId(long transactionId) { this.transactionId = transactionId; }
    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getRelatedAccount() { return relatedAccount; }
    public void setRelatedAccount(String relatedAccount) { this.relatedAccount = relatedAccount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}
