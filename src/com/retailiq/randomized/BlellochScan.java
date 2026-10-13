package com.retailiq.randomized;

import java.util.Arrays;

public class BlellochScan {

    public static void blellochScan(int[] arr) {

        int n = arr.length;

        // Up-sweep phase
        for (int stride = 1; stride < n; stride *= 2) {

            for (int i = 0; i < n; i += 2 * stride) {

                int index = i + 2 * stride - 1;
                int left = i + stride - 1;

                if (index < n) {
                    arr[index] += arr[left];
                }
            }
        }

        // Set root to zero
        arr[n - 1] = 0;

        // Down-sweep phase
        for (int stride = n / 2; stride >= 1; stride /= 2) {

            for (int i = 0; i < n; i += 2 * stride) {

                int index = i + 2 * stride - 1;
                int left = i + stride - 1;

                if (index < n) {

                    int temp = arr[left];

                    arr[left] = arr[index];

                    arr[index] += temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println("Input: " + Arrays.toString(arr));

        blellochScan(arr);

        System.out.println("Exclusive Prefix Sum: " + Arrays.toString(arr));
    }
}