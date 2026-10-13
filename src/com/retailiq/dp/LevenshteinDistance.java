
package com.retailiq.dp;

public class LevenshteinDistance {

    public static int calculateDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // DP table
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: convert to/from an empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                            insert,
                            Math.min(delete, replace)
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "cat";
        String s2 = "cut";

        int distance = calculateDistance(s1, s2);

        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        System.out.println("Levenshtein Distance: " + distance);
    }
}
