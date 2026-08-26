CREATE DATABASE IF NOT EXISTS banking_db;
USE banking_db;

CREATE TABLE IF NOT EXISTS customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255),
    password_hash VARCHAR(64) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts (
    account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    account_type ENUM('SAVINGS','CURRENT') NOT NULL DEFAULT 'SAVINGS',
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    status ENUM('ACTIVE','INACTIVE','BLOCKED') NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_accounts_customer
        FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    transaction_type ENUM('DEPOSIT','WITHDRAW','TRANSFER_IN','TRANSFER_OUT') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    related_account VARCHAR(20),
    description VARCHAR(255),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transactions_account
        FOREIGN KEY (account_id) REFERENCES accounts(account_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL
);

INSERT INTO admins(username, password_hash)
SELECT 'admin', SHA2('admin123', 256)
WHERE NOT EXISTS (SELECT 1 FROM admins WHERE username = 'admin');
