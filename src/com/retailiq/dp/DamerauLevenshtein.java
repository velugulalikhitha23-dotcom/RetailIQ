
package com.retailiq.dp;

public class DamerauLevenshtein {

    public static int calculateDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1))
                        ? 0 : 1;

                int insert = dp[i][j - 1] + 1;
                int delete = dp[i - 1][j] + 1;
                int replace = dp[i - 1][j - 1] + cost;

                dp[i][j] = Math.min(
                        insert,
                        Math.min(delete, replace)
                );

                // Adjacent character transposition
                if (i > 1 && j > 1
                        && s1.charAt(i - 1) == s2.charAt(j - 2)
                        && s1.charAt(i - 2) == s2.charAt(j - 1)) {

                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i - 2][j - 2] + 1
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ba";

        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        System.out.println("Damerau-Levenshtein Distance: "
                + calculateDistance(s1, s2));
    }
}
