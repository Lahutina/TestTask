package com.lahutina.equation;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that contains the name
 * of the variables and their value
 */
public class Variables {

    private final String roots;
    /** Key - variable name, value - its value */
    private final HashMap<String, Double> variables;

    /**
     * A constructor that initializes the HashMap and
     * parses the variables and puts them into a HashMap
     *
     * @param roots String that contain variables
     */
    public Variables(String roots) {
        this.roots = roots.replaceAll(" ", "");
        variables = new HashMap<>();
        if(!roots.equals(""))
            parseVariables(roots);
    }

    /**
     * Extracts variables from array "x=?, y=?"
     *
     * @param roots String that contain variables
     */
    private void parseVariables(String roots) {
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

    /**
     * Checks if this name of variable is in list
     *
     * @param varName variable to check if present
     * @return present or no
     */
    public boolean contains(String varName) { return variables.containsKey(varName); }

    @Override
    public String toString() {
        return roots;
    }
}
