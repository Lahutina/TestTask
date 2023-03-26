package com.lahutina.equation;

import java.util.HashMap;

/**
 * A class that contains the name
 * of the variables and their value
 */
public class Variables {
    /** Key - variable name, value - its value */
    private final HashMap<String, Double> variables;

    /**
     * A constructor that initializes the HashMap and
     * parses the variables and puts them into a HashMap
     *
     * @param roots String that contain variables
     */
    public Variables(String roots) {
        variables = new HashMap<>();
        parseVariables(roots);
    }

    /**
     * Extracts variables from array "x=?, y=?"
     *
     * @param roots String that contain variables
     */
    private void parseVariables(String roots) {
        roots = roots.replaceAll(" ", "");
        String[] arrRoots= roots.split(",");

        for (String arrRoot : arrRoots) {
            String[] tmp = arrRoot.split("=");
            variables.put(tmp[0], Double.parseDouble(tmp[1]));
        }
    }

    /**
     * Gives the value of a variable
     *
     * @param varName Variable name
     * @return  The value of a variable
     */
    public Double get(String varName) {
        return variables.get(varName);
    }

    public boolean contains(String varName) { return variables.containsKey(varName); }

    /**
     * Displays the names of all variables and their values
     */
    public void printVariables() {
        for (String varName : variables.keySet()) {
            System.out.println(varName + " = " + variables.get(varName));
        }
    }
}
