package com.lahutina.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public Scanner input = new Scanner(System.in);

    public void start()
    {
        Commands commands = new Commands();

        while(true){
            System.out.println("\nAll commands:");
            commands.printAll();

            System.out.println("Choose your option:");
            int choice = 0;
            try{
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                break;
            }

            if(!commands.execute(choice))
                return;
        }
    }
}