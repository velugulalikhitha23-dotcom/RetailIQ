package com.retailiq.flow;

import java.util.Arrays;

public class BipartiteMatching {

    private static boolean findMatch(
            int employee,
            boolean[][] graph,
            boolean[] visited,
            int[] assignedTask) {

        for (int task = 0; task < graph[employee].length; task++) {

            if (graph[employee][task] && !visited[task]) {

                visited[task] = true;

                if (assignedTask[task] == -1 ||
                    findMatch(assignedTask[task], graph, visited, assignedTask)) {

                    assignedTask[task] = employee;
                    return true;
                }
            }
        }

        return false;
    }

    public static int maximumMatching(boolean[][] graph) {

        int employees = graph.length;
        int tasks = graph[0].length;

        int[] assignedTask = new int[tasks];
        Arrays.fill(assignedTask, -1);

        int matching = 0;

        for (int employee = 0; employee < employees; employee++) {

            boolean[] visited = new boolean[tasks];

            if (findMatch(employee, graph, visited, assignedTask)) {
                matching++;
            }
        }

        return matching;
    }

    public static void main(String[] args) {

        boolean[][] graph = {
            {true, true, false, false},
            {true, false, true, false},
            {false, true, false, true},
            {false, false, true, true}
        };

        int result = maximumMatching(graph);

        System.out.println("Maximum Bipartite Matching: " + result);
    }
}