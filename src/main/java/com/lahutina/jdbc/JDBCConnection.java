package com.lahutina.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String DATABASE_URL = System.getenv("DB_URL");
    private static final String DATABASE_USERNAME = System.getenv("DB_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DB_PASSWORD");
    private static Connection instance;

    private JDBCConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        return instance;
    }

    public static void closeConnection() throws SQLException {
        if (instance != null) {
            instance.close();
            instance = null;
        }

    }
}