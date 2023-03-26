package com.lahutina.commands;

import com.lahutina.equation.Calculate;
import com.lahutina.menu.MenuInterface;

import java.util.Scanner;

public class AddEquation implements MenuInterface {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter equation: ");
        String equation = scanner.nextLine();

        System.out.println("Enter possible roots in form \"x=?, y=?\".\nIf there are no variables just press Enter");
        String variables = scanner.nextLine();

        Calculate calculate = new Calculate(equation, variables);
        calculate.doCalculate();
    }
}


