package com.retailiq.randomized;

import java.util.Arrays;
import java.util.Random;

public class RandomizedQuickSort {

    private static final Random random = new Random();

    public static void randomizedQuickSort(int[] arr, int low, int high) {

        if (low < high) {

            int pivotIndex = low + random.nextInt(high - low + 1);

            swap(arr, pivotIndex, high);

            int partitionIndex = partition(arr, low, high);

            randomizedQuickSort(arr, low, partitionIndex - 1);
            randomizedQuickSort(arr, partitionIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] <= pivot) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        randomizedQuickSort(arr, 0, arr.length - 1);

        System.out.println("After Randomized QuickSort: " + Arrays.toString(arr));
    }
}