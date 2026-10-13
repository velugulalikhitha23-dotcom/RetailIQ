
package com.retailiq.suffix;

import java.util.Arrays;
import java.util.Comparator;

public class SuffixArrayDemo {

    public static int[] buildSuffixArray(String text) {

        int n = text.length();
        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return text.substring(a)
                           .compareTo(text.substring(b));
            }
        });

        int[] suffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i];
        }

        return suffixArray;
    }

    public static void main(String[] args) {

        String description = "banana";

        int[] suffixArray = buildSuffixArray(description);

        System.out.println("Product Description: " + description);
        System.out.println("\nSuffix Array:");

        for (int index : suffixArray) {
            System.out.println(
                "Index " + index + " -> " + description.substring(index)
            );
        }
    }
}
