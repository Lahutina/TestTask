package com.lahutina.menu;

import com.lahutina.commands.AddEquation;
import com.lahutina.commands.FindByRootsNumbers;
import com.lahutina.commands.FindWithOneRoot;
import com.lahutina.commands.PrintEquations;

import java.util.LinkedHashMap;
import java.util.Map;

public class Commands {
    private final Map<String, MenuInterface> menuItems;

    public Commands() {
        menuItems = new LinkedHashMap<>();
        menuItems.put("Add equation", new AddEquation());
        menuItems.put("Print all equations", new PrintEquations());
        menuItems.put("Find equation with specific roots", new FindByRootsNumbers());
        menuItems.put("Find equation with one root", new FindWithOneRoot());
        menuItems.put("Exit program", null);
    }

    public void printAll() {
        int i = 1;
        for (String item : menuItems.keySet()) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    public boolean execute(int choice) {
        int i = 1;
        for (Map.Entry<String, MenuInterface> entry : menuItems.entrySet()) {
            if (choice == i) {
                entry.getValue().execute();
                return true;
            } else if (choice == 5)
                return false;
            i++;
        }
        System.out.println("Incorrect choice, try again");
        return true;
    }
}
