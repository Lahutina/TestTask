package com.lahutina.equation;

import java.util.ArrayList;

public class SplitEquation {

    /**
     * Breaks an expression into
     * parentheses, operands, and operators
     *
     * @return Array of elements of expression
     */
    public static ArrayList<String> split(String strExp) {
        ArrayList<String> splitExp = new ArrayList<>();
        StringBuilder operand = new StringBuilder();

        String currChar, prevChar;

        if (strExp.charAt(0) == '(')
            splitExp.add("(");
        else
            operand.append(strExp.charAt(0));

        for (int i = 1; i < strExp.length(); i++) {
            currChar = String.valueOf(strExp.charAt(i));
            prevChar = String.valueOf(strExp.charAt(i - 1));

            /* if the current symbol is unary operator,
             * and before this symbol not parenthesis or other symbol,
             * then the formed operand is added to the
             * list, and current operator/parenthesis is entered in the list
             */
            if (isUnaryOperator(currChar, prevChar) || isParenthesis(currChar)) {
                addOperandToList(operand, splitExp);
                splitExp.add(currChar);
            } else {
                operand.append(currChar);
            }
        }
        addOperandToList(operand, splitExp);
        return splitExp;
    }

    /**
     * Checks if symbol is simple unary operator
     * and before not parenthesis or other operator
     *
     * @param currChar The current character of the expression
     * @param prevChar The previous character of the expression
     * @return Whether the current character is a unary operator
     */
    private static boolean isUnaryOperator(String currChar, String prevChar) {
        return Equation.isOperator(currChar) && !Equation.isOperator(prevChar) && !prevChar.equals("(");
    }

    /**
     * Adds the operand to the list and
     * the variable that forms the operand
     *
     * @param operand Operand to add
     */
    private static void addOperandToList(StringBuilder operand, ArrayList<String> splitExp) {
        if (operand.length() != 0) {
            splitExp.add(String.valueOf(operand));
            operand.setLength(0);
        }
    }

    /**
     * Checks if the symbol is a parenthesis
     *
     * @param expChar Symbol to check
     * @return Whether a symbol is a parenthesis
     */
    private static boolean isParenthesis(String expChar) {
        return expChar.equals("(") || expChar.equals(")");
    }


}
