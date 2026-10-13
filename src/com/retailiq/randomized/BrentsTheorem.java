package com.retailiq.randomized;

public class BrentsTheorem {

    public static void main(String[] args) {

        int work = 1000;
        int processors = 4;

        int span = 250;

        int parallelTime = (work / processors) + span;

        System.out.println("Total Work: " + work);
        System.out.println("Number of Processors: " + processors);
        System.out.println("Span: " + span);
        System.out.println("Estimated Parallel Time: " + parallelTime);
    }
}