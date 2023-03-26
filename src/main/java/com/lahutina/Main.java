package com.lahutina;

import com.lahutina.equation.Calculate;
import com.lahutina.equation.Equation;
import com.lahutina.equation.Variables;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter equation: ");
        String equation = scanner.nextLine();

        System.out.println("Enter possible roots in form \"x=?, y=?\"");
        String variables = scanner.nextLine();

        Calculate calculate = new Calculate(equation, variables);
        calculate.doCalculate();

//        Dao.execQuery();
//
//        JDBCConnection.closeConnection();
    }

}
