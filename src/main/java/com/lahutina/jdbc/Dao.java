package com.lahutina.jdbc;

import java.sql.*;

public class Dao {
    public static void execQuery()
    {
        // execute simple SQL query to try if all work
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = JDBCConnection.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM equations");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("roots"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed.");
            e.printStackTrace();
        }
    }
}
