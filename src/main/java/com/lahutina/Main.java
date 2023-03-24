package com.lahutina;

import com.lahutina.jdbc.Dao;
import com.lahutina.jdbc.JDBCConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        int choose = 0;

        while(choose!=1 && choose!=2) {
            choose = Input.inputOptions();
            if (choose == 1)
                Input.inputConsole();
            else if (choose == 2)
                Input.inputFile();
        }

        Dao.execQuery();

        JDBCConnection.closeConnection();
    }

}
