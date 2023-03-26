package com.lahutina;

import com.lahutina.equation.Calculate;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        // TODO
        System.out.println("Here must be a menu: 1. Enter equation. " +
                                                "2. Find equation with one root. " +
                                                "3. Find equation with specific root number" +
                                                "4. Print all equation" +
                                                "5. Add roots to specific equation");


        System.out.println("Enter equation: ");
        String equation = scanner.nextLine();

        System.out.println("Enter possible roots in form \"x=?, y=?\".\nIf there are no variables just press Enter");
        String variables = scanner.nextLine();

        Calculate calculate = new Calculate(equation, variables);
        calculate.doCalculate();

//        Dao.execQuery();
//
//        JDBCConnection.closeConnection();
    }

}
