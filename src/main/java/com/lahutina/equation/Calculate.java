package com.lahutina.equation;

import java.util.Objects;

/**
 * Class that does all calculations
 * divides equation into two parts,
 * calculates each part and compares
 */
public class Calculate {

    /** Initial equation*/
    private String strExp;
    /** Possible values of unknown literal variables*/
    private final Variables variables;
    /** Initial equation divided into two strings after "=" and before */
    private String[] twoPartsExp = new String[2];
    /** Results of two parts*/
    private final Double[] twoPartsRes = new Double[2];

    /**
     * Constructor that assigns initial equation str
     * and possible values of variables
     *
     * @param strExp initial string equation
     * @param variables possible values of letters in equation
     */
    public Calculate(String strExp,  String variables) {
        this.strExp = strExp;
        this.variables = new Variables(variables);
    }

    /**
     * Removes all white spaces
     * splits one equation to two: after and before "="
     * calculates values of two parts
     * and compares the results
     */
    public void doCalculate() {
        this.strExp = strExp.replaceAll(" ", "");
        twoPartsExp = strExp.split("=");

        Equation firstPart = new Equation(twoPartsExp[0], variables);
        twoPartsRes[0] = firstPart.doOperations();

        Equation secondPart = new Equation(twoPartsExp[1], variables);
        twoPartsRes[1] = secondPart.doOperations();

        if(Objects.equals(twoPartsRes[0], twoPartsRes[1]))
        {
            // TODO
            System.out.println("Here I should save equation and roots to DB " +
                    "or if variables==null only equation");
        }
        else {
            // TODO
            System.out.println("Here if there are no variables do not save equation " +
                    "if there are variables just save equation because variables not correct");

            System.out.println("\nTwo parts are not equal: " + twoPartsRes[0] + " and " + twoPartsRes[1]);
        }
    }
}
