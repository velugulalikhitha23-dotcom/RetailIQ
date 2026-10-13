package com.retailiq.np;

import java.util.ArrayList;
import java.util.List;

public class CliqueIndependentVertexCover {

    static class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    // Complement graph:
    // An edge exists in complement if it does not exist
    // in the original graph.
    public static boolean[][] complementGraph(boolean[][] graph) {

        int n = graph.length;
        boolean[][] complement = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i != j && !graph[i][j]) {
                    complement[i][j] = true;
                }
            }
        }

        return complement;
    }

    // Checks whether selected vertices form an independent set.
    public static boolean isIndependentSet(
            boolean[][] graph,
            List<Integer> vertices) {

        for (int i = 0; i < vertices.size(); i++) {

            for (int j = i + 1; j < vertices.size(); j++) {

                int u = vertices.get(i);
                int v = vertices.get(j);

                if (graph[u][v]) {
                    return false;
                }
            }
        }

        return true;
    }

    // A set of vertices is a vertex cover if every edge
    // has at least one endpoint inside the set.
    public static boolean isVertexCover(
            boolean[][] graph,
            List<Integer> cover) {

        int n = graph.length;

        boolean[] selected = new boolean[n];

        for (int vertex : cover) {
            selected[vertex] = true;
        }

        for (int u = 0; u < n; u++) {

            for (int v = u + 1; v < n; v++) {

                if (graph[u][v]) {

                    if (!selected[u] && !selected[v]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // Original graph:
        //
        // 0 ----- 1
        // |       |
        // |       |
        // 2 ----- 3
        //
        // Clique {0,1,2} is not used here.
        // We demonstrate the relationship between
        // independent set and vertex cover.

        boolean[][] graph = {
            {false, true,  true,  false},
            {true,  false, false, true},
            {true,  false, false, true},
            {false, true,  true,  false}
        };

        int n = graph.length;

        // Example independent set
        List<Integer> independentSet = new ArrayList<>();

        independentSet.add(0);
        independentSet.add(3);

        // Complement relationship
        boolean[][] complement = complementGraph(graph);

        boolean independent =
                isIndependentSet(graph, independentSet);

        System.out.println(
                "Independent Set {0, 3}: " + independent
        );

        System.out.println(
                "Complement Graph Created: true"
        );

        // Vertex cover is the complement of the
        // independent set.
        List<Integer> vertexCover = new ArrayList<>();

        for (int vertex = 0; vertex < n; vertex++) {

            if (!independentSet.contains(vertex)) {
                vertexCover.add(vertex);
            }
        }

        boolean cover =
                isVertexCover(graph, vertexCover);

        System.out.println(
                "Vertex Cover {1, 2}: " + cover
        );

        System.out.println(
                "Independent Set Size: "
                + independentSet.size()
        );

        System.out.println(
                "Vertex Cover Size: "
                + vertexCover.size()
        );
    }
}