package com.lahutina.commands;

import com.lahutina.equation.Calculate;
import com.lahutina.jdbc.Dao;
import com.lahutina.menu.MenuInterface;

import java.util.List;

/**
 * Class that reads all data prom DB and prints it
 */
public class PrintEquations implements MenuInterface {
    @Override
    public void execute() {
        List<Calculate> result = Dao.getAll();

        if (result != null && !result.isEmpty()) {
            System.out.printf("\n%-15s    %s\n", "Equation", "roots");
            for (Calculate el : result)
                System.out.printf("%-15s  |  %s\n", el.getStrExp(), el.getRoots());
        }
    }
}
