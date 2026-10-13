package com.retailiq.randomized;

import java.util.Arrays;

public class ParallelReduce {

    public static int parallelReduce(int[] arr) {

        int[] temp = Arrays.copyOf(arr, arr.length);

        int n = temp.length;

        while (n > 1) {

            int newSize = (n + 1) / 2;
            int[] next = new int[newSize];

            for (int i = 0; i < n / 2; i++) {
                next[i] = temp[2 * i] + temp[2 * i + 1];
            }

            if (n % 2 == 1) {
                next[newSize - 1] = temp[n - 1];
            }

            temp = next;
            n = newSize;
        }

        return temp[0];
    }

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50};

        int result = parallelReduce(arr);

        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Parallel Reduction Sum: " + result);
    }
}