package com.lahutina.equation;

import java.util.Objects;

public class Calculate {

    private String strExp;
    private String[] twoPartsExp = new String[2];

    private final Double[] twoPartsRes = new Double[2];
    private final Variables variables;


    public Calculate(String strExp,  String variables) {
        this.strExp = strExp;
        this.variables = new Variables(variables);
    }

    public void doCalculate() {
        this.strExp = strExp.replaceAll(" ", "");
        twoPartsExp = strExp.split("=");

        Equation firstPart = new Equation(twoPartsExp[0], variables);
        twoPartsRes[0] = firstPart.doOperations();

        Equation secondPart = new Equation(twoPartsExp[1], variables);
        twoPartsRes[1] = secondPart.doOperations();

        if(Objects.equals(twoPartsRes[0], twoPartsRes[1]))
            System.out.println("Congruts! Two parts are equal");
        else System.out.println("Two parts are not equal: " + twoPartsRes[0] + " and " + twoPartsRes[1]);
    }
}
