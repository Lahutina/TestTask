package com.lahutina.equation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that is responsible for calculation of
 * one part of equation
 */
public class Equation {
    // One part of equation to calculate
    private final String strExp;
    // String divided into elements(numbers, operands etc.)
    private ArrayList<String> splitExp;
    // Equation parsed to array according to Polish notation
    private ArrayList<String> parsedExp;
    // Possible values of unknown literal variables
    private final Variables variables;

    /**
     * Constructor that initializes variables and
     * one part of equation that needs to be calculated
     *
     * @param strExp    initial str of one part of equation
     * @param variables variables that can be present
     */
    public Equation(String strExp, Variables variables) {
        this.strExp = strExp;
        this.variables = variables;
    }

    /**
     * Checks if a symbol is an operand
     *
     * @param expChar Symbol to check
     * @return Whether a symbol is an operand
     */
    public static boolean isOperator(String expChar) {
        return expChar.equals("^") || expChar.equals("*") ||
                expChar.equals("/") || expChar.equals("+") || expChar.equals("-");
    }

    /**
     * Splits equation into elements(numbers, operands etc.),
     * parses to array according to Polish notation,
     * calculates equation and returns result
     *
     * @return a number that is the result of a calculation
     */
    public Double doOperations() {
        splitExp = SplitEquation.split(this.strExp);
        parsedExp = ParseEquation.parse(splitExp);
        return calculate();
    }

    /**
     * Evaluates the value of an
     * expression using reverse Polish notation
     *
     * @return The value of the expression or null if there are errors
     */
    private Double calculate() {

        Stack<Double> stack = new Stack<>();

        for (String expEl : parsedExp) {

            if (isOperator(expEl)) {
                calculateIfMeetOperator(stack, expEl);
            } else if (variables.contains(expEl)) {
                stack.push(variables.get(expEl));
            } else if (isStrDouble(expEl)) {
                stack.push(Double.parseDouble(expEl));
            } else {
                printErrorMsg(expEl, variables);
                throw new RuntimeException("There are mistakes in equation");
            }
        }
        return stack.pop();
    }

    /**
     * Pops two operands from the stack, performs
     * an operation on them, and puts the result on the stack.
     * Іf there is only one element in the stack, and the operator
     * is a unary plus or minus, then a unary operation is performed
     * on this operand
     *
     * @param stack    Stack of numbers
     * @param operator Action that must be performed on numbers
     */
    private void calculateIfMeetOperator(Stack<Double> stack, String operator) {
        double var1, var2;
        if (stack.size() >= 2) {
            var2 = stack.pop();
            var1 = stack.pop();
            stack.push(calculateOp(var1, var2, operator));
        } else if (stack.size() == 1 && operator.equals("-") || operator.equals("+")) {
            if (operator.equals("-")) {
                stack.push(stack.pop() * -1);
            }
        } else {
            System.out.println(strExp);
            System.out.println("Check the operators");
            System.exit(0);
        }
    }

    /**
     * Performs an operation on two variables
     * if division by 0 is performed, it throws an exception
     *
     * @param var1     Variable1
     * @param var2     Variable2
     * @param operator The operation to be performed
     * @return The result of the operation
     */
    private double calculateOp(double var1, double var2, String operator) {
        double res = 0.0;
        switch (operator) {
            case "^" -> res = Math.pow(var1, var2);
            case "+" -> res = var1 + var2;
            case "-" -> res = var1 - var2;
            case "*" -> res = var1 * var2;
            case "/" -> {
                if (var2 == 0.0) {
                    throw new ArithmeticException("Cannot be divided by 0");
                }
                res = var1 / var2;
            }
        }
        return res;
    }

    /**
     * Checks whether the operand
     * can be parsed into a double number
     *
     * @param operand operand to check
     * @return Whether the operand can be of type double
     */
    private boolean isStrDouble(String operand) {
        try {
            Double.parseDouble(operand);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Displays information about the parcel,
     * highlighting the error messages in red in the expression
     *
     * @param error     place with error
     * @param variables Variables
     */
    private void printErrorMsg(String error, Variables variables) {
        /* Red color code to highlight the wrong place in the formula */
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("An error in the expression:");
        for (String expEl : splitExp) {
            if (expEl.equals(error)) {
                System.out.print(ANSI_RED + expEl + ANSI_RESET);
            } else {
                System.out.print(expEl);
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return strExp;
    }
}
