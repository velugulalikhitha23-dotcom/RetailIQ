
package com.retailiq.dp;

import java.util.Arrays;

public class BitmaskDP {

    private static final int INF = 1_000_000_000;

    public static int minimumCost(int[][] cost) {
        int n = cost.length;

        int[] dp = new int[1 << n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            int employee = Integer.bitCount(mask);

            if (employee >= n || dp[mask] == INF) {
                continue;
            }

            for (int task = 0; task < n; task++) {
                if ((mask & (1 << task)) == 0) {
                    int nextMask = mask | (1 << task);

                    dp[nextMask] = Math.min(
                        dp[nextMask],
                        dp[mask] + cost[employee][task]
                    );
                }
            }
        }

        return dp[(1 << n) - 1];
    }

    public static void main(String[] args) {
        int[][] cost = {
            {9, 2, 7},
            {6, 4, 3},
            {5, 8, 1}
        };

        System.out.println(
            "Minimum Assignment Cost: " + minimumCost(cost)
        );
    }
}
