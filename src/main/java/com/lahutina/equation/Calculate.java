package com.lahutina.equation;

import com.lahutina.jdbc.Dao;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Class that does all calculations
 * divides equation into two parts,
 * calculates each part and compares
 */
public class Calculate {

    // Possible root values of unknown literal variables
    private final Variables roots;
    // Results of two parts
    private final Double[] twoPartsRes = new Double[2];
    // Initial equation
    private String strExp;
    // Initial equation divided into two strings after "=" and before
    private String[] twoPartsExp = new String[2];

    /**
     * Constructor that assigns initial equation str
     * and possible values of variables
     *
     * @param strExp initial string equation
     * @param roots  possible values of letters in equation
     */
    public Calculate(String strExp, String roots) {
        this.strExp = strExp;
        this.roots = new Variables(roots);
    }

    /**
     * Removes all white spaces
     * splits one equation to two: after and before "="
     * calculates values of two parts,
     * compares the results and saves(or not) to the database
     */
    public void doCalculate() {
        this.strExp = strExp.replaceAll(" ", "");
        twoPartsExp = strExp.split("=");

        Equation firstPart = new Equation(twoPartsExp[0], roots);
        twoPartsRes[0] = firstPart.doOperations();

        Equation secondPart = new Equation(twoPartsExp[1], roots);
        twoPartsRes[1] = secondPart.doOperations();

        saveToDB();
    }

    /**
     * Saves equation (and variables) to the database
     */
    private void saveToDB() {
        if (Objects.equals(twoPartsRes[0], twoPartsRes[1])) {
            try {
                if (!roots.toString().equals("")) {
                    Dao.insertEquationAndRoots(this);
                    System.out.println("Equation and variables are correct, was saved to database");
                } else {
                    Dao.insertEquation(this);
                    System.out.println("Equation is correct, was saved to database");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (!roots.toString().equals("")) {
                    System.out.println("Equation is correct, but variables are not, was saved only equation to database");
                    Dao.insertEquation(this);
                } else {
                    System.out.println("Equation is not correct, was not saved to database");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Two parts are not equal: " + twoPartsRes[0] + " and " + twoPartsRes[1]);
        }
    }

    //getters
    public String getStrExp() {
        return strExp;
    }

    public Variables getRoots() {
        return roots;
    }

    @Override
    public String toString() {
        return String.format("%-15s  |  %s\n", strExp, roots);
    }
}
