# Banking Management System

A Java web-based Banking Management System built using Java, Servlets, JSP, JDBC, MySQL and Apache Tomcat.

## Features
- Customer registration and login
- Customer dashboard
- Account creation
- Balance enquiry
- Deposit
- Withdrawal
- Money transfer
- Transaction history
- Admin login
- Admin customer/account/transaction views
- Session-based authentication
- Password hashing with SHA-256
- Prepared statements for database operations

## Technology Stack
- Java 17+
- Maven
- Servlet API
- JSP
- MySQL 8+
- JDBC
- Apache Tomcat 10+
- HTML/CSS/JavaScript

## Database Setup
1. Create a MySQL database.
2. Run `database/banking_database.sql`.
3. Update `src/main/resources/database.properties`.
4. Build with Maven:
   `mvn clean package`
5. Deploy the generated WAR from `target/` to Tomcat.
6. Open:
   `http://localhost:8080/BankingManagementSystem/`

## Default Admin
Username: admin
Password: admin123

Change the password before using this project in a real environment.

## GitHub
Push the complete project folder to GitHub. The `.gitignore` excludes generated Maven files such as `target/`.
