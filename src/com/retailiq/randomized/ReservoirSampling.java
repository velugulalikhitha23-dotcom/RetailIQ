package com.retailiq.randomized;

import java.util.Random;

public class ReservoirSampling {

    public static int[] reservoirSample(int[] stream, int k) {

        int[] reservoir = new int[k];
        Random random = new Random();

        // First k elements
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        // Process remaining elements
        for (int i = k; i < stream.length; i++) {

            int j = random.nextInt(i + 1);

            if (j < k) {
                reservoir[j] = stream[i];
            }
        }

        return reservoir;
    }

    public static void main(String[] args) {

        int[] stream = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        int k = 5;

        int[] result = reservoirSample(stream, k);

        System.out.print("Reservoir Sample: ");

        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}