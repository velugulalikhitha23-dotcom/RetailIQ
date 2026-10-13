
package com.retailiq.string;

public class RabinKarp {

    static final int PRIME = 101;

    public static void search(String text, String pattern) {

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            System.out.println("Invalid pattern length.");
            return;
        }

        int patternHash = 0;
        int textHash = 0;
        int h = 1;

        for (int i = 0; i < m - 1; i++) {
            h = (h * 256) % PRIME;
        }

        for (int i = 0; i < m; i++) {
            patternHash =
                (256 * patternHash + pattern.charAt(i)) % PRIME;

            textHash =
                (256 * textHash + text.charAt(i)) % PRIME;
        }

        boolean found = false;

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash &&
                text.regionMatches(i, pattern, 0, m)) {

                System.out.println(
                    "Product code found at index " + i
                );
                found = true;
            }

            if (i < n - m) {
                textHash = (256 * (textHash
                    - text.charAt(i) * h)
                    + text.charAt(i + m)) % PRIME;

                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }

        if (!found) {
            System.out.println("Product code not found.");
        }
    }

    public static void main(String[] args) {

        String productCodes =
            "PRD101 PRD102 PRD103 PRD102";

        String searchCode = "PRD102";

        System.out.println("Searching: " + searchCode);

        search(productCodes, searchCode);
    }
}
