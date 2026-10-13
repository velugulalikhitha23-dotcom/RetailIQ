
package com.retailiq.suffix;

public class LCPKasaiDemo {

    public static int[] buildLCP(String text, int[] suffixArray) {
        int n = text.length();

        int[] rank = new int[n];
        int[] lcp = new int[n];

        // Suffix rank array create cheyyadam
        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int common = 0;

        // Kasai's Algorithm
        for (int i = 0; i < n; i++) {
            if (rank[i] == 0) {
                common = 0;
                continue;
            }

            int j = suffixArray[rank[i] - 1];

            while (i + common < n
                    && j + common < n
                    && text.charAt(i + common)
                       == text.charAt(j + common)) {
                common++;
            }

            lcp[rank[i]] = common;

            if (common > 0) {
                common--;
            }
        }

        return lcp;
    }

    public static void main(String[] args) {
        String text = "banana";

        // SuffixArrayDemo class nunchi suffix array
        int[] suffixArray =
                SuffixArrayDemo.buildSuffixArray(text);

        int[] lcp = buildLCP(text, suffixArray);

        System.out.println("Text: " + text);
        System.out.println("\nSuffix Array and LCP:");

        for (int i = 0; i < suffixArray.length; i++) {
            System.out.println(
                "Suffix: " + text.substring(suffixArray[i])
                + " | Index: " + suffixArray[i]
                + " | LCP: " + lcp[i]
            );
        }
    }
}
