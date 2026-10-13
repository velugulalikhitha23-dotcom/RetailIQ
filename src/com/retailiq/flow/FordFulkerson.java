package com.retailiq.flow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    private static boolean bfs(int[][] residualGraph, int source, int sink, int[] parent) {

        int n = residualGraph.length;
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int next = 0; next < n; next++) {

                if (!visited[next] && residualGraph[current][next] > 0) {

                    queue.add(next);
                    parent[next] = current;
                    visited[next] = true;
                }
            }
        }

        return visited[sink];
    }

    public static int maxFlow(int[][] graph, int source, int sink) {

        int n = graph.length;

        int[][] residualGraph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }

        int[] parent = new int[n];

        int maxFlow = 0;

        while (bfs(residualGraph, source, sink, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {

                int u = parent[v];

                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {

                int u = parent[v];

                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
        };

        int source = 0;
        int sink = 5;

        int result = maxFlow(graph, source, sink);

        System.out.println("Maximum Flow: " + result);
    }
}