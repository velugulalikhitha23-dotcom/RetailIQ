
package com.retailiq.suffix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SAISDemo {

    private static int[] sais(int[] s, int upper) {
        int n = s.length;

        if (n == 0) {
            return new int[0];
        }
        if (n == 1) {
            return new int[]{0};
        }
        if (n == 2) {
            return s[0] < s[1]
                    ? new int[]{0, 1}
                    : new int[]{1, 0};
        }

        // Step 1: Classify suffix positions as S-type or L-type.
        boolean[] isS = new boolean[n];

        for (int i = n - 2; i >= 0; i--) {
            if (s[i] == s[i + 1]) {
                isS[i] = isS[i + 1];
            } else {
                isS[i] = s[i] < s[i + 1];
            }
        }

        // Step 2: Count each symbol.
        int[] sumL = new int[upper + 2];
        int[] sumS = new int[upper + 2];

        for (int i = 0; i < n; i++) {
            if (!isS[i]) {
                sumS[s[i]]++;
            } else {
                sumL[s[i] + 1]++;
            }
        }

        for (int i = 0; i <= upper; i++) {
            sumS[i] += sumL[i];
            sumL[i + 1] += sumS[i];
        }

        // Step 3: Induced sorting.
        int[] sa = new int[n];
        Arrays.fill(sa, -1);

        Runnable induce = () -> {
            Arrays.fill(sa, -1);

            int[] buf = sumS.clone();

            for (int d : lmsPositions(isS)) {
                if (d < n) {
                    sa[buf[s[d]]++] = d;
                }
            }

            buf = sumL.clone();

            sa[buf[s[n - 1]]++] = n - 1;

            for (int i = 0; i < n; i++) {
                int v = sa[i];
                if (v >= 1 && !isS[v - 1]) {
                    sa[buf[s[v - 1]]++] = v - 1;
                }
            }

            buf = sumL.clone();

            for (int i = n - 1; i >= 0; i--) {
                int v = sa[i];
                if (v >= 1 && isS[v - 1]) {
                    sa[--buf[s[v - 1] + 1]] = v - 1;
                }
            }
        };

        // Step 4: Identify LMS positions.
        List<Integer> lms = lmsPositions(isS);

        // First induced sorting pass.
        induce.run();

        if (!lms.isEmpty()) {
            int[] sortedLms = new int[lms.size()];
            int p = 0;

            for (int v : sa) {
                if (v >= 0 && isLms(v, isS)) {
                    sortedLms[p++] = v;
                }
            }

            // Step 5: Name LMS substrings.
            int[] recS = new int[lms.size()];
            int recUpper = 0;
            

            int[] lmsMap = new int[n];
            Arrays.fill(lmsMap, -1);

            for (int i = 0; i < lms.size(); i++) {
                lmsMap[lms.get(i)] = i;
            }

            int[] orderedNames = new int[lms.size()];
            orderedNames[lmsMap[sortedLms[0]]] = 0;

            for (int i = 1; i < sortedLms.length; i++) {
                int left = sortedLms[i - 1];
                int right = sortedLms[i];

                int leftEnd = left + 1;
                while (leftEnd < n && !isLms(leftEnd, isS)) {
                    leftEnd++;
                }

                int rightEnd = right + 1;
                while (rightEnd < n && !isLms(rightEnd, isS)) {
                    rightEnd++;
                }

                boolean same = (leftEnd - left) == (rightEnd - right);

                if (same) {
                    int a = left;
                    int b = right;

                    while (a < leftEnd && s[a] == s[b]) {
                        a++;
                        b++;
                    }

                    if (a == n || b == n || s[a] != s[b]) {
                        same = false;
                    }
                }

                if (!same) {
                    recUpper++;
                }

                orderedNames[lmsMap[right]] = recUpper;
            }

            int[] reduced = new int[lms.size()];
            for (int i = 0; i < lms.size(); i++) {
                reduced[i] = orderedNames[i];
            }

            // Step 6: Recursively sort the reduced problem.
            int[] recSA;

            if (recUpper + 1 == lms.size()) {
                recSA = new int[lms.size()];
                for (int i = 0; i < lms.size(); i++) {
                    recSA[reduced[i]] = i;
                }
            } else {
                recSA = sais(reduced, recUpper);
            }

            int[] sortedLmsFinal = new int[lms.size()];
            for (int i = 0; i < recSA.length; i++) {
                sortedLmsFinal[i] = lms.get(recSA[i]);
            }

            // Final induced sorting pass.
            Arrays.fill(sa, -1);
            int[] buf = sumS.clone();

            for (int d : sortedLmsFinal) {
                sa[buf[s[d]]++] = d;
            }

            buf = sumL.clone();
            sa[buf[s[n - 1]]++] = n - 1;

            for (int i = 0; i < n; i++) {
                int v = sa[i];
                if (v >= 1 && !isS[v - 1]) {
                    sa[buf[s[v - 1]]++] = v - 1;
                }
            }

            buf = sumL.clone();
            for (int i = n - 1; i >= 0; i--) {
                int v = sa[i];
                if (v >= 1 && isS[v - 1]) {
                    sa[--buf[s[v - 1] + 1]] = v - 1;
                }
            }
        }

        return sa;
    }

    private static List<Integer> lmsPositions(boolean[] isS) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 1; i < isS.length; i++) {
            if (isLms(i, isS)) {
                positions.add(i);
            }
        }

        return positions;
    }

    private static boolean isLms(int i, boolean[] isS) {
        return i > 0 && isS[i] && !isS[i - 1];
    }

    public static int[] buildSuffixArray(String text) {
        // Convert characters to positive integers and add a unique sentinel 0.
        int n = text.length();
        int[] s = new int[n + 1];

        for (int i = 0; i < n; i++) {
            s[i] = text.charAt(i) + 1;
        }

        s[n] = 0;

        int[] fullSA = sais(s, 65536);
        int[] result = new int[n];
        int p = 0;

        // Remove the sentinel suffix from the result.
        for (int index : fullSA) {
            if (index != n) {
                result[p++] = index;
            }
        }

        return result;
    }

    public static void main(String[] args) {
    	String text = "aaaa";

        int[] suffixArray = buildSuffixArray(text);

        System.out.println("Input: " + text);
        System.out.println("Suffix Array: " + Arrays.toString(suffixArray));

        System.out.println("Sorted suffixes:");
        for (int index : suffixArray) {
            System.out.println(index + " -> " + text.substring(index));
        }
    }
}
