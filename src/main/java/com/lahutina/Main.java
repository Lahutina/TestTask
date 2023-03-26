package com.lahutina;

import com.lahutina.equation.Calculate;
import com.lahutina.jdbc.JDBCConnection;
import com.lahutina.menu.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.start();

       JDBCConnection.closeConnection();
    }

}
