package com.lahutina.jdbc;

import com.lahutina.equation.Calculate;

import java.sql.*;

public class Dao {

    private static final String INSERT_EQUATION_AND_ROOTS = "INSERT INTO  equations (equation, roots) VALUES (?, ?)";
    private static final String INSERT_EQUATION = "INSERT INTO  equations (equation) VALUES (?)";

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

    public static void insertEquationAndRoots(Calculate equation) throws SQLException {
        PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(INSERT_EQUATION_AND_ROOTS);
        preparedStatement.setString(1, equation.getStrExp());
        preparedStatement.setString(2, equation.getVariables().toString());

        preparedStatement.executeUpdate();
    }
    public static void insertEquation(Calculate equation) throws SQLException {
        PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(INSERT_EQUATION);
        preparedStatement.setString(1, equation.getStrExp());

        preparedStatement.executeUpdate();
    }

}
