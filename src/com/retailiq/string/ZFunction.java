
package com.retailiq.string;

public class ZFunction {

    public static int[] calculateZ(String s) {

        int n = s.length();
        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1,
                                z[i - left]);
            }

            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    public static void main(String[] args) {

        String review = "goodgoodgood";

        int[] z = calculateZ(review);

        System.out.println("Review: " + review);
        System.out.println("Z-Array:");

        for (int value : z) {
            System.out.print(value + " ");
        }

        System.out.println();

        System.out.println("\nRepeated prefix matches:");

        for (int i = 1; i < z.length; i++) {
            if (z[i] > 0) {
                System.out.println(
                    "Index " + i +
                    " matches prefix for " + z[i] + " characters"
                );
            }
        }
    }
}
