package com.lahutina.commands;

import com.lahutina.menu.MenuInterface;

public class PrintEquations implements MenuInterface {
    @Override
    public void execute() {
        System.out.println("Inside PrintEquations");
    }
}
