package com.retailiq.np;

import java.util.ArrayList;
import java.util.List;

public class VertexCoverApproximation {

    static class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static List<Integer> approximateVertexCover(
            int vertices,
            List<Edge> edges) {

        boolean[] covered = new boolean[vertices];

        List<Integer> vertexCover = new ArrayList<>();

        for (Edge edge : edges) {

            if (!covered[edge.u] && !covered[edge.v]) {

                covered[edge.u] = true;
                covered[edge.v] = true;

                vertexCover.add(edge.u);
                vertexCover.add(edge.v);
            }
        }

        return vertexCover;
    }

    public static void main(String[] args) {

        int vertices = 6;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(2, 4));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));

        List<Integer> cover =
                approximateVertexCover(vertices, edges);

        System.out.println(
                "Approximate Vertex Cover: " + cover
        );

        System.out.println(
                "Cover Size: " + cover.size()
        );

        System.out.println(
                "Approximation Ratio: 2"
        );
    }
}