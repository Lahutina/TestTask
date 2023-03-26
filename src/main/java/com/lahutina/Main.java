package com.lahutina;

import com.lahutina.jdbc.JDBCConnection;
import com.lahutina.menu.Menu;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.start();

        JDBCConnection.closeConnection();
    }
}
