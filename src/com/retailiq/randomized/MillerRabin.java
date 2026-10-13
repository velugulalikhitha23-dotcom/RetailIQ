package com.retailiq.randomized;

import java.util.Random;

public class MillerRabin {

    static long powerMod(long a, long d, long n) {

        long result = 1;

        a = a % n;

        while (d > 0) {

            if ((d & 1) == 1) {
                result = (result * a) % n;
            }

            a = (a * a) % n;
            d = d / 2;
        }

        return result;
    }

    static boolean isPrime(long n, int iterations) {

        if (n < 2) {
            return false;
        }

        if (n == 2 || n == 3) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        long d = n - 1;

        while (d % 2 == 0) {
            d = d / 2;
        }

        Random random = new Random();

        for (int i = 0; i < iterations; i++) {

            long a = 2 + Math.abs(random.nextLong()) % (n - 3);

            long x = powerMod(a, d, n);

            if (x == 1 || x == n - 1) {
                continue;
            }

            boolean passed = false;

            for (long r = d; r < n - 1; r *= 2) {

                x = (x * x) % n;

                if (x == n - 1) {
                    passed = true;
                    break;
                }
            }

            if (!passed) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        long number = 97;

        boolean result = isPrime(number, 5);

        System.out.println("Number: " + number);
        System.out.println("Probably Prime: " + result);
    }
}