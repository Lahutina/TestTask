package com.lahutina;

import com.lahutina.equation.Equation;

import java.util.Scanner;

public class Input {
    private final static Scanner scanner = new Scanner(System.in);

    public static int inputOptions()
    {
        return scanner.nextInt();
    }

    public static void inputConsole()
    {
        Equation equation = new Equation(scanner.nextLine());
    }

    public static void inputFile()
    {

    }
}
