package com.lahutina;

import com.lahutina.jdbc.Dao;
import com.lahutina.jdbc.JDBCConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Dao.execQuery();
        JDBCConnection.closeConnection();
    }

}
