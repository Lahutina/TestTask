package com.lahutina.commands;

import com.lahutina.equation.Calculate;
import com.lahutina.jdbc.Dao;
import com.lahutina.menu.MenuInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads all data from database,
 * and prints equations where only one root
 */
public class FindWithOneRoot implements MenuInterface {
    @Override
    public void execute() {
        List<Calculate> allEquations = Dao.getAll();

        if (allEquations == null)
            return;

        List<Calculate> oneRoot = new ArrayList<>();
        for (Calculate el : allEquations) {
            if (el.getRoots().getVariablesMap() != null &&
                    el.getRoots().getVariablesMap().values().size() == 1) {
                oneRoot.add(el);
            }
        }

        if (!oneRoot.isEmpty()) {
            System.out.printf("\n%-15s    %s\n", "Equation", "roots");
            for (Calculate el : oneRoot)
                System.out.printf(el.toString());
        }
    }
}
