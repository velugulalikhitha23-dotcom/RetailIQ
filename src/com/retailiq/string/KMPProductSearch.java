
package com.retailiq.string;

public class KMPProductSearch {

    // Build LPS Array
    static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i++] = 0;
                }
            }
        }
        return lps;
    }

    // KMP Search Algorithm
    public static boolean search(String text, String pattern) {

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        if (pattern.isEmpty()) return true;

        int[] lps = computeLPS(pattern);

        int i = 0, j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                return true;
            }

            if (i < text.length() &&
                text.charAt(i) != pattern.charAt(j)) {

                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return false;
    }

    // Product Search Demo
    public static void displayProducts(String query) {

        String[] products = {
            "Smartphone",
            "iPhone 15",
            "Samsung Galaxy",
            "Bluetooth Headphones",
            "Laptop",
            "Smart Watch",
            "Phone Charger",
            "Wireless Mouse"
        };

        System.out.println("\nMatching Products:");

        boolean found = false;

        for (String product : products) {
            if (search(product, query)) {
                System.out.println(" - " + product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching products found.");
        }
    }
}
