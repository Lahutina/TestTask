package com.lahutina.jdbc;

import com.lahutina.equation.Calculate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static final String INSERT_EQUATION_AND_ROOTS = "INSERT INTO  equations (equation, roots) VALUES (?, ?)";
    private static final String INSERT_EQUATION = "INSERT INTO  equations (equation) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM equations";


    /**
     * Inserts equation and roots to the database
     */
    public static void insertEquationAndRoots(Calculate equation) throws SQLException {
        PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(INSERT_EQUATION_AND_ROOTS);
        preparedStatement.setString(1, equation.getStrExp());
        preparedStatement.setString(2, equation.getRoots().toString());

        preparedStatement.executeUpdate();
    }

    /**
     * Inserts only equation to the database
     */
    public static void insertEquation(Calculate equation) throws SQLException {
        PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(INSERT_EQUATION);
        preparedStatement.setString(1, equation.getStrExp());

        preparedStatement.executeUpdate();
    }

    /**
     * Reeds all equations and roots from DB
     *
     * @return returns class with equation and roots
     */

    public static List<Calculate> getAll() {
        try {
            Statement statement = JDBCConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            List<Calculate> allEquation = new ArrayList<>();

            while (resultSet.next()) {
                allEquation.add(new Calculate(resultSet.getString(1), resultSet.getString(2)));
            }
            return allEquation;
        } catch (SQLException e) {
            System.out.println("Query failed.");
            e.printStackTrace();
        }
        return null;
    }
}
