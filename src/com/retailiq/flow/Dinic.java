package com.retailiq.flow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dinic {

    static class Edge {
        int to;
        int capacity;
        int reverse;

        Edge(int to, int capacity, int reverse) {
            this.to = to;
            this.capacity = capacity;
            this.reverse = reverse;
        }
    }

    private final int n;
    private final LinkedList<Edge>[] graph;
    private int[] level;
    private int[] pointer;

    @SuppressWarnings("unchecked")
    public Dinic(int n) {
        this.n = n;
        graph = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        level = new int[n];
        pointer = new int[n];
    }

    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, capacity, graph[to].size());
        Edge backward = new Edge(from, 0, graph[from].size());

        graph[from].add(forward);
        graph[to].add(backward);
    }

    private boolean bfs(int source, int sink) {

        Arrays.fill(level, -1);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        level[source] = 0;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (Edge edge : graph[current]) {

                if (edge.capacity > 0 && level[edge.to] == -1) {

                    level[edge.to] = level[current] + 1;
                    queue.add(edge.to);
                }
            }
        }

        return level[sink] != -1;
    }

    private int dfs(int current, int sink, int flow) {

        if (current == sink) {
            return flow;
        }

        for (int i = pointer[current]; i < graph[current].size(); i++, pointer[current]++) {

            Edge edge = graph[current].get(i);

            if (edge.capacity > 0 &&
                level[edge.to] == level[current] + 1) {

                int pushed = dfs(
                    edge.to,
                    sink,
                    Math.min(flow, edge.capacity)
                );

                if (pushed > 0) {

                    edge.capacity -= pushed;
                    graph[edge.to].get(edge.reverse).capacity += pushed;

                    return pushed;
                }
            }
        }

        return 0;
    }

    public int maxFlow(int source, int sink) {

        int totalFlow = 0;

        while (bfs(source, sink)) {

            Arrays.fill(pointer, 0);

            int flow;

            while ((flow = dfs(source, sink, Integer.MAX_VALUE)) > 0) {
                totalFlow += flow;
            }
        }

        return totalFlow;
    }

    public static void main(String[] args) {

        Dinic dinic = new Dinic(6);

        dinic.addEdge(0, 1, 16);
        dinic.addEdge(0, 2, 13);
        dinic.addEdge(1, 2, 10);
        dinic.addEdge(1, 3, 12);
        dinic.addEdge(2, 1, 4);
        dinic.addEdge(2, 4, 14);
        dinic.addEdge(3, 2, 9);
        dinic.addEdge(3, 5, 20);
        dinic.addEdge(4, 3, 7);
        dinic.addEdge(4, 5, 4);

        int source = 0;
        int sink = 5;

        int result = dinic.maxFlow(source, sink);

        System.out.println("Maximum Flow using Dinic: " + result);
    }
}