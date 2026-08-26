package com.banking.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class DBConnection {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = DBConnection.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IllegalStateException("database.properties not found");
            }
            PROPERTIES.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBConnection() {}

    public static Connection getConnection() throws java.sql.SQLException {
        return DriverManager.getConnection(
                PROPERTIES.getProperty("db.url"),
                PROPERTIES.getProperty("db.username"),
                PROPERTIES.getProperty("db.password")
        );
    }
}
