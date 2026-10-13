package com.retailiq.main;

import java.util.Scanner;

import com.retailiq.string.KMPProductSearch;

import com.retailiq.suffix.LCPKasaiDemo;
import com.retailiq.suffix.SAISDemo;
import com.retailiq.suffix.SuffixArrayDemo;
import com.retailiq.suffix.SuffixAutomatonDemo;
import com.retailiq.suffix.SuffixTreeDemo;

import com.retailiq.dp.LevenshteinDistance;
import com.retailiq.dp.DamerauLevenshtein;
import com.retailiq.dp.WeightedEditDistance;
import com.retailiq.dp.BitmaskDP;
import com.retailiq.dp.MatrixChainMultiplication;

import com.retailiq.flow.FordFulkerson;
import com.retailiq.flow.EdmondsKarp;
import com.retailiq.flow.Dinic;
import com.retailiq.flow.BipartiteMatching;
import com.retailiq.flow.MinCut;

import com.retailiq.np.SAT3SAT;
import com.retailiq.np.ThreeSATToClique;
import com.retailiq.np.CliqueIndependentVertexCover;
import com.retailiq.np.VertexCoverApproximation;

import com.retailiq.randomized.RandomizedQuickSort;
import com.retailiq.randomized.ReservoirSampling;
import com.retailiq.randomized.MillerRabin;
import com.retailiq.randomized.BlellochScan;
import com.retailiq.randomized.ParallelReduce;
import com.retailiq.randomized.BrentsTheorem;

public class RetailIQMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n==========================");
            System.out.println("     WELCOME TO RETAILIQ");
            System.out.println("==========================");

            System.out.println("1. Product Search - CO1");
            System.out.println("2. Suffix Structures - CO2");
            System.out.println("3. Dynamic Programming - CO3");
            System.out.println("4. Network Flow - CO4");
            System.out.println("5. NP Completeness - CO5");
            System.out.println("6. Randomized Algorithms - CO6");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                // =========================
                // CO1 - Product Search
                // =========================
                case 1:

                    System.out.print("Enter product name: ");

                    String query = sc.next();

                    KMPProductSearch.displayProducts(query);

                    break;


                // =========================
                // CO2 - Suffix Structures
                // =========================
                case 2:

                    System.out.println("Suffix Structures Module");

                    System.out.println("1. Suffix Array");
                    System.out.println("2. LCP Kasai");
                    System.out.println("3. Suffix Automaton");
                    System.out.println("4. SA-IS");
                    System.out.println("5. Suffix Tree");

                    System.out.print("Enter CO2 algorithm choice: ");

                    int co2Choice = sc.nextInt();

                    switch (co2Choice) {

                        case 1:
                            SuffixArrayDemo.main(null);
                            break;

                        case 2:
                            LCPKasaiDemo.main(null);
                            break;

                        case 3:
                            SuffixAutomatonDemo.main(null);
                            break;

                        case 4:
                            SAISDemo.main(null);
                            break;

                        case 5:
                            SuffixTreeDemo.main(null);
                            break;

                        default:
                            System.out.println("Invalid CO2 choice.");
                    }

                    break;


                // =========================
                // CO3 - Dynamic Programming
                // =========================
                case 3:

                    System.out.println("Dynamic Programming Module");

                    System.out.println("1. Levenshtein Distance");
                    System.out.println("2. Damerau-Levenshtein Distance");
                    System.out.println("3. Weighted Edit Distance");
                    System.out.println("4. Bitmask DP");
                    System.out.println("5. Matrix Chain Multiplication");

                    System.out.print("Enter CO3 algorithm choice: ");

                    int co3Choice = sc.nextInt();

                    switch (co3Choice) {

                        case 1:
                            LevenshteinDistance.main(null);
                            break;

                        case 2:
                            DamerauLevenshtein.main(null);
                            break;

                        case 3:
                            WeightedEditDistance.main(null);
                            break;

                        case 4:
                            BitmaskDP.main(null);
                            break;

                        case 5:
                            MatrixChainMultiplication.main(null);
                            break;

                        default:
                            System.out.println("Invalid CO3 choice.");
                    }

                    break;


                // =========================
                // CO4 - Network Flow
                // =========================
                case 4:

                    System.out.println("Network Flow Module");

                    System.out.println("1. Ford-Fulkerson");
                    System.out.println("2. Edmonds-Karp");
                    System.out.println("3. Dinic");
                    System.out.println("4. Bipartite Matching");
                    System.out.println("5. Min-Cut");

                    System.out.print("Enter CO4 algorithm choice: ");

                    int co4Choice = sc.nextInt();

                    switch (co4Choice) {

                        case 1:
                            FordFulkerson.main(null);
                            break;

                        case 2:
                            EdmondsKarp.main(null);
                            break;

                        case 3:
                            Dinic.main(null);
                            break;

                        case 4:
                            BipartiteMatching.main(null);
                            break;

                        case 5:
                            MinCut.main(null);
                            break;

                        default:
                            System.out.println("Invalid CO4 choice.");
                    }

                    break;


                // =========================
                // CO5 - NP Completeness
                // =========================
                case 5:

                    System.out.println("NP Completeness Module");

                    System.out.println("1. 3-SAT");
                    System.out.println("2. 3-SAT to Clique");
                    System.out.println("3. Clique / Independent Set / Vertex Cover");
                    System.out.println("4. Vertex Cover Approximation");

                    System.out.print("Enter CO5 algorithm choice: ");

                    int co5Choice = sc.nextInt();

                    switch (co5Choice) {

                        case 1:
                            SAT3SAT.main(null);
                            break;

                        case 2:
                            ThreeSATToClique.main(null);
                            break;

                        case 3:
                            CliqueIndependentVertexCover.main(null);
                            break;

                        case 4:
                            VertexCoverApproximation.main(null);
                            break;

                        default:
                            System.out.println("Invalid CO5 choice.");
                    }

                    break;


                // =========================
                // CO6 - Randomized Algorithms
                // =========================
                case 6:

                    System.out.println("Randomized Algorithms Module");

                    System.out.println("1. Randomized QuickSort");
                    System.out.println("2. Reservoir Sampling");
                    System.out.println("3. Miller-Rabin");
                    System.out.println("4. Blelloch Scan");
                    System.out.println("5. Parallel Reduce");
                    System.out.println("6. Brent's Theorem");

                    System.out.print("Enter CO6 algorithm choice: ");

                    int co6Choice = sc.nextInt();

                    switch (co6Choice) {

                        case 1:
                            RandomizedQuickSort.main(null);
                            break;

                        case 2:
                            ReservoirSampling.main(null);
                            break;

                        case 3:
                            MillerRabin.main(null);
                            break;

                        case 4:
                            BlellochScan.main(null);
                            break;

                        case 5:
                            ParallelReduce.main(null);
                            break;

                        case 6:
                            BrentsTheorem.main(null);
                            break;

                        default:
                            System.out.println("Invalid CO6 choice.");
                    }

                    break;


                // =========================
                // Exit
                // =========================
                case 0:

                    System.out.println("Thank you for using RetailIQ!");

                    break;


                // =========================
                // Invalid Choice
                // =========================
                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}