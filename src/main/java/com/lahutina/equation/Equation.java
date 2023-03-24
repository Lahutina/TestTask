package com.lahutina.equation;

import java.util.ArrayList;
import java.util.Stack;

public class Equation {
    String equation;
    ArrayList<String> roots;

    private Equation() {
    }

    public Equation(String equation) {
        this.equation = parse(equation);
        if(checkSymbols(equation) && checkParentheses(equation))
        {
            //save to bd
        }
        else System.err.println("Дужки розставленні не правильно");
    }

    private String parse(String equation) {
       return equation.replaceAll("\\s+", "");
    }

    public static boolean checkParentheses(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if ( stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean checkSymbols(String equation) {
        String regex = "^[\\d\\s+\\-,*/()%=a-zA-Z]*$";
        return equation.matches(regex);
    }
}
