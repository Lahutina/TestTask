package com.lahutina.equation;

/**
 * Priority of operators
 */
public enum ActionPriority {
    POWER("^", 3),
    MULTIPLICATION("*", 2),
    DIVISION("/", 2),
    PLUS("+", 1),
    MINUS("-", 1);

    /** Notation of the operator */
    private final String name;
    /** Operator priority */
    private final int priority;

    ActionPriority(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * Looks for operator priority
     *
     * @param actionName Operator name
     * @return Operator priority or -1 if the operator with that name does not exist
     */
    public static int getPriority(String actionName) {
        for (ActionPriority action: ActionPriority.values()) {
            if (action.name.equals(actionName)) {
                return action.priority;
            }
        }
        return -1;
    }

    /**
     * Checks whether ENUM contains a statement with that name
     *
     * @param actionName Operator name
     * @return Whether there is an operator with that name
     */
    public static boolean contains(String actionName) {
        for (ActionPriority action: ActionPriority.values()) {
            if (action.name.equals(actionName)) {
                return true;
            }
        }
        return false;
    }
}
