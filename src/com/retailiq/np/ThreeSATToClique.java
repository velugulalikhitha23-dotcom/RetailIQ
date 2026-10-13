package com.retailiq.np;

import java.util.ArrayList;
import java.util.List;

public class ThreeSATToClique {

    static class Vertex {
        int clause;
        int literal;

        Vertex(int clause, int literal) {
            this.clause = clause;
            this.literal = literal;
        }

        @Override
        public String toString() {
            return "C" + (clause + 1) + ":" + literalName(literal);
        }

        private String literalName(int literal) {
            int variable = Math.abs(literal);

            if (literal > 0) {
                return "x" + variable;
            } else {
                return "~x" + variable;
            }
        }
    }

    public static List<Vertex> createVertices(int[][] formula) {

        List<Vertex> vertices = new ArrayList<>();

        for (int clause = 0; clause < formula.length; clause++) {

            for (int literal : formula[clause]) {
                vertices.add(new Vertex(clause, literal));
            }
        }

        return vertices;
    }

    private static boolean compatible(Vertex a, Vertex b) {

        // Vertices must come from different clauses
        if (a.clause == b.clause) {
            return false;
        }

        // x and ~x cannot be selected together
        if (a.literal == -b.literal) {
            return false;
        }

        return true;
    }

    public static int countEdges(List<Vertex> vertices) {

        int edges = 0;

        for (int i = 0; i < vertices.size(); i++) {

            for (int j = i + 1; j < vertices.size(); j++) {

                if (compatible(vertices.get(i), vertices.get(j))) {
                    edges++;
                }
            }
        }

        return edges;
    }

    public static boolean hasKSizeClique(
            List<Vertex> vertices,
            int k) {

        return findClique(
                vertices,
                new ArrayList<>(),
                0,
                k
        );
    }

    private static boolean findClique(
            List<Vertex> vertices,
            List<Vertex> currentClique,
            int start,
            int k) {

        if (currentClique.size() == k) {
            return true;
        }

        for (int i = start; i < vertices.size(); i++) {

            Vertex candidate = vertices.get(i);

            boolean compatibleWithAll = true;

            for (Vertex selected : currentClique) {

                if (!compatible(candidate, selected)) {
                    compatibleWithAll = false;
                    break;
                }
            }

            if (compatibleWithAll) {

                currentClique.add(candidate);

                if (findClique(
                        vertices,
                        currentClique,
                        i + 1,
                        k)) {

                    return true;
                }

                currentClique.remove(currentClique.size() - 1);
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // 3-SAT formula:
        //
        // (x1 OR x2 OR ~x3)
        // AND
        // (~x1 OR x2 OR x3)
        // AND
        // (x1 OR ~x2 OR x3)

        int[][] formula = {
            {1, 2, -3},
            {-1, 2, 3},
            {1, -2, 3}
        };

        List<Vertex> vertices = createVertices(formula);

        int edges = countEdges(vertices);

        int k = formula.length;

        boolean cliqueExists =
                hasKSizeClique(vertices, k);

        System.out.println(
                "Number of Vertices: " + vertices.size()
        );

        System.out.println(
                "Number of Edges: " + edges
        );

        System.out.println(
                "Target Clique Size: " + k
        );

        System.out.println(
                "Clique of size " + k +
                " exists: " + cliqueExists
        );
    }
}