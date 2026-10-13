package com.retailiq.np;

public class SAT3SAT {

    // Checks whether a 3-SAT formula is satisfied
    private static boolean isClauseSatisfied(
            int[] clause,
            boolean[] assignment) {

        for (int literal : clause) {

            int variable = Math.abs(literal) - 1;

            if (literal > 0 && assignment[variable]) {
                return true;
            }

            if (literal < 0 && !assignment[variable]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSatisfiable(
            int[][] formula,
            int numberOfVariables) {

        int totalAssignments = 1 << numberOfVariables;

        for (int mask = 0; mask < totalAssignments; mask++) {

            boolean[] assignment = new boolean[numberOfVariables];

            for (int i = 0; i < numberOfVariables; i++) {
                assignment[i] = (mask & (1 << i)) != 0;
            }

            boolean allClausesSatisfied = true;

            for (int[] clause : formula) {

                if (!isClauseSatisfied(clause, assignment)) {
                    allClausesSatisfied = false;
                    break;
                }
            }

            if (allClausesSatisfied) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // Formula:
        // (x1 OR x2 OR NOT x3)
        // AND
        // (NOT x1 OR x2 OR x3)
        // AND
        // (x1 OR NOT x2 OR x3)

        int[][] formula = {
            {1, 2, -3},
            {-1, 2, 3},
            {1, -2, 3}
        };

        int numberOfVariables = 3;

        boolean result = isSatisfiable(
            formula,
            numberOfVariables
        );

        System.out.println("3-SAT Formula Satisfiable: " + result);
    }
}