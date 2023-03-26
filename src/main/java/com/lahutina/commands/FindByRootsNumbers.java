package com.lahutina.commands;

import com.lahutina.equation.Calculate;
import com.lahutina.jdbc.Dao;
import com.lahutina.menu.MenuInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that reads all data from database,
 * asks user for 1 number and looks for
 * equations where roots contain this number
 */
public class FindByRootsNumbers implements MenuInterface {
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input number");
        double findNumber = input.nextDouble();

        List<Calculate> allEquations = Dao.getAll();
        List<Calculate> containNumber = new ArrayList<>();

        if(allEquations==null) {
            return;
        }

        for(Calculate el : allEquations)
        {
            if(!(el.getRoots().getVariablesMap()==null))
            {
                for(Double number : el.getRoots().getVariablesMap().values())
                    if(findNumber == number && !containNumber.contains(el))
                        containNumber.add(el);
            }
        }

        if (!containNumber.isEmpty()) {
            System.out.printf("\n%-15s    %s\n", "Equation", "roots");
            for (Calculate el : containNumber)
                System.out.printf(el.toString());
        }
    }
}
