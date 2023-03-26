package com.lahutina.equation;

import com.lahutina.actions.priority.ActionPriority;

import java.util.ArrayList;
import java.util.Stack;

public class ParseEquation {

    /**
     * Parse the expression into
     * Reverse Polish notation format
     */
    public static ArrayList<String> parse(ArrayList<String> splitExp) {

        if (!checkParentheses(splitExp)) {
            System.out.println("Check the parentheses in the expression");
            System.exit(0);
        }

        ArrayList<String> parsedExp = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        for (String expEl : splitExp) {
            if (Equation.isOperator(expEl)) {
                parseIfMeetOperator(expEl, stack, parsedExp);
            } else if (expEl.equals("(")) {
                stack.push(expEl);
            } else if (expEl.equals(")")) {
                fromStackToListUntilParenthesis(stack, parsedExp);
            } else {
                parsedExp.add(expEl);
            }
        }
        fromStackToList(stack, parsedExp);

        return parsedExp;
    }


    /**
     * Checks whether the parentheses in the expression are
     * correctly placed.
     * If the parentheses are placed incorrectly in
     * the expression, the program terminates
     */
    private static boolean checkParentheses(ArrayList<String> splitExp) {
        int parenthesesCounter = 0;

        for (String expChar : splitExp) {
            if (expChar.equals("(")) {
                parenthesesCounter++;
            } else if (expChar.equals(")")) {
                parenthesesCounter--;
            }
            if (parenthesesCounter < 0) {
                return false;
            }
        }
        return parenthesesCounter == 0;
    }

    /**
     * From the stack to the list transfers operators that have a
     * higher priority and puts the parameter operator on the stack
     *
     * @param operator  Operator who met
     * @param stack     Stack of previous operators
     * @param parsedExp All previous elements(operands, numbers)
     */
    private static void parseIfMeetOperator(String operator, Stack<String> stack, ArrayList<String> parsedExp) {
        String lastStackEl;

        if (!stack.empty()) {
            lastStackEl = stack.lastElement();

            while (!stack.isEmpty() && hasHigherPriority(lastStackEl, operator)) {
                parsedExp.add(stack.pop());
                if (!stack.isEmpty()) {
                    lastStackEl = stack.lastElement();
                }
            }
        }
        stack.push(operator);
    }

    /**
     * Checks whether operator1 has a
     * higher priority than operator2
     *
     * @param operator1 Operator 1
     * @param operator2 Operator 2
     * @return Whether operator 1 has higher priority
     */
    private static boolean hasHigherPriority(String operator1, String operator2) {
        return ActionPriority.contains(operator1) &&
                ActionPriority.getPriority(operator1) >= ActionPriority.getPriority(operator2);
    }

    /**
     * Moves all elements from
     * the stack to the list
     *
     * @param stack     Stack with elements(operands)
     * @param parsedExp All previous elements(operands, numbers)
     */
    private static void fromStackToList(Stack<String> stack, ArrayList<String> parsedExp) {
        while (!stack.empty()) {
            parsedExp.add(stack.pop());
        }
    }

    /**
     * Pops everything from the stack into the
     * list until a closing brace is encountered
     *
     * @param stack     The stack from which elements are transferred
     * @param parsedExp All previous elements(operands, numbers)
     */
    private static void fromStackToListUntilParenthesis(Stack<String> stack, ArrayList<String> parsedExp) {
        String lastStackEl;
        while (!(lastStackEl = stack.pop()).equals("(")) {
            parsedExp.add(lastStackEl);
        }
    }
}
